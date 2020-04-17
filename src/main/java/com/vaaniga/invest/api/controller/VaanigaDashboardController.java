package com.vaaniga.invest.api.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaaniga.invest.api.service.VaanigaDashboardService;

@RestController
@RequestMapping(path="/dashboard")
@CrossOrigin("*")
public class VaanigaDashboardController {

	private VaanigaDashboardService vaanigaDashboardService;
	
	@Autowired
	public VaanigaDashboardController(VaanigaDashboardService vaanigaDashboardService) {
		super();
		this.vaanigaDashboardService = vaanigaDashboardService;
	}

	@GetMapping(value = "/productCount/{sector}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProductCountBySector(@PathVariable String sector) {
		
		long startTime = System.nanoTime();
		List<JSONObject> productCountList = this.vaanigaDashboardService.getProductCountByCompanies(sector);
		
		JSONObject responseObject = new JSONObject();
		
		long timeElapsed = System.nanoTime() - startTime;
		
		responseObject.put("results", productCountList);
		responseObject.put("executionTime", timeElapsed / 1000000 + " milliseconds");
		
		return new ResponseEntity<>(responseObject.toMap(), HttpStatus.OK);
	}
}
