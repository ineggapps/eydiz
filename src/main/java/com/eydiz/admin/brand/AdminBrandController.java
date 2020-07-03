package com.eydiz.admin.brand;

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


@Controller("brand.adminBrandController")
@RequestMapping("/admin/brand/*")
public class AdminBrandController {
	@Autowired
	private AdminBrandService service;

	@Autowired
	private MyUtil myutil;

	@RequestMapping(value= "list")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="brandName") String condition,
			@RequestParam(defaultValue="") String keyword, 
			Model model, HttpServletRequest req
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

		dataCount = service.dataCount(map);

		if(dataCount != 0) {
			total_page = myutil.pageCount(rows, dataCount);
		}

		if(total_page < current_page) {
			current_page = total_page;
		}

		int offset = (current_page-1) * rows;
		if(offset < 0) offset = 0;
		map.put("offset", offset);
		map.put("rows", rows);

		List<Brand> list = service.listBrand(map);

		int listNum = 0;
		int num = 0;
		for(Brand dto : list) {
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}

		String query = "";
		String listUrl = cp+"/admin/brand/list";
		String readUrl = cp+"/admin/brand/read?page="+current_page;

		if(keyword.length() != 0) {
			query = "condition=" + condition + "&keyword=" + URLEncoder.encode(keyword, "utf-8");
		}

		if(query.length() != 0) {
			listUrl = cp+"/admin/brand/list?query=" + query;
			readUrl = cp+"/admin/brand/read?page=" + current_page + "&query=" + query;
		}

		String paging = myutil.paging(current_page, total_page, listUrl);

		model.addAttribute("list", list);
		model.addAttribute("page", current_page);
		model.addAttribute("listUrl", listUrl);
		model.addAttribute("condition", condition);
		model.addAttribute("keyword", keyword);
		model.addAttribute("paging", paging);
		model.addAttribute("readUrl", readUrl);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);

		return ".adminLayout.brand.list"; 
	}

	@RequestMapping(value="read")
	public String read(
			@RequestParam int brandNo,
			@RequestParam String page,
			@RequestParam(defaultValue="brandName") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model
			) throws Exception {
		keyword = URLDecoder.decode(keyword, "utf-8");

		String query = "page="+page;
		if(keyword.length() != 0) {
			query = "condition=" +condition+ "&keyword=" +keyword+ URLEncoder.encode(keyword, "utf-8");
		}


		Brand dto = service.readBrandList(brandNo);
		if(dto == null) {
			return "redirect:/admin/brand/list?"+query;
		}

		Map<String, Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("brandNo", brandNo);

		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		model.addAttribute("query", query);

		return ".adminLayout.brand.read";
	}

}
