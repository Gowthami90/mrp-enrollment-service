package com.citi.membership.enrollment.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.service.EnrollmentService;
import com.citi.membership.enrollment.service.EnrollmentServiceImpl;
import com.citi.membership.enrollment.validator.EnrollmentRequestValidator;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/customer")
public class EnrollmentController {

	private Logger logger = Logger.getLogger(EnrollmentController.class);
	@Autowired
	EnrollmentRequestValidator validator;

	@Autowired
	EnrollmentService enrollmentService;

	@RequestMapping(value = "/enrollment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EnrollmentResponse createEnroll(@RequestBody EnrollmentRequest enrollmentReq)
			throws EnrollmentReqValidationExcep, BusinessException, SystemException {

		logger.debug("Entered into controller - start");

		if (enrollmentReq.getClientInfo() != null && enrollmentReq.getClientInfo().getReqId() != null)
			MDC.put("requestId", enrollmentReq.getClientInfo().getReqId());
		// 1.Get the request from consumer/client

		// 2.Validate the request

		validator.validate(enrollmentReq);

		// 3.Prepare the request for the service
		// 4.Call service and get the response

		EnrollmentResponse enrollmentResp = enrollmentService.createEnroll(enrollmentReq);

		// 5.Prepare the controller response

		logger.debug("Exit from controller - end");
		return enrollmentResp;

	}

	@RequestMapping(value = "/health", method = RequestMethod.GET)

	public String healthCheck() {

		return "Service up and running";
	}
}
