package com.vaaniga.invest.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaaniga.invest.api.model.CompanyMaster;

@Repository
public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Long> {

	List<CompanyMaster> findByCompanyName(String companyName);
}
