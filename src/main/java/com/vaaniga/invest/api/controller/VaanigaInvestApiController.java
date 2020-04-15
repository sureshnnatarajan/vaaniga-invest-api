package com.vaaniga.invest.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/admin")
public class VaanigaInvestApiController {

	@GetMapping("/")
	public String testGet() {
		return "Hello";
	}
}
