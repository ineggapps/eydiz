package com.eydiz.reward;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.Constant;
import com.eydiz.detail.DetailConstant;
import com.eydiz.detail.DetailService;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;
import com.eydiz.reward.kakao.KakaoPayApproval;
import com.eydiz.reward.kakao.KakaoPayService;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@RequestMapping("/reward/*")
@Controller("reward.rewardController")
public class RewardController implements Constant, MemberConstant, RewardConstant, DetailConstant {

	@Autowired
	private DetailService detailService;

	@Autowired
	private KakaoPayService kakaoPayService;

	@Autowired
	private RewardService rewardService;

	@RequestMapping(value = "/{projectNo}/step1", method = RequestMethod.GET)
	public String step1(@PathVariable Integer projectNo, HttpServletRequest req, Model model,
			@RequestParam(required = false, defaultValue = "0") int rewardNo) {
		try {
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			Map<String, Object> map = new HashMap<>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			map.put(MemberConstant.ATTRIBUTE_MEMBERNO, info.getMemberNo());
			Project project = detailService.readProject(map);
			if(project==null || project.getRemainDays()<0) {
				return "redirect:" + API_MAIN;
			}
			List<Reward> rewards = detailService.listRewards(projectNo);
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD, rewards);
			model.addAttribute(ATTRIBUTE_REWARDNO, rewardNo);
		} catch (NullPointerException e) {
			return "redirect:" + req.getContextPath();
		} catch (Exception e) {
			return "redirect:" + req.getContextPath() + String.format(API_DETAIL_PROJECT, projectNo);
		}
		return ".rewardLayout.step1";
	}

	@RequestMapping(value = "/{projectNo}/step1", method = RequestMethod.POST)
	public String step1Submit(@PathVariable Integer projectNo, HttpServletRequest req,
			@RequestParam(required = false, value = "rewardNo") List<Integer> rewardNoList,
			@RequestParam Map<String, Object> param) {
		try {
			if (projectNo == null) {
				throw new NullPointerException();
			}
			// 리워드별로 수량
			HttpSession session = req.getSession();
			SessionRewardInfo rInfo = new SessionRewardInfo();
			rInfo.setProjectNo(projectNo);
			Map<Integer, Reward> rewards = new HashMap<>();
			rInfo.setRewards(rewards);
			int totalAmount = 0;
			int shipAmount = 0;
			for (Integer rewardNo : rewardNoList) {
				Reward r = detailService.readReward(rewardNo);
				// 수량 X 단가
				r.setRequestQuantity(Integer.parseInt((String) param.get(ATTRIBUTE_REQUEST_QUANTITY + rewardNo)));
				r.setPurchasePrice(r.getAmount() * r.getRequestQuantity());
				totalAmount += r.getPurchasePrice();
				if (shipAmount < r.getShipAmount()) {
					shipAmount = r.getShipAmount();
				}
				r.setOptionAnswer((String) param.get(ATTRIBUTE_OPTION_ANSWER + rewardNo));
				rewards.put(rewardNo, r);
			}
			// 최종 결제해야 할 금액 계산하기
			rInfo.setTotalAmount(totalAmount);
			rInfo.setShipAmount(shipAmount);
			rInfo.setFinalAmount(rInfo.getTotalAmount() + rInfo.getShipAmount());
			session.setAttribute(SESSION_REWARD, rInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:" + API_MAIN;
		}
		return "redirect:" + String.format(API_REWARD_STEP2, projectNo);
	}

	@RequestMapping(value = "/{projectNo}/step2", method = RequestMethod.GET)
	public String step2(@PathVariable Integer projectNo, HttpSession session, Model model) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			Project project = detailService.readProject(map);
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			//리워드 수량체크 (0개인 경우)
			SessionRewardInfo rInfo = (SessionRewardInfo) session.getAttribute(SESSION_REWARD);
			if(rewardService.isValidQuantity(rInfo)) {
				throw new Exception("수량이 0개인 거 어떻게 선택했지?");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:" + API_MAIN;
		}
		return ".rewardLayout.step2";
	}

	@RequestMapping(value = "/save/rewardShippingLocation", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveRewardShippingLocation(HttpSession session, String recipient, String zipCode,
			String phone, String address1, String address2, String message) {
		Map<String, Object> map = new HashMap<>();
		try {
			System.out.println(String.format("%s, %s, %s, %s, %s, %s ==============", recipient, zipCode, phone,
					address1, address2, message));
			SessionRewardInfo rInfo = (SessionRewardInfo) session.getAttribute(SESSION_REWARD);
			SessionInfo memberInfo = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			RewardShippingLocation rewardShippingLocation = new RewardShippingLocation(rInfo.getBuyNo(),
					memberInfo.getMemberNo(), phone, zipCode, address1, address2, recipient, message);
			rInfo.setRewardShippingLocation(rewardShippingLocation);
			map.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/{projectNo}/pay/kakao")
	public String step2Submit(@PathVariable Integer projectNo, HttpServletRequest req, HttpSession session, Model model) {
		SessionRewardInfo rInfo = null;
		SessionInfo memberInfo = null;
		URL requestURL = null;
		try {
			rInfo = (SessionRewardInfo) session.getAttribute(SESSION_REWARD);
			rInfo.setBuyNo(rewardService.nextBuyNo());
			System.out.println(rInfo.getBuyNo());
			memberInfo = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			if (projectNo == null || projectNo == 0 || rInfo == null || memberInfo == null) {
				return "redirect:/";
			}
		    requestURL = new URL(req.getRequestURL().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + kakaoPayService.kakaoPayReady(rInfo, memberInfo, requestURL);
	}

	@RequestMapping(value = "/{projectNo}/pay/kakao/abort") // 결제 중단
	public String stepPayAbort(@PathVariable Integer projectNo,Model model, HttpSession session) {
		return ".rewardLayout.payAbort";
	}

	@RequestMapping(value = "/{projectNo}/pay/kakao/success")
	@ResponseBody
	public Map<String, Object> stepSuccess(@PathVariable Integer projectNo, @RequestParam("pg_token") String pg_token,
			HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			SessionRewardInfo rInfo = (SessionRewardInfo) session.getAttribute(SESSION_REWARD);
			SessionInfo memberInfo = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			KakaoPayApproval kakaoPayApproval = kakaoPayService.kakaoPayInfo(pg_token, rInfo, memberInfo);
			rInfo.setKakaoPayApproval(kakaoPayApproval);
			map.put(JSON_RESULT, JSON_RESULT_OK);
			map.put(JSON_PG_TOKEN, pg_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/pay/success") // 최종적인 결제 완료 시 쿼리 등록하기
	public String stepPaySuccess(Model model, HttpSession session) {
		SessionRewardInfo rInfo = null;
		SessionInfo memberInfo = null;
		try {
			// 쿼리 등록하기
			rInfo = (SessionRewardInfo) session.getAttribute(SESSION_REWARD);
			memberInfo = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			rewardService.insertReward(rInfo, memberInfo);
			if(rInfo.getKakaoPayApproval()!=null) {				
				rewardService.insertRewardBuyKakao(rInfo.getBuyNo(), rInfo.getKakaoPayApproval().getTid());//카카오페이인 경우
			}
			model.addAttribute(ATTRIBUTE_PROJECTNO, rInfo.getProjectNo());
			model.addAttribute("kakaoPay", rInfo.getKakaoPayApproval());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ".rewardLayout.paySuccess";
	}

}
