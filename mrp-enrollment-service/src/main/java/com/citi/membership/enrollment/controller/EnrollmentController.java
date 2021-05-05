package com.citi.membership.enrollment.controller;

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

@RestController
@RequestMapping(value = "/customer")
public class EnrollmentController {
	@RequestMapping(value = "/enrollment", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EnrollmentResponse createEnroll(@RequestBody EnrollmentRequest enrollmentReq)
			throws EnrollmentReqValidationExcep, BusinessException, SystemException {
		System.out.println("Entered into controller - start");

		// 1.Get the request from consumer/client

		// 2.Validate the request
		EnrollmentRequestValidator validator = new EnrollmentRequestValidator();

		validator.validate(enrollmentReq);

		// 3.Prepare the request for the service
		// 4.Call service and get the response
		EnrollmentService service = new EnrollmentServiceImpl();
		EnrollmentResponse enrollmentResp = service.createEnroll(enrollmentReq);

		// 5.Prepare the controller response

		System.out.println("Exit from controller - end");
		return enrollmentResp;

	}

	@RequestMapping(value = "/health", method = RequestMethod.GET)

	public String healthCheck() {

		return "Service up and running";
	}
}
