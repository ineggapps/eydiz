package com.eydiz.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class DetailController implements Constant,  DetailConstant, MemberConstant{

	@Autowired
	DetailService service;
	
	@RequestMapping(value="/")
	public String main() {
		return "redirect:/";
	}
	
	@RequestMapping(value= {"/{projectNo}"})
	public String detailProject(@PathVariable Integer projectNo, Model model, HttpSession session) {
		Project project = null;
		List<Reward> rewards = null;
		List<Project> popularProject = null;
		try {
			if(projectNo==null) {
				throw new Exception("프로젝트 번호를 찾을 수 없음");
			}
			Map<String, Object> param = new HashMap<>();
			SessionInfo info = (SessionInfo)session.getAttribute(SESSION_MEMBER);
			if(info!=null) {				
				param.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			}
			param.put(ATTRIBUTE_PROEJCTNO, projectNo);
			project = service.readProject(param);
			rewards = service.listRewards(projectNo);
			popularProject = service.listPopularProject();
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD,rewards);
			model.addAttribute(ATTRIBUTE_POPULAR_PROJECT, popularProject);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		return ".detailLayout";
	}
	
	//프로젝트 좋아요
	@RequestMapping(value= {"/{projectNo}/like"})
	@ResponseBody
	public Map<String, Object> likeProject(@PathVariable Integer projectNo, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		try {
			if(projectNo==null) {
				throw new Exception("There is no projectNo");
			}
			//좋아요 시도하기
			Map<String, Object> map = new HashMap<>();
			SessionInfo info = (SessionInfo) session.getAttribute(SESSION_MEMBER);
			map.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			map.put(ATTRIBUTE_PROEJCTNO, projectNo);
			result.put(JSON_RESULT, JSON_RESULT_OK);
			boolean echo = service.toggleLike(map);
			result.put(JSON_PROJECT_LIKE_RESULT, echo);
		} catch (Exception e) {
			result.put(JSON_RESULT, JSON_RESULT_ERROR);
			result.put(JSON_RESULT_ERROR_MESSAGE, e.getMessage());
		}
		return result;
	}
}
