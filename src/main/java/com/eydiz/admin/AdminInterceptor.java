package com.eydiz.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eydiz.member.LoginCheckInterceptor;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;

public class AdminInterceptor extends HandlerInterceptorAdapter implements MemberConstant{
	private final Logger logger = LoggerFactory.getLogger(LoginCheckInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result = true;
		HttpSession session = request.getSession();
		try {
			String cp = request.getContextPath();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			System.out.println(info);
			if(info == null || info.isAdmin() == false) {
				//관리자 계정이 아니면...
				result=false;
				response.sendRedirect(cp+API_ADMIN_LOGIN);
			}
		} catch (Exception e) {
			logger.info("admin 인터셉터 pre: " + e.toString());
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
