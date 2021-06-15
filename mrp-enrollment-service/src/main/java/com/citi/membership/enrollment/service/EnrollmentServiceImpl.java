package com.citi.membership.enrollment.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.builder.EnrollmentServiceReqBuilder;
import com.citi.membership.enrollment.builder.EnrollmentServiceResBuilder;
import com.citi.membership.enrollment.controller.EnrollmentController;
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


@Component
public class EnrollmentServiceImpl implements EnrollmentService {
	
	private Logger logger = Logger.getLogger(EnrollmentController.class);
	
	@Autowired
	CardsServiceClient cardsServiceClient;
	
	@Autowired
	@Qualifier("enrollmentSpringDaoimpl")
	EnrollmentDao enrollmentDao;
	
	@Autowired
	EnrollmentServiceReqBuilder enrollmentServiceReqBuilder;
    @Autowired
	EnrollmentServiceResBuilder enrollmentServiceResBuilder;
    
	@Override
	public EnrollmentResponse createEnroll(EnrollmentRequest enrollmentReq) throws BusinessException, SystemException {

		logger.debug("Entered into service - start");

		// 1.Get the enrollment request from the controller
		
		//2.prepare the request for the service client
		
		
		
		//3.call the service client
		
				 CardDetailsResp cardDetailsResp= cardsServiceClient.getCardDetails(enrollmentReq.getCustomerInfo().getCardNum());
		
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
		

		// 2.prepare the request for dao with the help of controller request
				 
		 EnrollmentDaoReq enrollmentDaoReq  =enrollmentServiceReqBuilder.buildDaoRequest(enrollmentReq);
		

		// 3.call dao and get the dao response

		 EnrollmentDaoRes enrollmentdaoRes = enrollmentDao.createEnroll(enrollmentDaoReq);

		// 4.Prepare the service response with the help of dao

	EnrollmentResponse enrollmentResponse=enrollmentServiceResBuilder.buildEnrollResp(enrollmentdaoRes);

		logger.info("Exit from service- end:::::::" +  enrollmentResponse);

		return enrollmentResponse;
	}
		
	}
