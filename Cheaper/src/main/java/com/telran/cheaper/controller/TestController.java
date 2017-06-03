package com.telran.cheaper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TestController {
	/*
	 * @RequestMapping("/greeting") public String greeting(@RequestParam(value =
	 * "name", required = false, defaultValue = "World") String name, Model
	 * model) { model.addAttribute("name", name); return "greeting"; }
	 */

	// @RequestMapping({"","/","/index"})
	// @ResponseBody
	// public String home() {
	// return "Hello World!";
	// }

	// inject via application.properties
//	@Value("${greeting.message:test}")
//	private String message = "Hello World";
//
//	@GetMapping("/greeting")
//	public String greetingForm(Model model) {
//		model.addAttribute("greeting", new Greeting());
//		model.addAttribute("message", this.message);
//		return "greeting";
//	}
//
//	@PostMapping("/greeting")
//	public String greetingSubmit(@ModelAttribute Greeting greeting) {
//		return "result";
//	}
}
