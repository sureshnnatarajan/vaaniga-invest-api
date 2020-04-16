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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping(value="/companies/{sector}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCompaniesBySector(@PathVariable String sector) {
		List<CompanyMaster> responseList = null;
		
		long startTime = System.nanoTime();
		
		responseList = apiService.getCompaniesBySector(sector);
		
		JSONObject responseObject = new JSONObject();
		
		List<JSONObject> entities = new ArrayList<JSONObject>();
		
		for (CompanyMaster company : responseList) {
			JSONObject entity = new JSONObject();
	        entity.put("companyName", company.getCompanyName());
	        entities.add(entity);
		}
		
		long timeElapsed = System.nanoTime() - startTime;
		
		responseObject.put("results", entities);
		responseObject.put("executionTime", timeElapsed / 1000000 + " milliseconds");
		
		return new ResponseEntity<>(responseObject.toMap(), HttpStatus.OK);
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
	
	@PostMapping(value = "/companies/like/{companyName}")
	public ResponseEntity<Object> likeCompany(@PathVariable String companyName) {
		this.apiService.saveLikeForCompany(companyName);
		JSONObject entity = new JSONObject();
		entity.put("result", "success");
		return new ResponseEntity<>(entity.toMap(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/companies/like/{companyName}")
	public ResponseEntity<Object> getLikeCount(@PathVariable String companyName) {
		Long count = this.apiService.getLikeCountForCompany(companyName);
		JSONObject entity = new JSONObject();
		entity.put("result", count);
		return new ResponseEntity<>(entity.toMap(), HttpStatus.OK);
	}
	
}
