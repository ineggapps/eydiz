package com.eydiz.story;

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

@Controller("story.storyController")
@RequestMapping("/story/*")
public class StoryController {
	@Autowired
	private StoryService service;
	
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="story")
	public String list(
			@RequestParam(value="pageNo", defaultValue="1") int current_page, Model model, HttpServletRequest req) throws Exception {
		String cp = req.getContextPath();
		
		int rows=3;
		int dataCount = service.dataCount();
		int total_page = myUtil.pageCount(rows, dataCount);
		if(current_page > total_page) {
			current_page = total_page;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		int offset = (current_page-1)*rows;
		if(offset < 0) {
			offset = 0;
		}
		
		map.put("offset", offset);
		map.put("rows", rows);
		
		int listNum = 0;
		int num = 0;
		
		List<Story> list = service.listStory(map);
		for(Story dto : list) {
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}
		
		String paging = myUtil.pagingMethod(current_page, total_page, "listPage");
		
		model.addAttribute("total_page", total_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("pageNo", current_page);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		
		return ".storyLayout.story";
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertSubmit(Story dto, HttpSession session) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		Map<String, Object> model = new HashMap<>();
		
		try {
			dto.setMemberId(info.getMemberId());
			service.insertStroy(dto);
			model.put("state", "true");
		} catch (Exception e) {
			model.put("state", "false");
		}
		
		return model;
	}
	
}
