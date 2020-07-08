package com.eydiz.studio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Brand;
import com.eydiz.studio.BrandSessionInfo;
import com.eydiz.studio.StudioConstant;
import com.eydiz.studio.StudioService;

@Controller("studio.controller.studioBrandController")
@RequestMapping("/studio/brand/*")
public class StudioBrandController implements StudioConstant, MemberConstant {
	private Logger logger = LoggerFactory.getLogger(StudioProjectController.class);

	@Autowired
	StudioService service;

	private String getRealURI(String requestURI, String contextPath) {
		return requestURI.substring(contextPath.length() + REQUEST_MAPPING.length());
	}

	private void addModelURIAttribute(Model model, HttpServletRequest req) {
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		if (req.getQueryString() != null && req.getQueryString().length() > 0) {
			uri.append("?" + req.getQueryString());
		}
		model.addAttribute(MemberConstant.ATTRIBUTE_URI, getRealURI(uri.toString(), req.getContextPath()));
	}

		//////////////////////////////////////////////브랜드
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getBrandInfo(Model model, HttpServletRequest req, HttpSession session) {
		String viewPath = VIEW_BRAND_INFO_PRIMAL;
		try {
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			//int myBrandCount = service.myBrandCount(info.getMemberNo());
			//if(myBrandCount>0) {
			//viewPath = VIEW_BRAND_INFO_UPDATE;
			//}
			// brand가 회원당 1개일 때 기준으로...
			Brand dto = service.readBrand(info.getMemberNo());
			if (dto != null && dto.getBrandNo() > 0) {
				viewPath = VIEW_BRAND_INFO_UPDATE;
				model.addAttribute(ATTRIBUTE_BRAND, dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addModelURIAttribute(model, req);
		return viewPath;// without tiles(단독 레이아웃 뷰)
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String postBrandInfo(Brand dto, Model model, HttpServletRequest req, HttpSession session) {
		String redirectUrl = API_PROJECT_LIST;
		try {
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			dto.setMemberNo(info.getMemberNo());
			service.updateBrand(dto);
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			bInfo.setBrandName(dto.getBrandName());
		} catch (Exception e) {
			e.printStackTrace();
			redirectUrl = API_BRAND_INFO;
		}
		return "redirect:" + redirectUrl;
	}
}
