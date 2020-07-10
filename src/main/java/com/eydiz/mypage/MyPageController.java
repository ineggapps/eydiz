package com.eydiz.mypage;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import com.eydiz.common.Constant;
import com.eydiz.common.Pager;
import com.eydiz.member.Member;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.MemberService;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@Controller("mypage.myPageController")
@RequestMapping(value = { "/mypage/*" })
public class MyPageController implements Constant, MemberConstant, MyPageConstant {
	private static final String REQUEST_MAPPING = "/mypage";
	private static final String JSON_AVATAR_URI = "avatar_uri";

	@Autowired
	private MyPageService myPageService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private Pager pager;
	
	private String getRealPath(HttpSession session) {
		// "/"문자열 다음부터 추출해야...
		String cpRealPath = session.getServletContext().getRealPath("/");
		String upload = File.separator + FOLDER_UPLOADS_ROOT + File.separator + FOLDER_UPLOADS_MEMBER;
		return cpRealPath + upload;
	}

	private String getRealURI(String requestURI, String contextPath) {
		return requestURI.substring(contextPath.length() + REQUEST_MAPPING.length());
	}

	private void addModelURIAttribute(Model model, HttpServletRequest req) {
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		if (req.getQueryString() != null && req.getQueryString().length() > 0) {
			uri.append("?" + req.getQueryString());
		}
		model.addAttribute(ATTRIBUTE_URI, getRealURI(uri.toString(), req.getContextPath()));
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public String emptyMapping() {
		return "redirect:"+API_MYPAGE_MYINFO;
	}

	@RequestMapping(value = {"/main", "/myInfo", "/myInfo/" }, method=RequestMethod.GET)
	public String main(Model model, HttpServletRequest req) {
		addModelURIAttribute(model, req);
		try {
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			if (info == null) {
				throw new NullPointerException();
			}
			Member member = memberService.readMember(info.getMemberId());
			model.addAttribute(ATTRIBUTE_MEMBER, member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ".myPageLayout.myInfo";
	}
	
	@RequestMapping(value="/myInfo", method=RequestMethod.POST)
	public String myInfoSubmit(Model model, HttpServletRequest req, Member member) {
		addModelURIAttribute(model, req);
		try {
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			if(info==null) {
				throw new NullPointerException();
			}
			member.setMemberNo(info.getMemberNo());
			myPageService.updateMyInfo(member);
			info.setMemberNickname(member.getMemberNickname());
			info.setMemberEmail(member.getMemberEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:"+API_MYPAGE_MYINFO;
	}

	@RequestMapping(value = "/upload/avatar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadProfile(Model model, HttpServletRequest req,
			@RequestParam("avatar") MultipartFile avatar) {
		Map<String, Object> map = new HashMap<>();
		try {

			// 업로드
			String cp = req.getServletContext().getContextPath();
			String realPath = getRealPath(req.getSession());
			String uriPath = cp + "/" + FOLDER_UPLOADS_ROOT + "/" + FOLDER_UPLOADS_MEMBER;
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			Member member = memberService.readMember(info.getMemberId());
			String avatarUri = myPageService.uploadAvatarImage(member, avatar, realPath, uriPath);
			info.setMemberImageUrl(avatarUri);
			map.put(JSON_RESULT, JSON_RESULT_OK);
			map.put(JSON_AVATAR_URI, avatarUri);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR, e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = {"/funding","/funding/","/funding/{buyNo}"})
	public String funding(@PathVariable(required=false) Integer buyNo, Model model, HttpServletRequest req) {
		addModelURIAttribute(model, req);
		try {
			if(buyNo!=null) {
				//buyNo 이용해서 관련 구매정보 추출
				SessionInfo sessionInfo = (SessionInfo)req.getSession().getAttribute(SESSION_MEMBER);
				Project project = myPageService.readBoughtMyProject(sessionInfo.getMemberNo(), buyNo);
				List<Reward> rewards = myPageService.readBoughtMyReward(sessionInfo.getMemberNo(), buyNo);
				model.addAttribute(ATTRIBUTE_PROJECT, project);
				model.addAttribute(ATTRIBUTE_REWARDS, rewards);
				return ".myPageLayout.fundingDetail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return ".myPageLayout.funding";
	}
	
	@RequestMapping(value="/cancel", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cancelFunding(@RequestParam(required=false, defaultValue="0") int buyNo, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(buyNo<=0) {
				throw new Exception("buyNo...T^T");
			}
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			myPageService.insertCancel(info.getMemberNo(), buyNo, "전액 펀딩 취소");
			map.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/history/{page}")
	@ResponseBody
	public Map<String, Object> readMyBoughtProjects(Model model, HttpServletRequest req, @PathVariable Integer page){
		Map<String, Object> map = new HashMap<>();
		try {
			if(page==null) {
				throw new NullPointerException();
			}
			final int rows = 5;
			int offset = pager.getOffset(page, rows);
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			int dataCount = myPageService.countBoughtMyProjects(info.getMemberNo());
			List<Project> project = myPageService.readBoughtMyProjects(info.getMemberNo(), offset, rows);
			map.put(JSON_RESULT, JSON_RESULT_OK);
			map.put(JSON_MY_BOUGHT_PROJECTS, project);
			map.put(JSON_PAGE_COUNT, pager.pageCount(rows, dataCount));
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}
}
