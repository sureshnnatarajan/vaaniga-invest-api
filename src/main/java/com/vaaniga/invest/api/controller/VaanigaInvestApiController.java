package com.vaaniga.invest.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaaniga.invest.api.model.CompanyMaster;
import com.vaaniga.invest.api.service.VaanigaInvestApiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/admin")
@CrossOrigin("*")
@Slf4j
public class VaanigaInvestApiController {

	private VaanigaInvestApiService apiService;
	
	@Autowired
	public VaanigaInvestApiController(VaanigaInvestApiService apiService) {
		super();
		this.apiService = apiService;
	}

	@GetMapping("/")
	public String testGet() {
		return "Hello";
	}
	
	@GetMapping("/companies")
	public List<CompanyMaster> getCompanies() {
		List<CompanyMaster> responseList = null;
		
		long startTime = System.nanoTime();
		
		responseList = apiService.getCompanies();
		
		long timeElapsed = System.nanoTime() - startTime;
		
		log.debug(" Request Response time in Milli seconds : "+ timeElapsed / 1000000 );
		
		return responseList;
	}
	
	@GetMapping(value="/companies/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCompaniesAll() {
		
		List<CompanyMaster> responseList = null;
		
		long startTime = System.nanoTime();
		
		responseList = apiService.getCompanies();
		
		long timeElapsed = System.nanoTime() - startTime;
		
		log.debug(" Request Response time in Milli seconds : "+ timeElapsed / 1000000 );
		
		JSONObject responseObject = new JSONObject();
		
		List<JSONObject> entities = new ArrayList<JSONObject>();
		
		for (CompanyMaster company : responseList) {
			JSONObject entity = new JSONObject();
	        entity.put("companyName", company.getCompanyName());
	        entities.add(entity);
		}
		
		responseObject.put("results", entities);
		responseObject.put("executionTime", timeElapsed / 1000000 + " milliseconds");
		
		return new ResponseEntity<>(responseObject.toMap(), HttpStatus.OK);
	}
}
