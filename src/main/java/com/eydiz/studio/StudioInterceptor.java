package com.eydiz.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eydiz.member.LoginCheckInterceptor;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;

public class StudioInterceptor extends HandlerInterceptorAdapter implements MemberConstant, StudioConstant{
	private final Logger logger = LoggerFactory.getLogger(LoginCheckInterceptor.class);

	@Autowired
	StudioService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result = true;
		HttpSession session = request.getSession();
		try {
			String cp = request.getContextPath();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			result = service.myBrandCount(info.getMemberNo()) > 0 ? true : false;
			if(result==false) {
				//브랜드를 만들지 않았으면
				response.sendRedirect(cp+API_BRAND_INFO);
			}
		} catch (Exception e) {
			logger.info("studio 인터셉터 pre: " + e.toString());
		}
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

}
