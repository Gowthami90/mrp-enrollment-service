package com.citi.membership.enrollment.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.model.StatusBlock;
import com.citi.membership.enrollment.util.EnrollmentConstant;

@ControllerAdvice

public class EnrollControllerAdvice {
	@ExceptionHandler(value = EnrollmentReqValidationExcep.class)
	@ResponseBody
	public EnrollmentResponse handleReqInvalidExceptions(EnrollmentReqValidationExcep exception) {
		// TODO need to implement error logs
		EnrollmentResponse enrollResp = buildStatusBlock(exception.getRespCode(), exception.getRespMsg());
		return enrollResp;
	}

	@ExceptionHandler(value = SystemException.class)
	@ResponseBody
	public EnrollmentResponse handleSystemError(SystemException exception) {
		// TODO need to implement error logs
		EnrollmentResponse enrollResp = buildStatusBlock(exception.getRespCode(), exception.getRespMsg());
		return enrollResp;
	}

	private EnrollmentResponse buildStatusBlock(String respCode, String respMsg) {
		EnrollmentResponse enrollResp = new EnrollmentResponse();
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setRespCode(respCode);
		statusBlock.setRespMsg(respMsg);
		enrollResp.setStatusblock(statusBlock);
		return enrollResp;
	}

	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public EnrollmentResponse handleDataError(BusinessException exception) {
	
		EnrollmentResponse enrollResp = buildStatusBlock(EnrollmentConstant.GENERIC_ERROR_CODE,EnrollmentConstant.GENERIC_ERROR_MSG );
		return enrollResp;

	}
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public EnrollmentResponse handleDataError(Exception exception) {
	
		EnrollmentResponse enrollResp = buildStatusBlock("222","Generic Error from service");
		return enrollResp;

	}


}
