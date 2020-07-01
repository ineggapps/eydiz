package com.eydiz.reward;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reward/*")
@Controller("reward.rewardController")
public class RewardController {
	@RequestMapping("/{projectNo}/step1")
	public String step1(@PathVariable Integer projectNo) {
		return ".detailLayout.step1";
	}
}
