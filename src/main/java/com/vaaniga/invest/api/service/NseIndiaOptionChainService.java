package com.vaaniga.invest.api.service;

import org.json.JSONObject;

public interface NseIndiaOptionChainService {

	JSONObject fetchOptionChainIndices(String symbol);
	
	JSONObject fetchOptionChainEquities(String symbol);
}
