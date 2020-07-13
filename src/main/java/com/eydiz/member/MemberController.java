package com.eydiz.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("member.memberController")
@RequestMapping("/member/*")
public class MemberController implements MemberConstant {
	@Autowired
	private MemberService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return ".memberLayout.login";
	}
	
	@RequestMapping(value="/admin", method= RequestMethod.GET)
	public String adminForm() {
		return "/admin/admin_login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSubmit(Member dto, final RedirectAttributes reAttr, HttpSession session) {
		String uri = null;
		try {
			Member login = service.loginMember(dto); // 로그인실패하면 예외 처리해야 함.
			SessionInfo info = new SessionInfo(login);
			session.setMaxInactiveInterval(30 * 60);
			session.setAttribute(SESSION_MEMBER, info);

			uri = (String)session.getAttribute(REDIRECTION_URI);
			if (uri == null || uri.trim().length() == 0) {
				uri = API_MAIN;
			}
		} catch (AuthenticationException e) {
			reAttr.addFlashAttribute(EXCEPTION_AUTHENTICATION, EXCEPTION_AUTHENTICATION);
			return "redirect:" + API_LOGIN;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + uri;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(SESSION_MEMBER);
		session.invalidate();
		return "redirect:" + API_MAIN;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinIntro(@RequestParam(defaultValue="1") int step) {
		if(step==2) {			
			return ".memberLayout.join_detail";
		}else {
			return ".memberLayout.join_intro";
		}
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinEmailSubmit(@RequestParam(defaultValue="2") int step, Member dto) {
		System.out.println("회원가입 시도");
		String redirectUrl = API_MAIN; //가입 시 완료페이지가 기본
		try {
			service.insertMember(dto);
		} catch (Exception e) {
			e.printStackTrace();
			redirectUrl = step==2? API_JOIN_DETAIL : API_JOIN_INTRO;
		}
		return "redirect:"+redirectUrl;
	}
	
	@RequestMapping(value="/join/checkId")
	@ResponseBody
	public Map<String, String>isTakenId(String memberId) {
		boolean isTaken = true;
		Map<String, String> map = new HashMap<>();
		try {
			isTaken = service.isTakenId(memberId);
			map.put(JSON_IS_TAKEN, String.valueOf(isTaken));
			map.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/join/checkEmail")
	@ResponseBody
	public Map<String, String> isTakenEmail(String memberEmail){
		boolean isTaken = true;
		Map<String, String> map = new HashMap<>();
		try {
			isTaken = service.isTakenEmail(memberEmail);
			map.put(JSON_IS_TAKEN, String.valueOf(isTaken));
			map.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}

}
