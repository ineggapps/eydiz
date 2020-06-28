package com.eydiz.admin.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("project.adminFundingController")
@RequestMapping(value= {"/admin/project/*"})
public class AdminProjectController {
	@RequestMapping(value= {"/main","/"})
	public String main() {
		return ".adminLayout";
	}
}
