package com.citi.membership.enrollment.builder;

import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.model.EnrollmentDaoReq;
import com.citi.membership.enrollment.model.EnrollmentRequest;

@Component
public class EnrollmentServiceReqBuilder {
	
	public EnrollmentDaoReq buildDaoRequest(EnrollmentRequest enrollmentReq) {
		
		EnrollmentDaoReq daoReq = new EnrollmentDaoReq();
		daoReq.setCardNum(enrollmentReq.getCustomerInfo().getCardNum());
		daoReq.setCvv(enrollmentReq.getCustomerInfo().getCvv());
		daoReq.setDob(enrollmentReq.getCustomerInfo().getDob());
		daoReq.setEmailId(enrollmentReq.getCustomerInfo().getEmailId());
		daoReq.setEnrollDate(enrollmentReq.getCustomerInfo().getEnrollDate());
		daoReq.setExpDate(enrollmentReq.getCustomerInfo().getExpDate());
		daoReq.setFirstName(enrollmentReq.getCustomerInfo().getFirstName());
		daoReq.setLastName(enrollmentReq.getCustomerInfo().getLastName());
		daoReq.setMobNum(enrollmentReq.getCustomerInfo().getMobNum());
		daoReq.setNameOnCard(enrollmentReq.getCustomerInfo().getMobNum());

		daoReq.setClientId(enrollmentReq.getCustomerInfo().getClientId());
		daoReq.setChannelId(enrollmentReq.getCustomerInfo().getChannelId());
		daoReq.setMsgts(enrollmentReq.getCustomerInfo().getMsgts());
		return daoReq;
		
		
	}

}
