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
public class PartnerController {
	@Autowired
	private PartnerService service;
	
	@Autowired
	private MyUtil myUtil;

	@RequestMapping(value="/partner/list")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="") String keyword,
			Partner dto,
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
		List<Partner> list = service.listPartner(map, dto.getBrandNo());
	
		
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
		
		
		int totalBrandCount = service.totalBrandCount();
		int totalProjectCount = service.totalProjectCount();
		int totalBuyMemberCount = service.totalBuyMemberCount();
		
		model.addAttribute("totalBrandCount", totalBrandCount);
		model.addAttribute("totalProjectCount", totalProjectCount);
		model.addAttribute("totalBuyMemberCount", totalBuyMemberCount);
				
		String today;
		today = service.getToday();
		model.addAttribute("today", today);
		
		
		return ".partnerLayout.list";
	}
	
	@RequestMapping(value="/partner/article")
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
		
		int brandTotalProject = service.brandTotalProject(brandNo);
		int brandTotalBuyMember = service.brandTotalBuyMember(brandNo);
		double brandTotalAmount = service.brandTotalAmount(brandNo);
		String memberImageUrl = service.memberImageUrl(brandNo);
		String today = service.getToday();

		model.addAttribute("brandTotalProject", brandTotalProject);
		model.addAttribute("brandTotalBuyMember", brandTotalBuyMember);
		model.addAttribute("brandTotalAmount", brandTotalAmount);
		model.addAttribute("memberImageUrl", memberImageUrl);
		model.addAttribute("today", today);
		
		return ".partnerLayout.article";
	}
	
	@RequestMapping(value="/partner/articleList")
	public String articleList(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="5") int statusNo,
			Partner dto,
			Model model
		) throws Exception {
		
		int rows = 12;
		int total_page;
		int pDataCount;
		
		Map<String, Object> map = new HashMap<>();
		map.put("statusNo", statusNo);
		map.put("brandNo", dto.getBrandNo());
		
		pDataCount = service.pDataCount(map);
		total_page = myUtil.pageCount(rows, pDataCount);
		
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page-1)*rows;
		if(offset < 0) offset = 0;
		
		map.put("offset", offset);
		map.put("rows", rows);
		
		List<Partner> plist = service.listProject(map);
		
		String paging = myUtil.pagingMethod(current_page, total_page, "listProjectPage");
		
		int projectTotalAmount = 0;
		int projectGoalAmount = 0;
		int percentage = 0;
		
		
		dto = service.getProjectMoneyInfo(dto.getProjectNo());
		
		
		projectTotalAmount = dto.getProjectTotalAmount();
		projectGoalAmount = dto.getProjectGoalAmount();
		percentage = dto.getPercentage();
		
		
		model.addAttribute("plist", plist);
		model.addAttribute("pDataCount", pDataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		
		model.addAttribute("projectTotalAmount", projectTotalAmount);
		model.addAttribute("projectGoalAmount", projectGoalAmount);
		model.addAttribute("percentage", percentage);
		return "partner/articleList";
	}
	
}
