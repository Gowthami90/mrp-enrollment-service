package com.citi.membership.enrollment.svcclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.citi.membership.enrollment.model.CardDetailsRequest;
import com.citi.membership.enrollment.model.CardDetailsResp;

public class CardsServiceClientImpl implements CardsServiceClient {

	

	@Override
	public CardDetailsResp getCardDetails(String cardnum) {
		// TODO write rest client code to connect to CardDetailsService and get details
		
		
		
		String serviceUrl="http://localhost:2021/card-details-service-war-1.0-SNAPSHOT/v1/carddetails/";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("clientId", "mrp");
		headers.set("requestId", UUID.randomUUID().toString().substring(0,16));
		headers.set("msgTs", "24-05-2021");
		
		RestTemplate restTemplate= new RestTemplate();
		
		HttpComponentsClientHttpRequestFactory httpRequestfactory=new HttpComponentsClientHttpRequestFactory();
		
		httpRequestfactory.setConnectionRequestTimeout(1000);
		httpRequestfactory.setConnectTimeout(1000);
		httpRequestfactory.setReadTimeout(2000);
		
		restTemplate.setRequestFactory(httpRequestfactory);
		HttpEntity entity= new HttpEntity(headers);
		
		ResponseEntity<CardDetailsResp> response=restTemplate.exchange(serviceUrl,HttpMethod.GET,entity,CardDetailsResp.class);
		
		
		// Entity = Http Response code+ Rsponse Body
		 
		CardDetailsResp svcResp=null;
		
		if(response != null && response.getStatusCode().is2xxSuccessful()) {
			
		svcResp=response.getBody();	
		
		}
		System.out.println("response is : " + response);
		
		return  svcResp;
	}
	
	}
