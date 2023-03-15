package io.dasom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ycs")
public class MainController {
	@GetMapping(value = {"/",""})
	public String Mainpage(Model model) {
		return "index";
	}
}
