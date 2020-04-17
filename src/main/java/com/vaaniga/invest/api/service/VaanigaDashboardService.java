package com.vaaniga.invest.api.service;

import java.util.List;

import org.json.JSONObject;

public interface VaanigaDashboardService {

	List<JSONObject> getProductCountByCompanies(String sectorName);
}
