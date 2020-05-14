package com.vaaniga.invest.api.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaaniga.invest.api.service.NseIndiaOptionChainService;

@RestController
@RequestMapping("/nseIndia/")
@CrossOrigin("*")
public class NseIndiaOptionChainController {

	@Autowired
	private NseIndiaOptionChainService optionChainService;
	
	@GetMapping(value = "/optionChainEquities/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> fetchOptionChainEquities(@RequestParam String symbol) {
		
		JSONObject response = optionChainService.fetchOptionChainEquities(symbol);
		
		return ResponseEntity.ok(response.toMap());
	}
	
	@GetMapping(value = "/optionChainIndices/", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> fetchOptionChainIndicies(@RequestParam String symbol) {
		
		JSONObject response = optionChainService.fetchOptionChainIndices(symbol);
		
		return ResponseEntity.ok(response.toMap());
	}
}
