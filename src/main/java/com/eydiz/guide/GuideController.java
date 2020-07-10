package com.eydiz.guide;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eydiz.common.MyUtil;
import com.eydiz.member.SessionInfo;

@Controller("guide.Controller")
@RequestMapping("/guide/*")
public class GuideController {
	@Autowired
	GuideSerivce service;
	
	@Autowired
	private MyUtil myutil;
	
	@RequestMapping(value="main")
	public String main(
			@RequestParam(defaultValue="") String keyword,
			HttpServletRequest req,
			Model model
			) throws Exception {
		
		int dataCount;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		
		dataCount = service.dataCount(map);
		
		List<Guide> list = service.listFaq(map);
		
		int listNum = 0;
		for(Guide dto : list) {
			listNum++;
			dto.setListNum(listNum);
			
			dto.setFaqContent(myutil.htmlSymbols(dto.getFaqContent()));
		}
		
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		
		model.addAttribute("keyword", keyword);
		
		return ".guideLayout.main";
	}
	
	@RequestMapping(value="createguide", method=RequestMethod.GET)
	public String createForm(
			Model model
			) throws Exception {
		model.addAttribute("mode", "createguide");
		
		return ".guideLayout.createguide";
	}
	
	@RequestMapping(value="createguide")
	public String createdSubmit(
			Guide dto,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String path = root+"upload"+File.separator+"guide";
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		try {
			dto.setMemberNo(info.getMemberNo());
			service.insertFaq(dto, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/guide/main";
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam int faqNo,
			HttpSession session,
			Model model
			) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		Guide dto = service.readFaq(faqNo);
		if(dto == null) {
			return "redirect:/guide/main";
		}
		
		if(info.getMemberNo() != (dto.getMemberNo())) {
			return "redirect:/guide/main";
		}
		
		model.addAttribute("mode", "update");
		model.addAttribute("dto", dto);
		
		return ".guideLayout.createguide";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateSubmit(
			Guide dto,
			HttpSession session
			) throws Exception {
		
		String root = session.getServletContext().getRealPath("/");
		String pathname = root+"uploads"+File.separator+"guide";
		
		try {
			service.updateFaq(dto, pathname);
		} catch (Exception e) {
		}
		
		return "redirect:/guide/main";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String deleteGuide(
			@RequestParam int faqNo,
			@RequestParam(defaultValue="") String keyword,
			HttpSession session
			) throws Exception {

		String root = session.getServletContext().getRealPath("/");
		String pathname=root+"uploads"+File.separator+"guide";
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		String query = "";
		if(keyword.length()!=0) {
			query="?keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		try {
			service.deleteFaq(faqNo, pathname, info.getMemberNo());
		} catch (Exception e) {
		}
		
		return "redirect:/guide/main"+query;
	}
}
