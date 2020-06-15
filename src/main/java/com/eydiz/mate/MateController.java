package com.eydiz.mate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("mate.Controller")
@RequestMapping("/mate/*")
public class MateController {
	@Autowired
	MateService service;
	
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		service.test();
		return ".mateLayout.main";
	}
}
