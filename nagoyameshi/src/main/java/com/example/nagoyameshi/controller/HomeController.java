package com.example.nagoyameshi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index() {
		// ビューを呼び出す
		return "index"; // src/main/resources/templatesフォルダ内にあるindex.htmlを呼び出す
	}
}
