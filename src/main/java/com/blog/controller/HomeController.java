package com.blog.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeController {
	
	@GetMapping({"", "/"})
	public String home(Model model) throws Exception {
		return "index";
	}
}
