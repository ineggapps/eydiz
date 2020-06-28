package com.eydiz.admin.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
	
@Controller("member.adminMemberController")
@RequestMapping(value="/admin/member/*")
public class AdminMemberController {
	@RequestMapping(value= {"/main","/"})
	public String main() {
		return ".adminLayout";
	}
}
