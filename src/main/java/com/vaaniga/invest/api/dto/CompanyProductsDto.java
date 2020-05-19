package com.vaaniga.invest.api.dto;

import com.vaaniga.invest.api.model.CompanyMaster;
import com.vaaniga.invest.api.model.CompanyProducts;
import com.vaaniga.invest.api.model.CompanyProductsKeywords;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProductsDto {

	private String productName;
	
	private String companyName;
	
	private String productKeywords;
	
	private String productDescription;

	public CompanyProductsDto(CompanyProducts product, CompanyMaster companyMaster) {
		this.productName = product.getProductName();
		this.companyName = companyMaster.getCompanyName();
		this.productKeywords = product.getProductKeywords();
		this.productDescription = product.getProductDescription();
	}

	public CompanyProductsDto(CompanyProductsKeywords products) {
		this.productName = products.getProductName();
		this.companyName = products.getCompanyName();
		this.productKeywords = products.getKeywords();
	}
}
