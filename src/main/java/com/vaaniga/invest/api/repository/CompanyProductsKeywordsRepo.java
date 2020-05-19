package com.vaaniga.invest.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.vaaniga.invest.api.model.CompanyProductsKeywords;

@Repository
public interface CompanyProductsKeywordsRepo extends PagingAndSortingRepository<CompanyProductsKeywords, Long> {

	List<CompanyProductsKeywords> findByCompanyNameIgnoreCase(String companyName);
	
	List<CompanyProductsKeywords> findByProductNameIgnoreCase(String productName);
	
	List<CompanyProductsKeywords> findByKeywordsIgnoreCaseContaining(String keywords);
}
