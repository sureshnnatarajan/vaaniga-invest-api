package com.vaaniga.invest.api.service;

import java.util.List;

import com.vaaniga.invest.api.dto.CompanyLikesDto;
import com.vaaniga.invest.api.dto.CompanyProductsDto;
import com.vaaniga.invest.api.model.CompanyMaster;

public interface VaanigaInvestApiService {

	List<CompanyMaster> getCompanies();
	
	List<CompanyMaster> getCompaniesBySector(String sector);
	
	void saveLikeForCompany(String companyName);
	
	Long getLikeCountForCompany(String companyName);
	
	List<CompanyLikesDto> getLikesForCompanies();
	
	List<CompanyProductsDto> getProductsForCompany(String companyName);
	
	List<CompanyProductsDto> getCompanyProducts(String companyName);
}
