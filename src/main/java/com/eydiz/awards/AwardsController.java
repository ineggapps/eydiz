package com.eydiz.awards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("awards.awardsController")
@RequestMapping(value="/awards/*")
public class AwardsController {
	@Autowired
	AwardsService service;
	
	@RequestMapping(value="list")
	public String main(
			Model model
			)throws Exception {
		// 판매왕 top5
		List<Awards> bestSellerList = service.bestSeller();
		model.addAttribute("bestSellerList", bestSellerList);

		
		
		// 퍼센트 top5
		List<Awards> highestList = service.highestPercentage();
		model.addAttribute("highestList", highestList);
		
		
		
		// 플젝 많이 브랜드 top 5
		List<Awards> brandRankList = service.brandProjectRank();
		model.addAttribute("brandRankList", brandRankList);
	
		
		return ".awardsLayout.list";
	}
	
}
