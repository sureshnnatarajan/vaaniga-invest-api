package com.vaaniga.invest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyLikesDto {
	
	private String companyName;
	
	private Long count;
}
