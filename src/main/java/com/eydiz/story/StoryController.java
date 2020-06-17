package com.eydiz.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("story.storyController")
@RequestMapping("/story/*")
public class StoryController {
	@Autowired
	private StoryService service;
	
	
	@RequestMapping(value="story", method=RequestMethod.GET)
	public String list() {
		System.out.println("aaaa");
		return ".storyLayout";
	}
	
}
