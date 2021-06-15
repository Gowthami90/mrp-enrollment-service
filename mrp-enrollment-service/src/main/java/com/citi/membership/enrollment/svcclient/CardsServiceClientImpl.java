package com.citi.membership.enrollment.svcclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.citi.membership.enrollment.controller.EnrollControllerAdvice;
import com.citi.membership.enrollment.model.CardDetailsRequest;
import com.citi.membership.enrollment.model.CardDetailsResp;


@Component
public class CardsServiceClientImpl implements CardsServiceClient {
	
	@Value ("${service-url}")
	private String serviceUrl;
	
	@Value ("${service-timeout}")
	private String serviceTimeout;
	
	@Autowired
	RestTemplate restTemplate;
	
	private Logger logger=Logger.getLogger( CardsServiceClientImpl.class);

	@Override
	public CardDetailsResp getCardDetails(String cardnum) {
		// TODO write rest client code to connect to CardDetailsService and get details

		
		CardDetailsResp svcResp = null;
		try {			
			HttpHeaders headers = new HttpHeaders();
			headers.set("accept", "application/json");
			headers.set("clientId", "mrp");
			headers.set("requestId", UUID.randomUUID().toString().substring(0, 16));
			headers.set("msgTs", "24-05-2021");

			/*
			 * HttpComponentsClientHttpRequestFactory httpRequestfactory = new
			 * HttpComponentsClientHttpRequestFactory();
			 * 
			 * httpRequestfactory.setConnectionRequestTimeout(1000);
			 * httpRequestfactory.setConnectTimeout(1000);
			 * httpRequestfactory.setReadTimeout(2000);
			 * 
			 * restTemplate.setRequestFactory(httpRequestfactory);
			 */

			HttpEntity entity = new HttpEntity(headers);
			
			logger.debug("service url is "+ serviceUrl);

			ResponseEntity<CardDetailsResp> response = restTemplate.exchange(serviceUrl, HttpMethod.GET, entity,
					CardDetailsResp.class);

			// Entity = Http Response code+ Response Body

			svcResp = null;

			if (response != null && response.getStatusCode().is2xxSuccessful()) {

				svcResp = response.getBody();

			}
			logger.info("response is : " + response);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			 logger.error("RestClientException from CardServiceClientImpl",e);
		}

		return svcResp;
	}



	/*public static void main(String args[]) {
	  
	 System.setProperty("environment", "dev");
	 CardsServiceClientImpl svcClientImpl=new CardsServiceClientImpl();  
	 CardDetailsResp response=svcClientImpl.getCardDetails("232563214");
	 System.out.println("response is :" + response);
	  
	}*/

}