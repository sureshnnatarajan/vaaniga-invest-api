package com.vaaniga.invest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableFeignClients
//@RibbonClient(name="nse-api")
public class VaanigaInvestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaanigaInvestApiApplication.class, args);
	}

}
