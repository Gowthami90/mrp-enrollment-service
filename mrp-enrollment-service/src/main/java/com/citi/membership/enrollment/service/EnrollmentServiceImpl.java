package com.citi.membership.enrollment.service;

import java.util.List;

import com.citi.membership.enrollment.dao.EnrollmentDao;
import com.citi.membership.enrollment.dao.EnrollmentDaoImpl;
import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.CardDetails;
import com.citi.membership.enrollment.model.CardDetailsResp;
import com.citi.membership.enrollment.model.EnrollmentDaoReq;
import com.citi.membership.enrollment.model.EnrollmentDaoRes;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.model.StatusBlock;
import com.citi.membership.enrollment.svcclient.CardsServiceClient;
import com.citi.membership.enrollment.svcclient.CardsServiceClientImpl;

public class EnrollmentServiceImpl implements EnrollmentService {

	@Override
	public EnrollmentResponse createEnroll(EnrollmentRequest enrollmentReq) throws BusinessException, SystemException {

		System.out.println("Entered into service - start");

		// 1.Get the enrollment request from the controller
		
		//2.prepare the request for the service client
		
		//3.call the service client
		
		CardsServiceClient cardsServiceClient=new CardsServiceClientImpl();
		 CardDetailsResp cardDetailsResp= new CardDetailsResp();
		 if(cardDetailsResp !=null) {
			 List<CardDetails> cardDetailsList =cardDetailsResp.getCardDetails();
			 
			 for(CardDetails cardDetails: cardDetailsList) {
			 
			 String card13digit =cardDetails.getCardNum().substring(13,15);
			 if(!"pa".equals(cardDetails.getProductType())|| !"active".equals(cardDetails.getStatus()) ||
					 !cardDetails.isPrimary()) {			 
				 
				 //todo handle userdefine exception - your not eligible for this enrollment
			 }	 
		 }
		 }
		 
		
		//TODO once will get the response from card details then we need to write the below business logic
		// one cardproducts, status,Primarycard....etc
		//if all the checks are success then only it will call to  EnrollmentDao else it will throw exception from here
		

		// 2.prepare the request for dao wit the help of controller request

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
		daoReq.setMsgts(enrollmentReq.getCustomerInfo().getMsgts());

		// 3.call dao and get the dao response

		EnrollmentDao enrollmentDao = new EnrollmentDaoImpl();

		// 4.Prepare the service response with the help of dao

		EnrollmentResponse enrollResp = new EnrollmentResponse();

		EnrollmentDaoRes daoResp = enrollmentDao.createEnroll(daoReq);

		StatusBlock statusBlock = new StatusBlock();

		statusBlock.setRespCode(daoResp.getRespCode());
		statusBlock.setRespMsg(daoResp.getRespMsg());

		enrollResp.setStatusblock(statusBlock);
		enrollResp.setEnrollStatus(daoResp.getEnrollStatus());
		enrollResp.setAcknNum(daoResp.getAcknNum());
		

		System.out.println("Exit from service- end:::::::" + enrollResp);

		return enrollResp;
	}

}
