package com.citi.membership.enrollment.builder;

import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.model.EnrollmentDaoRes;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.model.StatusBlock;

@Component
public class EnrollmentServiceResBuilder {



	StatusBlock statusBlock = new StatusBlock();

	public EnrollmentResponse buildEnrollResp(EnrollmentDaoRes enrollmentdaoRes) {
		
		EnrollmentResponse enrollResp = new EnrollmentResponse();
		
		StatusBlock statusBlock = new StatusBlock();

	statusBlock.setRespCode(enrollmentdaoRes.getRespCode());
	statusBlock.setRespMsg(enrollmentdaoRes.getRespMsg());

	enrollResp.setStatusblock(statusBlock);
	enrollResp.setEnrollStatus(enrollmentdaoRes.getEnrollStatus());
	enrollResp.setAcknNum(enrollmentdaoRes.getAcknNum());
	
	return enrollResp;
	
}
}