package com.eydiz.reward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eydiz.common.Constant;
import com.eydiz.detail.DetailConstant;
import com.eydiz.detail.DetailService;
import com.eydiz.member.MemberConstant;
import com.eydiz.member.SessionInfo;
import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@RequestMapping("/reward/*")
@Controller("reward.rewardController")
public class RewardController  implements Constant, MemberConstant, RewardConstant, DetailConstant{
	
	@Autowired
	private DetailService detailService;
	
	@RequestMapping("/{projectNo}/step1")
	public String step1(@PathVariable Integer projectNo, HttpServletRequest req, Model model) {
		try {
			SessionInfo info = (SessionInfo) req.getSession().getAttribute(SESSION_MEMBER);
			Map<String, Object> map = new HashMap<>();
			map.put(ATTRIBUTE_PROEJCTNO, projectNo);
			map.put(ATTRIBUTE_MEMBERNO, info.getMemberNo());
			Project project = detailService.readProject(map);
			List<Reward> rewards = detailService.listRewards(projectNo);
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD,rewards);
		} 
		catch(NullPointerException e) {
			return "redirect:" + req.getContextPath();
		}
		catch (Exception e) {
			return "redirect:"  + req.getContextPath() + String.format(API_DETAIL_PROJECT, projectNo);
		}
		return ".detailLayout.step1";
	}
}
