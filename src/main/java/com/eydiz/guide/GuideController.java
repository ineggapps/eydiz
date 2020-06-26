package com.eydiz.guide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("guide.Controller")
@RequestMapping("/guide/*")
public class GuideController {
	@Autowired
	GuideSerivce service;
	
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		return ".guideLayout.main";
	}
	
	@RequestMapping(value="/createguide", method=RequestMethod.GET)
	public String createguide() {
		return ".guideLayout.createguide";
	}
}
