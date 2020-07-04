package com.eydiz.mate;

import java.io.File;
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

@Controller("mate.Controller")
@RequestMapping("/mate/*")
public class MateController implements MemberConstant {
	@Autowired
	MateService service;
	@Autowired
	private MyUtil myUtil;
	
	@RequestMapping(value="main")
	public String main() {
		return ".mateLayout.main";
	}
	
	@RequestMapping(value="list")
	public String list (
			@RequestParam(value="pageNo", defaultValue="1") int current_page,
			@RequestParam(defaultValue="10") int fmcaNo,
			HttpServletRequest req,
			Model model
			) throws Exception {
		
		int rows = 6;
		int total_page;
		int dataCount;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fmcaNo", fmcaNo);
		
		dataCount = service.dataCount(map);
		total_page = myUtil.pageCount(rows, dataCount);
			
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page-1)*rows;
		if(offset < 0) {
			offset = 0;
		}
		map.put("offset", offset);
		map.put("rows", rows);
		
		List<Mate> list = service.listMate(map);
		
		int listNum, num = 0;
		for(Mate dto : list) {
			listNum = dataCount - (offset + num);
			dto.setListNum(listNum);
			num++;
		}
		
		String paging  = myUtil.paging(current_page, total_page, "listPage");
		
		model.addAttribute("list", list);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("pageNo", current_page);
		model.addAttribute("paging", paging);
		
		return "mate/list";
	}
	
	@RequestMapping(value="created", method=RequestMethod.GET)
	public String createdForm(Model model) throws Exception {
		model.addAttribute("mode", "created");
		model.addAttribute("pageNo", "1");
		return ".mateLayout.created";
	}
	
	@RequestMapping(value="created", method=RequestMethod.POST)
	public String createdSubmit(
			Mate dto,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String path = root+"uploads"+File.separator+"mate";
		
		SessionInfo info = (SessionInfo)session.getAttribute(SESSION_MEMBER);
		try {
			dto.setMemberNo(info.getMemberNo());
			service.insertMate(dto, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/mate/main";
	}
	
	@RequestMapping(value="article", method=RequestMethod.GET)
	public String article(
			@RequestParam int fmNo,
			@RequestParam String pageNo,
			@RequestParam(defaultValue="1") int fmcaNo,
			Model model
			) throws Exception {
		
		Mate dto = service.readMate(fmNo);
		if(dto==null) {
			return "mate/error";
		}
		
		dto.setFmContent(dto.getFmContent().replaceAll("\n", "<br>"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("fmcaNo", fmcaNo);
		map.put("fmNo", fmNo);
		
		model.addAttribute("dto", dto);
		model.addAttribute("pageNo", pageNo);
		
		return ".mateLayout.content";
	}
	
	@RequestMapping(value="update", method=RequestMethod.GET)
	public String updateForm(
			@RequestParam int fmNo,
			@RequestParam int fmcaNo,
			@RequestParam String pageNo,
			HttpSession session,
			Model model
			) throws Exception {
		
		Mate dto = service.readMate(fmNo);
		if(dto == null) {
			return "redirect:/mate/main?pageNo="+pageNo;
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("page", pageNo);
		model.addAttribute("mode", "update");
		
		return ".mateLayout.created";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateSubmit(
			Mate dto,
			@RequestParam String pageNo,
			HttpSession session
			) throws Exception {
		String root = session.getServletContext().getRealPath("/");
		String pathname=root+"uploads"+File.separator+"mate";
		
		try {
			service.updateMate(dto, pathname);
		} catch (Exception e) {
		}
		
		
		return "redirect:/mate/article?fmNo="+dto.getFmNo()+"&pageNo"+pageNo;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String delete (
			@RequestParam int fmNo,
			HttpSession session
			) throws Exception {
		
		String root = session.getServletContext().getRealPath("/");
		String pathname=root+"uploads"+File.separator+"mate";
		
		SessionInfo info = (SessionInfo)session.getAttribute("member");
		
		try {
			service.deleteMate(fmNo, pathname, info.getMemberNo());
		} catch (Exception e) {
		}
		
		return "redirect:/mate/main";
	}
	
	@RequestMapping(value="insertMateProject", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMateProject(
			@RequestParam int fmNo,
			HttpSession session
			) throws Exception {
		String state="true";
		int mateProjectCount=0;
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		Map<String, Object> model = new HashMap<>();
		Mate dto = service.readMate(fmNo);
		if(dto.getMateProjectCount()>=dto.getFmAllNumber()) {
			mateProjectCount = service.mateProjectCount(fmNo);
			model.put("state", "over");
			model.put("mateProjectCount", mateProjectCount);
			return model;
		}
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fmNo", fmNo);
		paramMap.put("memberNo", info.getMemberNo());
		
		try {
			service.insertMateProject(paramMap);
		} catch (Exception e) {
			state="false";
		}
		
		mateProjectCount = service.mateProjectCount(fmNo);
		

		model.put("state", state);
		model.put("mateProjectCount", mateProjectCount);
		
		return model;
	}
	
}
