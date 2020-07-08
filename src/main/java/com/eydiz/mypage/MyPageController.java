package com.eydiz.mypage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eydiz.common.Constant;
import com.eydiz.member.MemberConstant;
import com.eydiz.studio.StudioConstant;

@Controller("mypage.myPageController")
@RequestMapping(value = { "/mypage", "/mypage/*" })
public class MyPageController implements Constant, MemberConstant {
	private static final String REQUEST_MAPPING = "/mypage";
	
	private String getRealURI(String requestURI, String contextPath) {
		return requestURI.substring(contextPath.length() + REQUEST_MAPPING.length());
	}

	private void addModelURIAttribute(Model model, HttpServletRequest req) {
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		if (req.getQueryString() != null && req.getQueryString().length() > 0) {
			uri.append("?" + req.getQueryString());
		}
		System.out.println(uri.toString() +"\n"+getRealURI(uri.toString(), req.getContextPath()));
		model.addAttribute(ATTRIBUTE_URI, getRealURI(uri.toString(), req.getContextPath()));
	}

	@RequestMapping(value = { "", "/", "/main" })
	public String main(Model model, HttpServletRequest req) {
		addModelURIAttribute(model, req);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ".myPageLayout";
	}

	@RequestMapping(value = "/mypage/funding")
	public String funding(Model model, HttpServletRequest req) {
		addModelURIAttribute(model, req);
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ".myPageLayout";
	}
}
