package com.eydiz.admin.member;

import java.io.UnsupportedEncodingException;
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
	
@Controller("member.adminMemberController")
@RequestMapping(value="/admin/member/*")
public class AdminMemberController {
	@Autowired
	private AdminMemberService service;
	@Autowired
	private MyUtil myutil;
	
	@RequestMapping(value="memberlist")
	public String memberlist(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="memberNo") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpServletRequest req,
			Model model
			) throws Exception {
		
		String cp = req.getContextPath();
		
		int rows = 10;
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		dataCount = service.maindataCount(map);

		if(dataCount != 0) {
			total_page = myutil.pageCount(rows, dataCount);
		}
		
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page-1) * rows;
		if(offset < 0) {
			offset = 0;
		}
		
		map.put("offset", offset);
		map.put("rows", rows);

		List<AdminMember> list = service.mainAdminMember(map);
		
		int listNum, num = 0;
		for(AdminMember dto : list) {
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}
		
		String query = "";
		String listUrl = cp+"/admin/member/memberlist";
		if(keyword.length()!=0) {
			query = "condition="+condition+"&keyword="+URLEncoder.encode(keyword, "utf-8");
		}
		
		if(query.length()!=0) {
			listUrl = cp+"/admin/member/memberlist";
		}
		
		String paging = myutil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("page", current_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("paging", paging);
		model.addAttribute("menu", "member");
		
		return ".adminLayout.member.memberlist";
	}
	
	@RequestMapping(value="brandMemberlist")
	public String brandMemberlist(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="memberNo") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpServletRequest req,
			Model model
			) {
		String cp = req.getContextPath();
		
		int rows = 10;
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			try {
				keyword = URLDecoder.decode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		dataCount = service.listdataCount(map);
		if(dataCount != 0) {
			total_page = myutil.pageCount(rows, dataCount);
		}
		
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page-1) * rows;
		if(offset < 0) {
			offset = 0;
		}
		
		map.put("offset", offset);
		map.put("rows", rows);
		
		List<AdminMember> list = service.listAdminMember(map);
		
		int listNum, num = 0;
		for(AdminMember dto : list) {
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}
		
		String query = "";
		String listUrl = cp+"/admin/member/brandMemberlist";
		if(keyword.length()!=0) {
			try {
				query = "condition="+condition+"&keyword="+URLEncoder.encode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if(query.length()!=0) {
			listUrl = cp+"/admin/member/brandMemberlist";
		}
		
		String paging = myutil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("page", current_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("paging", paging);
		model.addAttribute("menu", "brand");
		
		return ".adminLayout.member.brandMemberlist";
	}
}
