package com.vaaniga.invest.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaaniga.invest.api.dto.CompanyLikesDto;
import com.vaaniga.invest.api.dto.CompanyProductsDto;
import com.vaaniga.invest.api.model.CompanyLikes;
import com.vaaniga.invest.api.model.CompanyMaster;
import com.vaaniga.invest.api.model.CompanyProducts;
import com.vaaniga.invest.api.repository.CompanyLikesRepository;
import com.vaaniga.invest.api.repository.CompanyMasterRepository;
import com.vaaniga.invest.api.repository.CompanyProductsRepository;
import com.vaaniga.invest.api.service.VaanigaInvestApiService;

@Service
public class VaanigaInvestApiServiceImpl implements VaanigaInvestApiService {

	private CompanyMasterRepository companyMasterRepo;
	
	private CompanyLikesRepository companyLikesRepo;
	
	private CompanyProductsRepository companyProductsRepo;
	
	@Autowired
	public VaanigaInvestApiServiceImpl(CompanyMasterRepository companyMasterRepoParam, 
			CompanyLikesRepository companyLikesRepoParam, CompanyProductsRepository companyProductsRepoParam) {
		super();
		this.companyMasterRepo = companyMasterRepoParam;
		this.companyLikesRepo = companyLikesRepoParam;
		this.companyProductsRepo = companyProductsRepoParam;
	}

	@Override
	public List<CompanyMaster> getCompanies() {
		return companyMasterRepo.findAll();
	}

	@Override
	public List<CompanyMaster> getCompaniesBySector(String sector) {
		return companyMasterRepo.findByCategoriesContainingIgnoreCase(sector);
	}

	@Override
	@Transactional
	public void saveLikeForCompany(String companyName) {
		List<CompanyMaster> companyNames = companyMasterRepo.findByCompanyName(companyName);
		
		if (null == companyNames || companyNames.isEmpty()) {
			return;
		}
		
		CompanyLikes companyLike = companyLikesRepo.findByCompanyId(companyNames.get(0).getId());
		if (companyLike == null) {
			companyLike = new CompanyLikes();
			companyLike.setCompanyId(companyNames.get(0).getId());
			companyLike.setCount(1L);
		} else {
			companyLike.setCompanyId(companyNames.get(0).getId());
			companyLike.setCount(companyLike.getCount()+1);
		}
		
		companyLikesRepo.save(companyLike);
	}

	@Override
	public Long getLikeCountForCompany(String companyName) {
		List<CompanyMaster> companyNames = companyMasterRepo.findByCompanyName(companyName);
		
		if (null == companyNames || companyNames.isEmpty()) {
			return 0L;
		}
		
		CompanyLikes companyLike = companyLikesRepo.findByCompanyId(companyNames.get(0).getId());
		
		return companyLike.getCount();
		
	}

	@Override
	public List<CompanyLikesDto> getLikesForCompanies() {
		
		List<CompanyLikes> companyLikesList = companyLikesRepo.findAll();
		
		List<CompanyLikesDto> likesListDto = new ArrayList<>(companyLikesList.size());
		
		for (CompanyLikes like : companyLikesList) {
			CompanyLikesDto dto = new CompanyLikesDto();
			
			Optional<CompanyMaster> companies = companyMasterRepo.findById(like.getCompanyId());
			
			if (companies.isPresent()) {
				dto.setCompanyName(companies.get().getCompanyName());
				//CompanyLikes companyLikes = companyLikesRepo.findByCompanyId(companies.get().getId());
			}
			dto.setCount(like.getCount());
			likesListDto.add(dto);
		}
		
		return likesListDto;
	}

	@Override
	public List<CompanyProductsDto> getProductsForCompany(String companyName) {
		
		List<CompanyMaster> companies = companyMasterRepo.findByCompanyName(companyName);
		
		List<CompanyProductsDto> productList = new ArrayList<>();
		
		if (null == companies || companies.isEmpty()) {
			return productList;
		}
		
		//access first company
		List<CompanyProducts> products = companyProductsRepo.findByCompanyId(companies.get(0).getId());
		
		if (products == null || products.isEmpty()) {
			return productList;
		}
		
		for (CompanyProducts product : products) {
			CompanyProductsDto productDto = new CompanyProductsDto(product, companies.get(0));
			productList.add(productDto);
		}
		
		return productList;
	}

}
