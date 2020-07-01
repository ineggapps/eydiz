package com.eydiz.admin.brand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("brand.adminBrandController")
@RequestMapping("/admin/brand/*")
public class AdminBrandController {
	
	@RequestMapping(value= {"/view","/"})
	public String view() {
		return ".adminLayout"; 
	}
	
}
