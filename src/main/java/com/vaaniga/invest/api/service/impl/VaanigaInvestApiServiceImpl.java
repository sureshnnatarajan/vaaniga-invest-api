package com.vaaniga.invest.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaaniga.invest.api.model.CompanyMaster;
import com.vaaniga.invest.api.repository.CompanyMasterRepository;
import com.vaaniga.invest.api.service.VaanigaInvestApiService;

@Service
public class VaanigaInvestApiServiceImpl implements VaanigaInvestApiService {

	private CompanyMasterRepository companyMasterRepo;
	
	@Autowired
	public VaanigaInvestApiServiceImpl(CompanyMasterRepository companyMasterRepo) {
		super();
		this.companyMasterRepo = companyMasterRepo;
	}

	@Override
	public List<CompanyMaster> getCompanies() {
		return companyMasterRepo.findAll();
	}

}
