package com.eydiz.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("admin.adminController")
public class AdminController {
	@RequestMapping("/admin/")
	public String main() {
		return "redirect:" + "/admin/brand/main";
	}
}
