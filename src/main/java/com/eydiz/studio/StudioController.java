package com.eydiz.studio;

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
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;

@Controller("studio.studioController")
@RequestMapping("/studio/*")
public class StudioController implements Constant, StudioConstant, MemberConstant {

	private Logger logger = LoggerFactory.getLogger(StudioController.class);

	@Autowired
	StudioService service;

	private String getRealURI(String requestURI, String contextPath) {
		return requestURI.substring(contextPath.length() + REQUEST_MAPPING.length());
	}

	private void addModelURIAttribute(Model model, HttpServletRequest req) {
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		if (req.getQueryString() != null && req.getQueryString().length() > 0) {
			uri.append("?" + req.getQueryString());
		}
		model.addAttribute(ATTRIBUTE_URI, getRealURI(uri.toString(), req.getContextPath()));
	}

	////////////////////////////////////////////// 브랜드
	@RequestMapping(value = "/brand/info", method = RequestMethod.GET)
	public String getBrandInfo(Model model, HttpServletRequest req, HttpSession session) {
		String viewPath = VIEW_BRAND_INFO_PRIMAL;
		try {
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
//			int myBrandCount = service.myBrandCount(info.getMemberNo());
//			if(myBrandCount>0) {
//				viewPath = VIEW_BRAND_INFO_UPDATE;
//			}
			// brand가 회원당 1개일 때 기준으로...
			Brand dto = service.readBrand(info.getMemberNo());
			if (dto != null && dto.getBrandNo() > 0) {
				viewPath = VIEW_BRAND_INFO_UPDATE;
				model.addAttribute(ATTRIBUTE_BRAND, dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addModelURIAttribute(model, req);
		return viewPath;// without tiles(단독 레이아웃 뷰)
	}

	@RequestMapping(value = "/brand/info", method = RequestMethod.POST)
	public String postBrandInfo(Brand dto, Model model, HttpServletRequest req, HttpSession session) {
		String redirectUrl = API_PROJECT_LIST;
		try {
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			dto.setMemberNo(info.getMemberNo());
			service.updateBrand(dto);
		} catch (Exception e) {
			e.printStackTrace();
			redirectUrl = API_BRAND_INFO;
		}
		return "redirect:" + redirectUrl;
	}

	////////////////////////////////////////////// 프로젝트
	@RequestMapping(value = { "/project/list/{categoryName}", "/project/list" })
	public String list(@PathVariable(required = false) String categoryName, Model model, HttpServletRequest req) {
		addModelURIAttribute(model, req);
		model.addAttribute(ATTRIBUTE_CATEGORY, categoryName);
		return VIEW_PROJECT_LIST;
	}

	@RequestMapping(value = { "/project/register", "/project/register/{projectNo}" }, method = RequestMethod.GET)
	public String registerForm(@PathVariable(required = false) Integer projectNo, Model model, HttpServletRequest req,
			HttpSession session) {
		try {
			BrandSessionInfo info = (BrandSessionInfo) session.getAttribute(SESSION_BRAND);
			if (projectNo == null) {
				// 새로 프로젝트 만들기
				Project project = new Project();
				project.setBrandNo(info.getBrandNo());
				projectNo = service.insertEmptyProject(project);
				return "redirect:" + API_PROJECT_REGISTER + "/" + projectNo;
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
		addModelURIAttribute(model, req);
		return VIEW_PROJECT_REGISTER;
	}

	@RequestMapping(value = "/project/{projectNo}/register", method = RequestMethod.POST)
	public String updateProject(@PathVariable Integer projectNo, Project project) {
		try {
			if (projectNo == null) {
				throw new Exception("프로젝트 번호가 전달되지 않아 프로젝트를 저장할 수 없음");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:" + API_PROJECT_LIST;
	}

	private String getRealPath(HttpSession session) {
		// "/"문자열 다음부터 추출해야...
		String cpRealPath = session.getServletContext().getRealPath("/");
		String upload = File.separator + FOLDER_UPLOADS_ROOT + File.separator + FOLDER_UPLOADS_PROJECT;
		return cpRealPath + upload;
	}

	// 이미지 업로드
	@RequestMapping(value = "/project/{projectNo}/register/upload/image", method = RequestMethod.POST)
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
	@RequestMapping(value = "/project/{projectNo}/register/delete/image", method = RequestMethod.POST)
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
	@RequestMapping(value = "/project/{projectNo}/hashtag/insert/{keyword}", method = RequestMethod.POST)
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

	@RequestMapping(value = "/project/{projectNo}/hashtag/view", method = RequestMethod.GET)
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

	@RequestMapping(value = "/project/{projectNo}/hashtag/delete/{keyword}", method = RequestMethod.POST)
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
}
