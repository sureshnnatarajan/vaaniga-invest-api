package com.vaaniga.invest.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaaniga.invest.api.model.CompanyMaster;
import com.vaaniga.invest.api.repository.CompanyMasterRepository;
import com.vaaniga.invest.api.repository.CompanyProductsRepository;
import com.vaaniga.invest.api.service.VaanigaDashboardService;

@Service
public class VaanigaDashboardServiceImpl implements VaanigaDashboardService {

	private CompanyMasterRepository companyMasterRepo;
	
	private CompanyProductsRepository companyProductsRepo;
	
	@Autowired
	public VaanigaDashboardServiceImpl(CompanyMasterRepository companyMasterRepo,
			CompanyProductsRepository companyProductsRepo) {
		super();
		this.companyMasterRepo = companyMasterRepo;
		this.companyProductsRepo = companyProductsRepo;
	}

	@Override
	public List<JSONObject> getProductCountByCompanies(String sectorName) {
		List<JSONObject> resultList = new ArrayList<>();
		
		List<CompanyMaster> companyList = companyMasterRepo.findByCategoriesContainingIgnoreCase(sectorName);
		
		return resultList;
	}

}
