package com.vaaniga.invest.api.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.zip.GZIPInputStream;

import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import com.vaaniga.invest.api.service.NseIndiaOptionChainService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NseIndiaOptionChainServiceImpl implements NseIndiaOptionChainService {
	
	public static final String _OPTION_CHAIN_INDICES_ENDPOINT_ = "https://www.nseindia.com/api/option-chain-indices?symbol=";
	public static final String _OPTION_CHAIN_EQUITIES_ENDPOINT_ = "https://www.nseindia.com/api/option-chain-equities?symbol=";
	
	public static final String endpoint = "https://www.nseindia.com/api/option-chain-indices?symbol=NIFTY";
	
	@Override
	public JSONObject fetchOptionChainIndices(String symbol) {
		HttpEntity<String> entity = addNseApiHeaders("IN");
		String url = constructApiEndPointWithSymbol("IN",symbol);
		
		log.info("fetchOptionChainIndices->URL->"+url);
		
		ResponseEntity<Resource> equitiesJsonEntity = new RestTemplate(getClientHttpRequestFactory()).exchange(url, HttpMethod.GET, entity, Resource.class);
		JSONObject response = new JSONObject();
		
		try {
			GZIPInputStream gzi = new GZIPInputStream(equitiesJsonEntity.getBody().getInputStream());
			response = new JSONObject(unCompressGzip(gzi));
			log.info("fetchOptionChainIndices->response->"+response);
		} catch (IOException e) {
			log.error(""+e);
		}
		return response;
	}
	
	//Override timeouts in request factory
	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory
	                      = new SimpleClientHttpRequestFactory();
	    //Connect timeout
	    clientHttpRequestFactory.setConnectTimeout(30_000);
	     
	    //Read timeout
	    clientHttpRequestFactory.setReadTimeout(30_000);
	    return clientHttpRequestFactory;
	}

	@Override
	public JSONObject fetchOptionChainEquities(String symbol) {
		HttpEntity<String> entity = addNseApiHeaders("EQ");
		String url = constructApiEndPointWithSymbol("EQ",symbol);
		
		System.out.println("URL==>" + url);
		
		ResponseEntity<Resource> equitiesJsonEntity = new RestTemplate().exchange(url, HttpMethod.GET, entity, Resource.class);
		
		System.out.println(equitiesJsonEntity);
		
		JSONObject response = dummyMethod(equitiesJsonEntity);
		
		return response;
	}

	private JSONObject dummyMethod(ResponseEntity<Resource> equitiesJsonEntity) {
		JSONObject response = new JSONObject();
		try {
			if (equitiesJsonEntity != null) {
				if (equitiesJsonEntity.getHeaders().getContentType().toString().equalsIgnoreCase("application/json;charset=utf-8")) {
					String obj = asString(equitiesJsonEntity.getBody());
					response = new JSONObject(obj);
				} else {
					GZIPInputStream gzi = new GZIPInputStream(equitiesJsonEntity.getBody().getInputStream());
					response = new JSONObject(unCompressGzip(gzi));		
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8")) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
	
	private String constructApiEndPointWithSymbol(String option, String symbol) {
		StringBuilder url = new StringBuilder();
		if ("".equalsIgnoreCase("EQ")) {
			url.append(_OPTION_CHAIN_EQUITIES_ENDPOINT_);
		} else {
			url.append(_OPTION_CHAIN_INDICES_ENDPOINT_);
		}
		url.append(symbol);
		return url.toString();
	}

	private HttpEntity<String> addNseApiHeaders(String option) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
		headers.add(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en");
		headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36");
		if ("EQ".equals(option)) {
			headers.add("Cookie", "45142221333757D8E0A93B310FD365F11736535C44530000CA1ABD5E7E928830~plqlUdDXl0CcD4RyyAUmpHEAaBbEyep9DrH6XW/rv9NtAiFMjMO7HL3W3ZqkeEzy7JbNjjEbrRltFKg7MO10LamLPomiLSRlOCO2DusDzhgAqeK2gk2q8oStKfg2TnCV3PsyA1HlOeiEf9/VRkCW+o7rEyXcRdSwVZslXGTDw7BYEvUSUWnQZOjm0SDi/AYoRmymmeojVBKRm14EVd2c1gFeP1QABBx3ZLJlnbutxb5h8=");
		} else {
			headers.add("Cookie", "7DB5BBE617DB1D49C27973DDF355D286~6nLfEuBSUtCpwDRpxhPbogQ6nIhpPoF0HT++y4fWeFacWuK9iSTYN+c5DutGyKOp693nJ0gZZVNpJGGHpuipZ3B36CVkQI/j9SboVCElLRFKl61luZbRtofNrVNa6a81S+iRkmtBsiS8vc4RDJdAVZ1J/OeahqEhwQGgbzEhwfA=");
		}
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
	
	public String unCompressGzip(GZIPInputStream gzip) {
		StringBuilder  szBuffer = new StringBuilder();
		try {
			byte  tByte [] = new byte [1024];
	
			while (true) {
			    int  iLength = gzip.read (tByte, 0, 1024); // <-- Error comes here
			    if (iLength < 0) {
			        break;
			    } 
			    szBuffer.append (new String (tByte, 0, iLength));
			}
		} catch (IOException iox) {
			iox.printStackTrace();
		}
		return szBuffer.toString();
	}
}
