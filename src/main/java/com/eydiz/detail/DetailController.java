package com.eydiz.detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eydiz.studio.Project;
import com.eydiz.studio.Reward;

@Controller("detail.detailController")
@RequestMapping("/detail/*")
public class DetailController implements DetailConstant{

	@Autowired
	DetailService service;
	
	@RequestMapping(value="/")
	public String main() {
		return "redirect:/";
	}
	
	@RequestMapping(value= {"/{projectNo}"})
	public String detailProject(@PathVariable Integer projectNo, Model model) {
		Project project = null;
		List<Reward> rewards = null;
		try {
			if(projectNo==null) {
				throw new Exception("프로젝트 번호를 찾을 수 없음");
			}
			project = service.readProject(projectNo);
			rewards = service.listRewards(projectNo);
			model.addAttribute(ATTRIBUTE_PROJECT, project);
			model.addAttribute(ATTRIBUTE_REWARD,rewards);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
		return ".detailLayout";
	}
}
