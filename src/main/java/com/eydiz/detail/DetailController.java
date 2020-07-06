package com.eydiz.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.Constant;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@Controller("detail.detailController")
@RequestMapping("/detail/*")
public class DetailController implements Constant, DetailConstant, MemberConstant {

	@Autowired
	DetailService service;
	
	private String getRealURI(String requestURI, String contextPath, int projectNo) {
		return requestURI.substring(contextPath.length() + REQUEST_MAPPING.length()+ (projectNo+"/").length());
	}
	
	private void addModelURIAttribute(Model model, HttpServletRequest req, Integer projectNo) {
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		if (req.getQueryString() != null && req.getQueryString().length() > 0) {
			uri.append("?" + req.getQueryString());
		}
		model.addAttribute(ATTRIBUTE_URI, getRealURI(uri.toString(), req.getContextPath(), projectNo));
	}
	
	@RequestMapping(value = "/")
	public String main() {
		return "redirect:/";
	}

	@RequestMapping(value = { "/{projectNo}", "/{projectNo}/view" })
	public String detailProject(@PathVariable Integer projectNo, Model model, HttpSession session, HttpServletRequest req) {
		Project project = null;
		List<Reward> rewards = null;
		List<Project> popularProject = null;
		try {
			addModelURIAttribute(model, req, projectNo);
			if (projectNo == null) {
				throw new Exception("프로젝트 번호를 찾을 수 없음");
			}
			Map<String, Object> param = new HashMap<>();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			if (info != null) {
				param.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			}
			param.put(ATTRIBUTE_PROJECTNO, projectNo);
			project = service.readProject(param);
			rewards = service.listRewards(projectNo);
			popularProject = service.listPopularProject();
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD, rewards);
			model.addAttribute(ATTRIBUTE_POPULAR_PROJECT, popularProject);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		return ".detailLayout";
	}

	// 프로젝트 좋아요 AJAX
	@RequestMapping(value = { "/{projectNo}/like" })
	@ResponseBody
	public Map<String, Object> likeProject(@PathVariable Integer projectNo, Model model, HttpSession session, HttpServletRequest req) {
		Map<String, Object> result = new HashMap<>();
		try {
			addModelURIAttribute(model, req, projectNo);
			if (projectNo == null) {
				throw new Exception("There is no projectNo");
			}
			// 좋아요 시도하기
			Map<String, Object> map = new HashMap<>();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			map.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			map.put(ATTRIBUTE_PROJECTNO, projectNo);
			result.put(JSON_RESULT, JSON_RESULT_OK);
			boolean echo = service.toggleLike(map);
			result.put(JSON_PROJECT_LIKE_RESULT, echo);
		} catch (Exception e) {
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value="/{projectNo}/community")
	public String community(@PathVariable Integer projectNo, HttpSession session, Model model, HttpServletRequest req) throws Exception {
		Project project = null;
		List<Reward> rewards = null;
		List<Project> popularProject = null;
		try {
			addModelURIAttribute(model, req, projectNo);			
			if (projectNo == null) {
				throw new Exception("프로젝트 번호를 찾을 수 없음");
			}
			Map<String, Object> param = new HashMap<>();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			if (info != null) {
				param.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			}
			param.put(ATTRIBUTE_PROJECTNO, projectNo);
			project = service.readProject(param);
			rewards = service.listRewards(projectNo);
			popularProject = service.listPopularProject();
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD, rewards);
			model.addAttribute(ATTRIBUTE_POPULAR_PROJECT, popularProject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ".detailLayout.community";
	}
	
	@RequestMapping(value="/{projectNo}/news")
	public String news(@PathVariable Integer projectNo, HttpSession session, Model model, HttpServletRequest req) throws Exception{
		Project project = null;
		List<Reward> rewards = null;
		List<Project> popularProject = null;
		try {
			addModelURIAttribute(model, req, projectNo);
			if (projectNo == null) {
				throw new Exception("프로젝트 번호를 찾을 수 없음");
			}
			Map<String, Object> param = new HashMap<>();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			if (info != null) {
				param.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			}
			param.put(ATTRIBUTE_PROJECTNO, projectNo);
			project = service.readProject(param);
			rewards = service.listRewards(projectNo);
			popularProject = service.listPopularProject();
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD, rewards);
			model.addAttribute(ATTRIBUTE_POPULAR_PROJECT, popularProject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ".detailLayout.news";
	}
}
