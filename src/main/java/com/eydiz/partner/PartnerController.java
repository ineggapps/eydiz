package com.eydiz.partner;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eydiz.common.MyUtil;

@Controller("partner.partnerController")
@RequestMapping("/partner/*")
public class PartnerController {
	@Autowired
	private PartnerService service;
	
	@Autowired
	private MyUtil myUtil;

	@RequestMapping(value="list")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="") String keyword,
			Model model,
			HttpServletRequest req
			) throws Exception {
		
		String cp = req.getContextPath();
		
		int rows = 12;
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		
		// 전체 페이지
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		
		dataCount = service.dataCount(map);
		
		if(dataCount != 0) {
			total_page = myUtil.pageCount(rows, dataCount);
		}
		
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page - 1) * rows;
		if(offset < 0) offset = 0;
		map.put("offset", offset);
		map.put("rows", rows);
		
		// 글 리스트
		List<Partner> list = service.listPartner(map);
	
		
		String query = "";
        String listUrl = cp+"/partner/list";
        String articleUrl = cp+"/partner/article?page=" + current_page;
        if(keyword.length()!=0) {
        	query =  "keyword=" + URLEncoder.encode(keyword, "utf-8");	
        }
        
        if(query.length()!=0) {
        	listUrl = cp+"/partner/list?" + query;
        	articleUrl = cp+"/partner/article?page=" + current_page + "&"+ query;
        }
		
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		
		
		return ".partnerLayout.list";
	}
	
	@RequestMapping(value="article")
	public String article(
			@RequestParam int brandNo,
			@RequestParam String page,
			@RequestParam(defaultValue="") String keyword,
			Model model,
			HttpServletRequest req
			) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("GET")) { // GET 방식인 경우
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		String query = "page="+page;
		if(keyword.length() != 0) {
			query += "&keyword="+URLEncoder.encode(keyword, "UTF-8");
		}
		
		Partner dto = service.readPartner(brandNo);
		System.out.println(dto);
		if(dto == null) {
			return "redirect:/partner/list?"+query;
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		
		return ".partnerLayout.article";
	}
	
}
