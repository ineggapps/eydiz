package com.eydiz.cast;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.MyUtil;
import com.eydiz.member.SessionInfo;

@Controller("cast.castController")
@RequestMapping("/cast/*")
public class CastController {
	@Autowired
	private CastService service;
	
	@Autowired
	private MyUtil myUtil;
	
	
	@RequestMapping(value="news")
	public String list(
			@RequestParam(value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="1") int castCnum,
			@RequestParam(defaultValue="commentTitle") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpServletRequest req, Model model
			) throws Exception {
		
		String cp = req.getContextPath();
		
		int rows = 5;
		int total_page = 0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "utf-8");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("castCnum", castCnum);
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		dataCount = service.dataCount(map);
		
		if(dataCount != 0) {
			total_page = myUtil.pageCount(rows, dataCount);
		}
		
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page-1) * rows;
		if(offset < 0) offset = 0;
		map.put("offset", offset);
		map.put("rows", rows);
		
		List<Cast> list = service.listCast(map);
		
		int listNum = 0;	
		int num = 0;
		for(Cast dto : list) {
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}
		
		String query = "";
		String listUrl = cp+"/cast/news?castCnum="+castCnum;
		String readUrl = cp+"/cast/read?page=" + current_page;
		if(keyword.length() != 0) {
			query = "condition=" + condition + "&keyword=" + URLEncoder.encode(keyword, "utf-8");
		}
		
		if(query.length() != 0) {
			listUrl = cp + "/cast/news?castCnum="+ castCnum + "&query=" + query;
			readUrl = cp + "/cast/read?castCnum="+castCnum+"&page=" + current_page + "&" +query;
		}
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("readUrl", readUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("paging", paging);
		model.addAttribute("condition", condition);
		model.addAttribute("keyword", keyword);
		model.addAttribute("castCnum", castCnum);
		if(castCnum==1) {			
			return ".castLayout.next";
		}else {
			return ".castLayout.news";
		}
	}
	
	@RequestMapping(value= {"write1", "write2"}, method=RequestMethod.GET)
	public String writeForm(
			Model model, 
			@RequestParam(defaultValue="1") int castCnum
			) throws Exception {
		
		
		model.addAttribute("castCnum", castCnum);
		
		if(castCnum==1) {	
			model.addAttribute("mode", "write1");
			return ".castLayout.write1";
		} else{
			model.addAttribute("mode", "write2");
			return ".castLayout.write2";
		}
	}
	@RequestMapping(value={"write1", "write2"} , method=RequestMethod.POST)
	public String writeSubmit(
			Cast dto, 
			HttpSession session
			) throws Exception {
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		try {
			dto.setMemberId(info.getMemberId());
			service.insertCast(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/cast/news?castCnum="+dto.getCastCnum();
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam int castNum,
			@RequestParam String page,
			HttpSession session, Model model,
			@RequestParam(defaultValue="1") int castCnum
			) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		Cast dto = service.readCast(castNum);
		if(dto == null) {
			return "redirect:/cast/news?castCnum="+ castCnum + "&page=" + page;
		}
		
		if(! info.getMemberId().equals("eydiz")) {
			return "redirect:/cast/news?castCnum="+ castCnum + "&page=" + page;
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("mode", "update");
		model.addAttribute("page", page);
		model.addAttribute("castCnum", castCnum);
		
		if(castCnum==1) {			
			return ".castLayout.write1";
		}else {
			return ".castLayout.write2";
		}
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateSubmit(
			@RequestParam String page,
			HttpSession session, Cast dto,
			@RequestParam(defaultValue="1") int castCnum
			) throws Exception {
		try {
			service.updateCast(dto);
		} catch (Exception e) {
		}
		
		return "redirect:/cast/news?castCnum="+ castCnum + "&page=" + page;
	}
	
	@RequestMapping(value={"read"})
	public String read(
			@RequestParam int castNum,
			@RequestParam String page,
			@RequestParam(defaultValue="1") int castCnum,
			@RequestParam(defaultValue="commentTitle") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model
			) throws Exception {
		keyword = URLDecoder.decode(keyword, "utf-8");
		
		String query = "page="+page+"&castCnum="+castCnum;
		if(keyword.length() != 0) {
			query = "condition=" + condition + "&keyword=" + URLEncoder.encode(keyword, "utf-8");
		}
		
		Cast dto = service.readCast(castNum);
		if(dto==null) {
			return "redirect:/cast/news?"+query;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("castNum", castNum);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		model.addAttribute("castCnum", castCnum);
		
		if(castCnum==1) {			
			return ".castLayout.read1";
		}else {
			return ".castLayout.read2";
		}
		
	}
	
	
	@RequestMapping(value="delete")
	public String delete(
			@RequestParam int castNum,
			@RequestParam String page,
			@RequestParam(defaultValue="1") int castCnum,
			@RequestParam(defaultValue="commentTitle") String condition,
			@RequestParam(defaultValue="") String keyword,
			HttpSession session
			) throws Exception {
		SessionInfo info =(SessionInfo)session.getAttribute("member");
		
		keyword = URLDecoder.decode(keyword, "utf-8");
		
		String query = "page="+page+"&castCnum="+castCnum;
		if(keyword.length() != 0) {
			query = "condition=" + condition + "&keyword=" + URLEncoder.encode(keyword, "utf-8");
		}
		
		service.deleteCast(castNum, info.getMemberId());
		
		return "redirect:/cast/news?"+query;
	}
	
	@RequestMapping(value="insertCastLike", method=RequestMethod.POST)
	@ResponseBody
	public Map<String , Object> insertCastLike(
			@RequestParam int castNum,
			HttpSession session
			) {
		SessionInfo info =(SessionInfo)session.getAttribute("member");
		int castLikeCount=0;
		String state="true";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("castNum", castNum);
		paramMap.put("memberId", info.getMemberId());
		
		try {
			service.insertCastLike(paramMap);
		} catch (Exception e) {
			state="false";
		}
		
		castLikeCount = service.castLikeCount(castNum);
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);
		model.put("castLikeCount", castLikeCount);
		
		return model;
	}
	
	@RequestMapping(value="listComment")
	public String listComment(
			@RequestParam int castNum,
			@RequestParam(value="pageNo", defaultValue="1") int current_page, Model model
			) throws Exception {
		
		int rows = 5;
		int total_page = 0;
		int dataCount = 0;
		
		Map<String , Object> map = new HashMap<>();
		map.put("castNum", castNum);
		
		dataCount = service.commentCount(map);
		total_page = myUtil.pageCount(rows, dataCount);
		
		if(current_page > total_page) {
			current_page = total_page;
		} 
		
		int offset = (current_page-1) * rows;
		if(offset < 0) offset = 0;
        map.put("offset", offset);
        map.put("rows", rows);
        
        List<Comment> listComment = service.listComment(map);
        
        for(Comment dto : listComment) {
        	dto.setCommentContent(dto.getCommentContent().replaceAll("\n", "<br>"));
        }
        
        String paging = myUtil.pagingMethod(current_page, total_page, "listPage");
        
        model.addAttribute("listComment", listComment);
        model.addAttribute("pageNo", current_page);
        model.addAttribute("commentCount", dataCount);
        model.addAttribute("total_page", total_page);
        model.addAttribute("paging", paging);
		
		return "/cast/listComment";
	}
	
	@RequestMapping(value="insertComment", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertComment(
			Comment dto, HttpSession session
			) {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		String state = "true";
		
		try {
			dto.setMemberId(info.getMemberId());
			System.out.println(dto);
			service.insertComment(dto);
		} catch (Exception e) {
			state = "false";
		}
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);
		
		return model;
	}
	
	@RequestMapping(value="deleteComment", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteComment(
			@RequestParam Map<String, Object> paramMap
			) {
		
		String state = "true";
		
		try {
			service.deleteComment(paramMap);
		} catch (Exception e) {
			state = "false";
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("state", state);
		
		return map;
	}
	
}
