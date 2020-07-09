package com.eydiz.mypage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eydiz.common.Constant;
import com.eydiz.common.FileManager;
import com.eydiz.member.Member;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.MemberService;
import com.eydiz.member.SessionInfo;

@Controller("mypage.myPageController")
@RequestMapping(value = { "/mypage", "/mypage/*" })
public class MyPageController implements Constant, MemberConstant {
	private static final String REQUEST_MAPPING = "/mypage";
	private static final String JSON_AVATAR_URI = "avatar_uri";

	@Autowired
	private FileManager fileManager;

	@Autowired
	private MyPageService myPageService;

	@Autowired
	private MemberService memberService;

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

	@RequestMapping(value = { "", "/", "/main", "/myInfo", "/myInfo/" })
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

	@RequestMapping(value = "/mypage/funding")
	public String funding(Model model, HttpServletRequest req) {
		addModelURIAttribute(model, req);
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ".myPageLayout.funding";
	}
}
