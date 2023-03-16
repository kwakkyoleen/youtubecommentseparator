package io.dasom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ycs")
public class MainController {
	@GetMapping(value = {"/",""})
	public String Mainpage(Model model) {
		return "index";
	}
}
