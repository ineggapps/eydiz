package com.eydiz.partner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("partner.partnerController")
@RequestMapping("/partner/*")
public class PartnerController {

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list( ) {
		return ".partnerLayout.list";
	}
	
}
