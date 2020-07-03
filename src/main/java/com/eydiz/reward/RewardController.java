package com.eydiz.reward;

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

import com.eydiz.common.Constant;
import com.eydiz.detail.DetailConstant;
import com.eydiz.detail.DetailService;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@RequestMapping("/reward/*")
@Controller("reward.rewardController")
public class RewardController implements Constant, MemberConstant, RewardConstant, DetailConstant {

	@Autowired
	private DetailService detailService;

	@RequestMapping(value = "/{projectNo}/step1", method = RequestMethod.GET)
	public String step1(@PathVariable Integer projectNo, HttpServletRequest req, Model model,
			@RequestParam(required = false, defaultValue = "0") int rewardNo) {
		try {
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			Map<String, Object> map = new HashMap<>();
			map.put(ATTRIBUTE_PROEJCTNO, projectNo);
			map.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			Project project = detailService.readProject(map);
			List<Reward> rewards = detailService.listRewards(projectNo);
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD, rewards);
			model.addAttribute(ATTRIBUTE_REWARDNO, rewardNo);

		} catch (NullPointerException e) {
			return "redirect:" + req.getContextPath();
		} catch (Exception e) {
			return "redirect:" + req.getContextPath() + String.format(API_DETAIL_PROJECT, projectNo);
		}
		return ".detailLayout.step1";
	}

	@RequestMapping(value = "/{projectNo}/step1", method = RequestMethod.POST)
	public String step1Submit(@PathVariable Integer projectNo, HttpServletRequest req,
			@RequestParam(required = false, value="rewardNo") List<Integer> rewardNoList, @RequestParam Map<String, Object> param) {
		try {
			if (projectNo == null) {
				throw new NullPointerException();
			}
			//리워드별로 수량
			HttpSession session = req.getSession();
			SessionRewardInfo rInfo = new SessionRewardInfo();
			rInfo.setProjectNo(projectNo);
			Map<Integer, Reward> rewards = new HashMap<>();
			rInfo.setRewards(rewards);
			for (Integer rewardNo : rewardNoList) {
				Reward r = detailService.readReward(rewardNo);
				r.setAmount(Integer.parseInt((String)param.get(ATTRIBUTE_REQUEST_QUANTITY+rewardNo)));
				r.setRewardOption((String)param.get(ATTRIBUTE_OPTION_ANSWER+rewardNo));
				rewards.put(rewardNo, r);
			}
			session.setAttribute(SESSION_REWARD, rInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:" + API_MAIN;
		}
		return "redirect:" + String.format(API_REWARD_STEP2, projectNo);
	}
	
	@RequestMapping(value="/{projectNo}/step2", method=RequestMethod.GET)
	public String step2(HttpSession session){
		try {
			SessionRewardInfo rInfo = (SessionRewardInfo) session.getAttribute(SESSION_REWARD);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ".detailLayout.step2";
	}
}
