package com.eydiz.studio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;

public class StudioBrandInterceptor extends HandlerInterceptorAdapter implements MemberConstant, StudioConstant {

	private final Logger logger = LoggerFactory.getLogger(StudioBrandInterceptor.class);

	@Autowired
	private StudioService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션 입히기
		HttpSession session = request.getSession();
		try {
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			if (bInfo == null || bInfo.getBrandName() == null || bInfo.getBrandName().length() == 0) {
				Brand brand = service.readBrand(info.getMemberNo());
				session.setAttribute(SESSION_BRAND, brand);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return true;// 컨트롤러는 무조건 호출해야 함.
	}

}
