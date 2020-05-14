package com.vaaniga.invest.api.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(name = "nse-api")
//@FeignClient(url = "https://www.nseindia.com/")
//@FeignClient(name = "nse-api", url = "https://www.nseindia.com/")
public interface NseIndiaFeignClient {

	//https://www.nseindia.com/api/option-chain-indices?symbol=NIFTY
	@GetMapping(path = "/api/option-chain-indices/")
    String getOptionChainIndices(@RequestParam(value="symbol") String symbol);
	
	//https://www.nseindia.com/api/option-chain-equities?symbol=BERGEPAINT
	@GetMapping(path = "/api/option-chain-equities/")
	String getOptionChainEquities(@RequestParam(value="symbol") String symbol); 
}