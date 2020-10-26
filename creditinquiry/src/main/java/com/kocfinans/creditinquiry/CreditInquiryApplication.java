package com.kocfinans.creditinquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class CreditInquiryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditInquiryApplication.class, args);
	}

}
