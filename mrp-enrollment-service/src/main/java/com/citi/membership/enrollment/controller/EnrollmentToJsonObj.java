package com.citi.membership.enrollment.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.citi.membership.enrollment.model.ClientInfo;
import com.citi.membership.enrollment.model.CustomerInfo;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnrollmentToJsonObj {
	
	public static void main (String [] args) throws JsonProcessingException {	
		 Logger logger=Logger.getLogger(EnrollmentToJsonObj.class);
	EnrollmentRequest enrollmentRequest = new EnrollmentRequest();
	
	ClientInfo clientInfo = new ClientInfo();
	  
	clientInfo.setClientId("web");
	clientInfo.setChannelId("online");
	clientInfo.setReqId("abc123xyz");
	clientInfo.setMsgTs("27-04-21");
	
	CustomerInfo customerInfo = new CustomerInfo();
	customerInfo.setCardNum("123456789654");
	customerInfo.setCvv("123");
	customerInfo.setDob("14-07-200");
	customerInfo.setEmailId("gow@gmail.com");
	customerInfo.setEnrollDate("1-1-2020");
	customerInfo.setExpDate("12-2025");
	customerInfo.setFirstName("vijay");
	customerInfo.setLastName("tech");
	customerInfo.setMobNum("1234567892");
	customerInfo.setNameOnCard("vijay");
	
	MDC.put("requestId", clientInfo.getReqId());
	
	enrollmentRequest.setClientInfo(clientInfo);
	enrollmentRequest.setCustomerInfo(customerInfo);
	
		ObjectMapper mapper = new ObjectMapper();
		String resp=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(enrollmentRequest);
		logger.info(resp);
		
	}
}
