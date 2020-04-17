package com.vaaniga.invest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaaniga.invest.api.model.CompanyProducts;

@Repository
public interface CompanyProductsRepository extends JpaRepository<CompanyProducts, Long> {

	List<CompanyProducts> findByProductName(String productName);
	
	List<CompanyProducts> findByCompanyId(Long companyId);
	
	List<CompanyProducts> findByProductKeywords(String productKeywords);
 
}