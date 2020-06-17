package com.eydiz.news;

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

@Controller("news.Controller")
@RequestMapping("/news/*")
public class NewsController {
	@Autowired
	private NewsService service;
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="main")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="subject") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpServletRequest req,
			Model model
			) throws Exception {
		String cp = req.getContextPath();
		
		int rows = 10;
		int total_page;
		int dataCount;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		dataCount = service.dataCount(map);
		total_page = myUtil.pageCount(rows, dataCount);
		
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page-1)*rows;
		if(offset < 0) offset = 0;
		map.put("offset", offset);
		map.put("rows", rows);
		
		List<News> list = service.listNews(map);
		
		int listNum, num = 0;
		for(News dto : list) {
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}
		
		String query = "";
		String listUrl = cp+"/news/main";
		String articleUrl = cp+"/news/content?page="+current_page;
		if(keyword.length()!=0) {
			query = "condition="+condition+"&keyword=" +URLEncoder.encode(keyword, "utf-8");
		}			
		
		if(query.length()!=0) {
			listUrl = cp+"/news/main?"+query;
			articleUrl = cp+"/news/content?page="+current_page+"&"+query;
		}
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("paging", paging);
		
		model.addAttribute("condition", condition);
		model.addAttribute("keyword", keyword);
		
		return ".newsLayout.main";
	}
	
	public String createdForm(Model model) throws Exception {
		
		model.addAttribute("mode", "created");
		return ".news.created";
	}
	
	@RequestMapping(value="created", method=RequestMethod.GET)
	public String createdSubmit(
			News dto,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String path = root+"uploads"+File.separator+"news";
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		try {
			dto.setmNum(info.getMemberNo());
			service.insertNews(dto, path);
		} catch (Exception e) {
		}
		
		return "redirect:/news/main";
	}
	
	public String article(
			@RequestParam int nNum,
			@RequestParam String page,
			@RequestParam(defaultValue="subject") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model
			) throws Exception {
		
		keyword  = URLDecoder.decode(keyword, "utf-8");
		
		String query = "page="+page;
		if(keyword.length()!=0) {
			query+="&condition="+condition+"&keyword="+URLEncoder.encode(keyword ,"utf-8");
		}
		
		News dto = service.readNews(nNum);
		if(dto==null) {
			return "redirect:/news/main?"+query;
		}
		
		dto.setNcontent(dto.getNcontent().replaceAll("\n", "<br>"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("num", nNum);
		
		News preReadDto = service.preReadNews(map);
		News nextReadDto = service.nextReadNews(map);
		
		model.addAttribute("dto", dto);
		model.addAttribute("preReadDto", preReadDto);
		model.addAttribute("nextReadDto", nextReadDto);
		
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		
		return ".news.content";
	}
	
	
}
