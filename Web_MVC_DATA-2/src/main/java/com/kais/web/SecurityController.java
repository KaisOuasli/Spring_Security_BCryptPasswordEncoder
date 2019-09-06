package com.kais.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/403")
	public String accessDenied() {
		return "403";

	}
}
