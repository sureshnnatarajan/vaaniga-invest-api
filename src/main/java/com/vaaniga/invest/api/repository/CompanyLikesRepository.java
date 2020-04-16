package com.vaaniga.invest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaaniga.invest.api.model.CompanyLikes;

@Repository
public interface CompanyLikesRepository extends JpaRepository<CompanyLikes, Long> {

	public CompanyLikes findByCompanyId(Long companyId);
}
