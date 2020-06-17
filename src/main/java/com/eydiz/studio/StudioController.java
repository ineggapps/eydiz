package com.eydiz.studio;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;

@Controller("studio.studioController")
@RequestMapping("/studio/*")
public class StudioController implements StudioConstant, MemberConstant {
	
	@Autowired
	StudioService service;
	
	//////////////////////////////////////////////브랜드
	@RequestMapping(value="/brand/info", method=RequestMethod.GET)
	public String getBrandInfo() {
		return "/studio/info_insert";//without tiles(단독 레이아웃 뷰)
	}
	
	@RequestMapping(value="/brand/info", method=RequestMethod.POST)
	public String postBrandInfo(Brand dto, HttpSession session) {
		String redirectUrl = API_PROJECT_LIST;
		try {
			SessionInfo info = (SessionInfo)session.getAttribute(SESSION_MEMBER);
			dto.setMemberNo(info.getMemberNo());
			service.updateBrand(dto);
		} catch (Exception e) {
			e.printStackTrace();
			redirectUrl = API_BRAND_INFO;
		}
		return "redirect:"+redirectUrl;
	}
	
	
	
	
	
	//////////////////////////////////////////////프로젝트
	
	@RequestMapping(value="/project/list")
	public String list() {
		
		return ".studioLayout";
	}
}
