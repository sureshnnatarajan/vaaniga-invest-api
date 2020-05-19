package com.vaaniga.invest.api.service.impl;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vaaniga.invest.api.service.VaanigaWordpressService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VaanigaWordpressServiceImpl implements VaanigaWordpressService {

	public static final String _WP_POSTS_ENDPOINT_ = "https://vaaniga.com/wp-json/wp/v2/posts/";
	
	@Override
	public JSONObject listPosts() {

		HttpEntity<String> entity = addNseApiHeaders();
		
		log.info("_WP_POSTS_ENDPOINT_->URL->"+_WP_POSTS_ENDPOINT_);
		
		ResponseEntity<String> equitiesJsonEntity = new RestTemplate(getClientHttpRequestFactory())
							.exchange(_WP_POSTS_ENDPOINT_, HttpMethod.GET, entity, String.class);
		JSONObject response = new JSONObject(equitiesJsonEntity);
		
		//log.info("response ->"+response);
		
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
	
	private HttpEntity<String> addNseApiHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}

}
