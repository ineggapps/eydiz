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
			@RequestParam(value="pageNo", defaultValue="1") int current_page, 
			@RequestParam(defaultValue="1") int storyCnum,
			Model model, HttpServletRequest req) throws Exception {		
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
		map.put("storyCnum", storyCnum);
		
		int listNum = 0;
		int num = 0;
		
		List<Story> list = service.listStory(map);
		for(Story dto : list) {
			dto.setStoryContent(dto.getStoryContent().replaceAll("\n", "<br>"));
			
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}
		
		String cp=req.getContextPath();
		String listUrl = cp+"/story/story?storyCnum="+storyCnum;
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("total_page", total_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("pageNo", current_page);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		model.addAttribute("storyCnum", storyCnum);
		
		return ".storyLayout.story";
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String insertSubmit(Story dto, HttpSession session) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		try {
			dto.setMemberId(info.getMemberId());
			service.insertStory(dto);

		} catch (Exception e) {
		}
		
		return "redirect:/story/story?storyCnum="+dto.getStoryCnum();
	}
	
	@RequestMapping(value="insertStoryLike", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertStoryLike(
			@RequestParam int storyNum, HttpSession session) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		int storyLikeCount=0;
		String state="true";
		
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("storyNum", storyNum);
		paramMap.put("memberId", info.getMemberId());
		
		try {
			service.insertStoryLike(paramMap);
		} catch (Exception e) {
			state="false";
		}
		
		storyLikeCount = service.storyLikeCount(storyNum);
		
		Map<String, Object> model = new HashMap<>();
		model.put("state", state);
		model.put("storyLikeCount", storyLikeCount);
		
		return model;
	}
	
	@RequestMapping(value="delete")
	public String deleteStory(
			Story dto, @RequestParam int storyNum, HttpSession session
			) throws Exception {
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		service.deleteStory(storyNum, info.getMemberId());
		
		return "redirect:/story/story?storyCnum="+dto.getStoryCnum();
	}
}
