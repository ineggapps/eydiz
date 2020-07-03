package com.eydiz.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("school.schoolController")
@RequestMapping("/school/*")
public class SchoolController {
	
	@Autowired
	private SchoolService service;
	
	@RequestMapping(value="main")
	public String main() throws Exception {
		service.test();
		return ".schoolLayout.main";
	}
	
}
