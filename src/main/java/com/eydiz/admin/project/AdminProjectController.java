package com.eydiz.admin.project;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eydiz.common.MyUtil;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@Controller("admin.project.adminFundingController")
@RequestMapping(value= {"/admin/project/*"})
public class AdminProjectController {
	
	@Autowired
	private AdminProjectService service;
	
	@Autowired
	private MyUtil myUtil;
	
	
	
	// 프로젝트 리스트
	@RequestMapping(value="list")
	public String projectList(
			@RequestParam(defaultValue="1") int parentCategoryNo,
			@RequestParam(defaultValue="1") int statusNo,
			@RequestParam(required=false, value="page", defaultValue="1") int current_page,
			@RequestParam(defaultValue="projectName") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model,
			HttpServletRequest req
			) throws Exception {
		String cp = req.getContextPath();
		
		int rows = 5;
		int total_page=0;
		int dataCount = 0;
		
		if(req.getMethod().equalsIgnoreCase("GET")) {
			keyword = URLDecoder.decode(keyword, "UTF-8");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("parentCategoryNo", parentCategoryNo);
		map.put("statusNo", statusNo);
		
		dataCount = service.dataCount(map);
		if(dataCount != 0) {
			total_page = myUtil.pageCount(rows, dataCount);
		}
		
		if(total_page < current_page) {
			current_page = total_page;
		}
		
		int offset = (current_page -1) * rows;
		if(offset < 0 ) offset = 0;
		map.put("offset", offset);
		map.put("rows", rows);
		
		List<AdminProject> list = service.adminProjectList(map);
		
		String query = "";
		String listUrl = cp+"/admin/project/list?parentCategoryNo="+parentCategoryNo+"&statusNo="+statusNo;
		String articleUrl = cp+"/admin/project/article?parentCategoryNo="+parentCategoryNo+"&statusNo="+statusNo+"&page=" + current_page;
		
		if(keyword.length() != 0) {
			query = "condition="+condition + "&keyword=" + URLEncoder.encode(keyword, "UTF-8");
		}
		
		
		if(query.length() != 0) {
			listUrl = listUrl+"&"+query;
			articleUrl = articleUrl + "&" + query;
		}
		
		String paging = myUtil.paging(current_page, total_page, listUrl);
		
		model.addAttribute("list", list);
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("page", current_page);
		model.addAttribute("dataCount", dataCount);
		model.addAttribute("total_page", total_page);
		model.addAttribute("paging", paging);
		
		model.addAttribute("condition", condition);
		model.addAttribute("keyword", keyword);
		
		model.addAttribute("parentCategoryNo",parentCategoryNo);
		model.addAttribute("statusNo", statusNo);
		
		return ".adminLayout.project.list";
	}
		
	
	@RequestMapping(value="article", method=RequestMethod.GET)
	public String articleProject(
			@RequestParam int projectNo,
			@RequestParam String page,
			@RequestParam(defaultValue="1") int parentCategoryNo,
			@RequestParam(defaultValue="1") int statusNo,
			@RequestParam(defaultValue="projectName") String condition,
			@RequestParam(defaultValue="") String keyword,
			Model model
			) throws Exception {
		keyword = URLDecoder.decode(keyword, "UTF-8");
		
		String query = "parentCategoryNo="+parentCategoryNo+"&stausNo="+statusNo;
		if(keyword.length() != 0) {
			query += "&condition="+condition+"&keyword="+URLEncoder.encode(keyword, "UTF-8");
		}
		
		Project project = service.readProject(projectNo);
		List<Reward> rewards = service.listRewards(projectNo);
		
		if(project == null) {
			return "redirect:/admin/project/list?"+query;
		}
		
		
		project = service.readProject(projectNo);
		rewards = service.listRewards(projectNo);
		
		model.addAttribute("project", project);
		model.addAttribute("rewards", rewards);
		
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		model.addAttribute("projectNo", projectNo);
		model.addAttribute("parentCategoryNo", parentCategoryNo);
		model.addAttribute("statusNo", statusNo);
				
		return ".adminProjectLayout.article";
	}
	
	// 프로젝트 승인
	@RequestMapping(value="submit", method=RequestMethod.POST)
	public String projectSubmit(
			@RequestParam int projectNo,
			@RequestParam String page,
			@RequestParam String status,
			@RequestParam(defaultValue="1") int parentCategoryNo,
			AdminProject dto
			) throws Exception {
		
		System.out.println(dto.getStatusNo()+","+dto.getStatusMemo());
		Map<String, Object> map = new HashMap<>();
		map.put("projectNo", projectNo);
		map.put("statusNo", dto.getStatusNo());
		
		dto.setProjectNo(projectNo);
		
		
		try {
			service.updateProject(map);				// 프로젝트 상태번호 업뎃
			service.insertProjectStatusList(dto);	// 로그찍는거
		} catch (Exception e) {
		}
		
		return "redirect:/admin/project/list?parentCategoryNo="+parentCategoryNo+"&statusNo="+status+"&page="+page;
	}
	
	
	// 반려
	@RequestMapping(value="reject", method=RequestMethod.POST)
	public String projectReject(
			@RequestParam int projectNo,
			@RequestParam String page,
			@RequestParam String status,
			@RequestParam(defaultValue="1") int parentCategoryNo,
			AdminProject dto
			) throws Exception {
		
		System.out.println(dto.getStatusNo()+","+dto.getStatusMemo());
		Map<String, Object> map = new HashMap<>();
		map.put("projectNo", projectNo);
		map.put("statusNo", dto.getStatusNo());
		
		dto.setProjectNo(projectNo);
		
		
		try {
			service.updateProject(map);				// 프로젝트 상태번호 업뎃
			service.insertProjectStatusList(dto);	// 로그찍는거
		} catch (Exception e) {
		}
		
		return "redirect:/admin/project/list?parentCategoryNo="+parentCategoryNo+"&statusNo="+status+"&page="+page;
	}
	
}
