package com.eydiz.studio.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.Constant;
import com.eydiz.common.Pager;
import com.eydiz.member.MemberConstant;
import com.eydiz.studio.BrandSessionInfo;
import com.eydiz.studio.Project;
import com.eydiz.studio.ProjectCategory;
import com.eydiz.studio.ProjectHashtag;
import com.eydiz.studio.ProjectImage;
import com.eydiz.studio.Reward;
import com.eydiz.studio.StudioConstant;
import com.eydiz.studio.StudioService;

@Controller("studio.controller.studioProjectController")
@RequestMapping("/studio/project/*")
public class StudioProjectController implements Constant, StudioConstant, MemberConstant {

	private Logger logger = LoggerFactory.getLogger(StudioProjectController.class);
	private final static int ROWS = 10;

	@Autowired
	StudioService service;

	@Autowired
	Pager pager;

	private String getRealURI(String requestURI, String contextPath) {
		return requestURI.substring(contextPath.length() + REQUEST_MAPPING.length());
	}

	private void addModelURIAttribute(Model model, HttpServletRequest req, Integer projectNo) {
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		if (req.getQueryString() != null && req.getQueryString().length() > 0) {
			uri.append("?" + req.getQueryString());
		}
		model.addAttribute(ATTRIBUTE_URI, getRealURI(uri.toString(), req.getContextPath()));
		model.addAttribute(ATTRIBUTE_PROJECTNO, projectNo);
	}
	
	private String getRealPath(HttpSession session) {
		// "/"문자열 다음부터 추출해야...
		String cpRealPath = session.getServletContext().getRealPath("/");
		String upload = File.separator + FOLDER_UPLOADS_ROOT + File.separator + FOLDER_UPLOADS_PROJECT;
		return cpRealPath + upload;
	}

	////////////////////////////////////////////// 프로젝트
	@RequestMapping(value = { "/list/{categoryName}", "/list/{categoryName}/page/{page}", "/list",
			"/list/page/{page}" })
	public String list(@PathVariable(required = false) String categoryName,
			@PathVariable(required = false) Integer page, Model model, HttpServletRequest req, HttpSession session) {
		addModelURIAttribute(model, req, null);
		int currentPage = 1;
		if (page != null) {
			currentPage = page;
		}
		BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
		int brandNo = bInfo.getBrandNo();
		// 페이징 정보 계산과 해당하는 페이지의 프로젝트 불러오기
		int listProjectCount = service.listProjectCount(brandNo);
		int pageCount = pager.pageCount(ROWS, listProjectCount);
		int offset = pager.getOffset(currentPage, ROWS);
		Map<String, Object> map = new HashMap<>();
		map.put(ATTRIBUTE_ROWS, ROWS);
		map.put(ATTRIBUTE_OFFSET, offset);
		map.put(ATTRIBUTE_BRANDNO, brandNo);
		List<Project> listProject = service.listProject(map);
		// 페이징 정보 입력
		model.addAttribute(ATTRIBUTE_CURRENT_PAGE, currentPage);
		model.addAttribute(ATTRIBUTE_PAGE_COUNT, pageCount);
		model.addAttribute(ATTRIBUTE_CATEGORY, categoryName);
		model.addAttribute(ATTRIBUTE_PROJECT, listProject);
		return VIEW_PROJECT_LIST;
	}

	// 프로젝트 조회/편집
	@RequestMapping(value = { "/register", "/{projectNo}/register" }, method = RequestMethod.GET)
	public String registerForm(@PathVariable(required = false) Integer projectNo, Model model, HttpServletRequest req,
			HttpSession session) {
		try {
			BrandSessionInfo info = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			if (projectNo == null) {
				// 새로 프로젝트 만들기
				Project project = new Project();
				project.setBrandNo(info.getBrandNo());
				projectNo = service.insertEmptyProject(project);
				return "redirect:" + String.format(API_PROJECT_REGISTER, projectNo);
			}
			////////////// 프로젝트 정보 불러오기
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			Project project = service.readProject(projectNo, bInfo.getBrandNo());
			if (project == null) {
				// 자신의 브랜드의 프로젝트가 아니면 null을 반환함
				return "redirect:" + API_PROJECT_LIST;
			}
			List<ProjectCategory> category = service.listCategory();
			model.addAttribute(ATTRIBUTE_CATEGORY, category);
			model.addAttribute(ATTRIBUTE_PROJECT, project);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		addModelURIAttribute(model, req, projectNo);
		return VIEW_PROJECT_REGISTER;
	}

	// 프로젝트 저장하기
	@RequestMapping(value = "/{projectNo}/register", method = RequestMethod.POST)
	public String updateProject(@PathVariable Integer projectNo, Project project, HttpSession session) {
		try {
			if (projectNo == null) {
				throw new Exception("프로젝트 번호가 전달되지 않아 프로젝트를 저장할 수 없음");
			}
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			project.setProjectNo(projectNo);
			project.setBrandNo(bInfo.getBrandNo());
			service.updateProjectBasic(project);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:" + API_PROJECT_LIST;
	}

	// 이미지 업로드
	@RequestMapping(value = "/{projectNo}/register/upload/image", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@PathVariable Integer projectNo, ProjectImage uploadImage, HttpSession session) {
		JSONObject json = new JSONObject();
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			Project project = service.readProject(projectNo, bInfo.getBrandNo());
			String realPath = getRealPath(session);
			String cp = session.getServletContext().getContextPath();
			String uriPath = cp + "/" + FOLDER_UPLOADS_ROOT + "/" + FOLDER_UPLOADS_PROJECT;
			String saveFilename = service.uploadProjectImage(project, uploadImage.getUploadImage(), realPath, uriPath);
			if (saveFilename != null) {
				json.put(JSON_RESULT, JSON_RESULT_OK);
				json.put(JSON_IMAGE_URL, saveFilename);
			} else {
				throw new Exception("이미지 업로드 실패");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			json.put(JSON_RESULT, JSON_RESULT_ERROR);
			json.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return json.toString();
	}

	// 이미지 삭제
	@RequestMapping(value = "/{projectNo}/register/delete/image", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteImage(@PathVariable Integer projectNo, HttpSession session) {
		Map<String, String> map = new HashMap<>();
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			Project project = service.readProject(projectNo, bInfo.getBrandNo());
			service.deleteProjectImage(project, getRealPath(session));
			map.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	/// 해시태그
	@RequestMapping(value = "/{projectNo}/hashtag/insert/{keyword}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertHashtag(@PathVariable Integer projectNo, @PathVariable String keyword) {
		Map<String, Object> result = new HashMap<>();
		try {
			if (keyword == null) {
				throw new Exception("태그가 입력되지 않았습니다.");
			}
			ProjectHashtag hashtag = new ProjectHashtag(projectNo, keyword);
			service.insertHashtag(hashtag);
			result.put(JSON_RESULT, JSON_RESULT_OK);
			result.put(JSON_HASHTAG, hashtag);
		} catch (Exception e) {
			e.printStackTrace();
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{projectNo}/hashtag/view", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> readHashtag(@PathVariable Integer projectNo) {
		Map<String, Object> result = new HashMap<>();
		try {
			List<ProjectHashtag> list = service.readHashtag(projectNo);
			result.put(JSON_RESULT, JSON_RESULT_OK);
			result.put(JSON_HASHTAG, list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{projectNo}/hashtag/delete/{keyword}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteHashtag(@PathVariable Integer projectNo, @PathVariable String keyword) {
		Map<String, Object> result = new HashMap<>();
		try {
			ProjectHashtag tag = new ProjectHashtag(projectNo, keyword);
			service.deleteHashtag(tag);
			result.put(JSON_RESULT, JSON_RESULT_OK);
			result.put(JSON_HASHTAG, tag);
		} catch (Exception e) {
			e.printStackTrace();
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}

	/// 리워드 설계
	@RequestMapping(value = "/{projectNo}/reward")
	public String rewardForm(@PathVariable Integer projectNo, Model model, HttpServletRequest req,
			HttpSession session) {
		addModelURIAttribute(model, req, projectNo);
		BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
		Project project = service.readProject(projectNo, bInfo.getBrandNo());
		model.addAttribute(ATTRIBUTE_BRANDNO, bInfo.getBrandNo());
		model.addAttribute(ATTRIBUTE_PROJECT, project);
		return VIEW_PROJECT_REWARD;
	}

	// 리워드 설계
	@RequestMapping(value = "/{projectNo}/reward/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rewardPost(@PathVariable Integer projectNo, Reward reward) {
		Map<String, Object> result = new HashMap<>();
		try {
			if (projectNo == null) {
				throw new Exception("projectNo is null");
			}
			reward.setProjectNo(projectNo);
			System.out.println(reward.toString());
			service.insertReward(reward);
			result.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}

	// 리워드 조회
	@RequestMapping(value = "/{projectNo}/reward/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> rewardList(@PathVariable Integer projectNo, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		List<Reward> rewards = null;
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			rewards = service.selectReward(projectNo, bInfo.getBrandNo());
			result.put(JSON_RESULT, JSON_RESULT_OK);
			result.put(JSON_REWARD_LIST, rewards);
		} catch (Exception e) {
			e.printStackTrace();
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}

	// 리워드 수정
	@RequestMapping(value = "/{projectNo}/reward/{rewardNo}/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateReward(@PathVariable Integer projectNo, @PathVariable Integer rewardNo,
			Reward reward, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			reward.setProjectNo(projectNo);
			reward.setBrandNo(bInfo.getBrandNo());
			service.updateReward(reward);
			result.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}

	// 리워드 삭제
	@RequestMapping(value = "/{projectNo}/reward/{rewardNo}/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteReward(@PathVariable Integer projectNo, @PathVariable Integer rewardNo,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			service.deleteReward(rewardNo, projectNo, bInfo.getBrandNo());
			result.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}
	
	//스토리
	// 프로젝트 조회/편집
	@RequestMapping(value = {"/{projectNo}/story" }, method = RequestMethod.GET)
	public String storyForm(@PathVariable(required = false) Integer projectNo, Model model, HttpServletRequest req,
			HttpSession session) {
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			Project project = service.readProject(projectNo, bInfo.getBrandNo());
			model.addAttribute(ATTRIBUTE_PROJECT, project);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		addModelURIAttribute(model, req, projectNo);
		return VIEW_PROJECT_STORY;
	}
	
	//스토리 변경
	@RequestMapping(value= {"/{projectNo}/story"}, method=RequestMethod.POST)
	public String storySubmit(@PathVariable(required=false) Integer projectNo, Project project, Model model, HttpSession session) {
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			project.setProjectNo(projectNo);
			project.setBrandNo(bInfo.getBrandNo());
			service.updateStory(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + String.format(API_PROJECT_STORY, projectNo);
	}
	
	//프로젝트 삭제
	@RequestMapping(value="/{projectNo}/delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteProject(@PathVariable(required=false) Integer projectNo, HttpSession session){
		Map<String, Object> map = new HashMap<>();
		try {
			BrandSessionInfo bInfo = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			service.deleteProject(projectNo, bInfo.getBrandNo());
			map.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}
}
