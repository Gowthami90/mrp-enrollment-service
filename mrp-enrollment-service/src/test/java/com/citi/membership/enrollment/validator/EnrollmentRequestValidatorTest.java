package com.citi.membership.enrollment.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.model.ClientInfo;
import com.citi.membership.enrollment.model.CustomerInfo;
import com.citi.membership.enrollment.model.EnrollmentRequest;

public class EnrollmentRequestValidatorTest {
	EnrollmentRequestValidator validator = null;
	EnrollmentRequest enrollmentReq = null;

	@Before
	public void setUp() throws Exception {

		validator = new EnrollmentRequestValidator();
		enrollmentReq = buildEnrollmentReq();

	}

	
	@Test
	public void test_RequestObj_Null_Scenario() {

		try {
			validator.validate(null);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens001", e.getRespCode());
		}
	}
	@Test
	public void test_ClientObj_Null_Scenario() {

		try {
			enrollmentReq.setClientInfo(null);
			validator.validate(enrollmentReq);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens001", e.getRespCode());
		}
	}
	

	
	@Test
	public void test_CustomerObj_Null_Scenario() {

		try {
			enrollmentReq.setCustomerInfo(null);
			validator.validate(enrollmentReq);
		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens001", e.getRespCode());
		}
	}


	@Test
	public void testValidate_ClientId_Null_Scenario() {

		try {
			enrollmentReq.getClientInfo().setClientId(null);
			validator.validate(enrollmentReq);

		} catch (EnrollmentReqValidationExcep e) {
			assertEquals("ens002", e.getRespCode());
           }
	}
	
	@Test
	public void testValidate_ClientId_Empty_Scenario() {

		try {
			enrollmentReq.getClientInfo().setClientId(" "); 
			validator.validate(enrollmentReq);

		} catch (EnrollmentReqValidationExcep e) {

			assertEquals("ens002", e.getRespCode());
		}
	}
	
	
	@After
	public void tearDown() throws Exception {
       validator=null;
       enrollmentReq=null;
	
	}


	private EnrollmentRequest buildEnrollmentReq() {
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

		enrollmentRequest.setClientInfo(clientInfo);
		enrollmentRequest.setCustomerInfo(customerInfo);

		return enrollmentRequest;
	}

}
