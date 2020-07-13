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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eydiz.common.Constant;
import com.eydiz.common.Pager;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Project;
import com.eydiz.studio.ProjectCommunity;
import com.eydiz.studio.ProjectNews;
import com.eydiz.studio.Reward;
import com.eydiz.studio.StudioService;

@Controller("detail.detailController")
@RequestMapping("/detail/*")
public class DetailController implements Constant, DetailConstant, MemberConstant {

	@Autowired
	DetailService service;

	@Autowired
	StudioService studioService;

	@Autowired
	Pager pager;

	private String getRealURI(String requestURI, String contextPath, int projectNo) {
		return requestURI.substring(contextPath.length() + REQUEST_MAPPING.length() + (projectNo + "/").length());
	}

	private void addModelURIAttribute(Model model, HttpServletRequest req, Integer projectNo) {
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		if (req.getQueryString() != null && req.getQueryString().length() > 0) {
			uri.append("?" + req.getQueryString());
		}
		model.addAttribute(MemberConstant.ATTRIBUTE_URI, getRealURI(uri.toString(), req.getContextPath(), projectNo));
	}

	@RequestMapping(value = "/")
	public String main() {
		return "redirect:/";
	}

	@RequestMapping(value = { "/{projectNo}", "/{projectNo}/view" })
	public String detailProject(@PathVariable Integer projectNo, Model model, HttpSession session,
			HttpServletRequest req) {
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
				param.put(MemberConstant.ATTRIBUTE_MEMBERNO, info.getMemberNo());
			}
			param.put(ATTRIBUTE_PROJECTNO, projectNo);
			project = service.readProject(param);
			if (project == null) {
				throw new Exception("프로젝트를 찾을 수 없음");
			}
			rewards = service.listRewards(projectNo);
			popularProject = service.listPopularProject();
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD, rewards);
			model.addAttribute(ATTRIBUTE_POPULAR_PROJECT, popularProject);

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/" + API_MAIN;
		}
		return ".detailLayout";
	}

	// 프로젝트 좋아요 AJAX
	@RequestMapping(value = { "/{projectNo}/like" })
	@ResponseBody
	public Map<String, Object> likeProject(@PathVariable Integer projectNo, Model model, HttpSession session,
			HttpServletRequest req) {
		Map<String, Object> result = new HashMap<>();
		try {
			addModelURIAttribute(model, req, projectNo);
			if (projectNo == null) {
				throw new Exception("There is no projectNo");
			}
			// 좋아요 시도하기
			Map<String, Object> map = new HashMap<>();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			map.put(MemberConstant.ATTRIBUTE_MEMBERNO, info.getMemberNo());
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

	@RequestMapping(value = "/{projectNo}/community", method = RequestMethod.GET)
	public String community(@PathVariable Integer projectNo, HttpSession session, Model model, HttpServletRequest req)
			throws Exception {
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
				param.put(MemberConstant.ATTRIBUTE_MEMBERNO, info.getMemberNo());
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

	// 댓글 보기
	@RequestMapping(value = { "/{projectNo}/community/view", "/{projectNo}/community/view/{page}" })
	@ResponseBody
	public Map<String, Object> communityCommentView(@PathVariable Integer projectNo,
			@PathVariable(required=false) Integer page, Integer parentCommentNo) {
		// 로그인 필요없음
		Map<String, Object> map = new HashMap<>();
		List<ProjectCommunity> list = null;
		if(page==null) {
			page = 1;
		}
		try {
			if (projectNo == null) {
				throw new NullPointerException();
			}
			if (parentCommentNo == null) {// 댓글
				final int rows = 10;//한 번에 보내 줄 댓글 개수
				int dataCount = service.countCommunityComments(projectNo);
				int offset = pager.getOffset(page, rows);
				list = service.listCommunityComments(projectNo, offset, rows);
				map.put(ATTRIBUTE_PAGE_COUNT, pager.pageCount(rows, dataCount));
			} else {// 답글
				list = service.listCommunityComments(projectNo, parentCommentNo);
			}
			map.put(JSON_RESULT, JSON_RESULT_OK);
			map.put(ATTRIBUTE_COMMUNITY_COMMENTS, list);
		} catch (Exception e) {
			map.clear();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}

	// 댓글 쓰기
	@RequestMapping(value = "/{projectNo}/community/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> communityCommentCreate(@PathVariable Integer projectNo, ProjectCommunity dto,
			HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			// commentNo, parentCommentNo(답글인 경우), projectNo, memberId, content, createdDate
			// 폼에서는 댓글이면 content만, 답글이면 content와 parentCommentNo만 받아 올 것임!
			if (projectNo == null) {
				throw new NullPointerException();
			}
			dto.setProjectNo(projectNo);
			dto.setCommentNo(service.getNewCommentNo());
			System.out.println(dto.getCommentNo()+"는???");
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			dto.setMemberId(info.getMemberId());
			service.insertCommunityComment(dto);
			map.put(JSON_RESULT, JSON_RESULT_OK);
			ProjectCommunity comment = service.readCommunity(dto.getCommentNo());
			map.put(ATTRIBUTE_COMMUNITY_COMMENT, comment);
		} catch (Exception e) {
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping(value = "/{projectNo}/community/edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> communityCommentEdit(@PathVariable Integer projectNo, ProjectCommunity dto,
			HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 댓글 수정하기
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			if (info == null) {
				throw new Exception("Login required");
			}
			dto.setMemberId(info.getMemberId());
			service.updateCommunityComment(dto);
			map.put(JSON_RESULT, JSON_RESULT_OK);
			map.put(ATTRIBUTE_COMMUNITY_COMMENTS, dto);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}

	// 댓글 삭제
	@RequestMapping(value = "/{projectNo}/community/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> communityCommentDelete(ProjectCommunity dto, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		try {
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			dto.setMemberId(info.getMemberId());
			service.deleteCommunityComment(dto);
			map.put(JSON_RESULT, JSON_RESULT_OK);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/{projectNo}/news")
	public String news(@PathVariable Integer projectNo, HttpSession session, Model model, HttpServletRequest req)
			throws Exception {
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
				param.put(MemberConstant.ATTRIBUTE_MEMBERNO, info.getMemberNo());
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

	@RequestMapping(value = "/{projectNo}/news/view")
	@ResponseBody
	public Map<String, Object> newsJson(@PathVariable Integer projectNo,
			@RequestParam(value = "page", defaultValue = "1") int currentPage) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		try {
			if (projectNo == null) {
				throw new NullPointerException();
			}
			final int rows = 1;
			int offset = pager.getOffset(currentPage, rows);
			param.put(ATTRIBUTE_PROJECTNO, projectNo);
			param.put(DetailConstant.ATTRIBUTE_OFFSET, offset);
			param.put(DetailConstant.ATTRIBUTE_ROWS, rows);
			int dataCount = studioService.dataCount(param);
			System.out.println(dataCount);
			int pageCount = pager.pageCount(rows, dataCount);
			List<ProjectNews> list = studioService.listProjectNews(param);

			map.put(JSON_RESULT, JSON_RESULT_OK);
			map.put(JSON_PROJECT_NEWS, list);
			map.put(JSON_PROJECT_PAGE_COUNT, pageCount);

		} catch (Exception e) {
			e.printStackTrace();
			map.put(JSON_RESULT, JSON_RESULT_ERROR);
			map.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return map;
	}
}
