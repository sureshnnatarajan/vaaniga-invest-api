package com.vaaniga.invest.api.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaaniga.invest.api.service.VaanigaWordpressService;

@RequestMapping("/wp")
@RestController
public class VaanigaWordpressController {

	@Autowired
	private VaanigaWordpressService vaanigaWordpressService;
	
	@GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listPosts() {
		JSONObject response = vaanigaWordpressService.listPosts(); 
		return ResponseEntity.ok(response.toMap());
	}
}
