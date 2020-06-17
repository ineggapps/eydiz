package com.eydiz.cast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("cast.castController")
@RequestMapping("/cast/*")
public class CastController {
	@Autowired
	private CastService service;
	
	
	@RequestMapping(value="next", method=RequestMethod.GET)
	public String list(Model model) {
		System.out.println("aaaa");
		return ".castLayout";
	}
	
}
