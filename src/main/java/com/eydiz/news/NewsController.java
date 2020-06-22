package com.eydiz.news;

import java.io.File;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.MyUtil;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;

@Controller("news.newsController")
@RequestMapping("/news/*")
public class NewsController implements MemberConstant{
	@Autowired
	private NewsService service;
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="main")
	public String main() throws Exception {
		return ".newsLayout.main";
	}
	
	// AJAX-Text(HTML)
	@RequestMapping(value="list")
	public String list(
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@RequestParam(defaultValue="all") String condition,
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="10") int nocaNo,
			HttpServletRequest req,
			Model model
			) throws Exception {
		
		int rows = 5;
		int total_page;
		int dataCount;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nocaNo", nocaNo);
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
		
		String paging = myUtil.pagingMethod(current_page, total_page, "listPage");
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("pageNo", current_page);
		model.addAttribute("paging", paging);
		
		model.addAttribute("condition", condition);
		model.addAttribute("keyword", keyword);
		
		return "news/list";
	}
	
	// AJAX-Text(HTML)
	@RequestMapping(value="created", method=RequestMethod.GET)
	public String createdForm(Model model) throws Exception {
		model.addAttribute("mode", "created");
		model.addAttribute("pageNo", "1");
		return "news/created";
	}
	
	// AJAX-JSON
	@RequestMapping(value="created", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createdSubmit(
			News dto,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String path = root+"uploads"+File.separator+"news";
		
		SessionInfo info = (SessionInfo)session.getAttribute(SESSION_MEMBER);
		String state="true";
		try {
			dto.setMemberNo(info.getMemberNo());
			service.insertNews(dto, path);
		} catch (Exception e) {
			state="false";
			e.printStackTrace();
		}
		
		Map<String, Object> model=new HashMap<>();
		model.put("state", state);
		
		return model;
	}
	
	// AJAX-Text(HTML)
	@RequestMapping(value="article", method=RequestMethod.GET)
	public String article(
			@RequestParam int noticeNo,
			@RequestParam String pageNo,
			@RequestParam(defaultValue="10") int nocaNo,
			@RequestParam(defaultValue="subject") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model
			) throws Exception {
		
		keyword  = URLDecoder.decode(keyword, "utf-8");

		News dto = service.readNews(noticeNo);
		if(dto==null) {
			return "news/error";
		}
		
		dto.setNoticeContent(dto.getNoticeContent().replaceAll("\n", "<br>"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("nocaNo", nocaNo);
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("noticeNo", noticeNo);
		
		News preReadDto = service.preReadNews(map);
		News nextReadDto = service.nextReadNews(map);
		
		model.addAttribute("dto", dto);
		model.addAttribute("preReadDto", preReadDto);
		model.addAttribute("nextReadDto", nextReadDto);
		
		model.addAttribute("pageNo", pageNo);
		
		return "news/content";
	}
	
	// AJAX-Text(HTML)
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam int noticeNo,
			@RequestParam String pageNo,
			HttpSession session,
			Model model
			) throws Exception {
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		News dto = service.readNews(noticeNo);
		if(dto == null) {
			return "news/error";
		}
		
		if(dto.getMemberNo()!=info.getMemberNo()) {
			return "news/error";
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("mode", "update");
		
		return "news/created";
	}
	
	// AJAX-JSON
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateSubmit(
			News dto,
			@RequestParam String page,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String pathname=root+"uploads"+File.separator+"news";
		
		String state="true";
		try {
			service.updateNews(dto, pathname);
		} catch (Exception e) {
			state="false";
		}
		
		Map<String, Object> model=new HashMap<>();
		model.put("state", state);
		
		return model;
	}
	
	// AJAX-Text(HTML)
	@RequestMapping(value="delete")
	@ResponseBody
	public Map<String, Object> delete(
			@RequestParam int noticeNo,
			HttpSession session
			) throws Exception {

		String root = session.getServletContext().getRealPath("/");
		String pathname = root+"uploads"+File.separator+"news";
		
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		String state="true";
		try {
			service.deleteNews(noticeNo, pathname, info.getMemberNo());
		} catch (Exception e) {
			state="false";
		}
		
		Map<String, Object> model=new HashMap<>();
		model.put("state", state);
		
		return model;
	}
	
}
