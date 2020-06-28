package com.eydiz.admin.funding;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("funding.adminFundingController")
@RequestMapping(value= {"/admin/funding/*", "/admin/donate/*"})
public class AdminFundingController {
	@RequestMapping(value= {"/main","/"})
	public String main() {
		return ".adminLayout";
	}
}
