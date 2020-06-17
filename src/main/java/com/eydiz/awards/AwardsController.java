package com.eydiz.awards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("awards.awardsController")
@RequestMapping(value="/awards/*")
public class AwardsController {
	@Autowired
	AwardsService service;
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String main() {
		return ".awardsLayout.list";
	}
	
}
