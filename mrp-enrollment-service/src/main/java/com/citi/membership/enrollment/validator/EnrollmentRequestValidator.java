package com.citi.membership.enrollment.validator;

import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.exception.EnrollmentReqValidationExcep;
import com.citi.membership.enrollment.model.ClientInfo;
import com.citi.membership.enrollment.model.CustomerInfo;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;
import com.citi.membership.enrollment.util.EnrollmentConstant;

@Component
public class EnrollmentRequestValidator {
	public void validate(EnrollmentRequest enrollmentReq) throws EnrollmentReqValidationExcep {

		if (enrollmentReq == null || enrollmentReq.getClientInfo() == null || enrollmentReq.getCustomerInfo() == null) {

			throw new EnrollmentReqValidationExcep(EnrollmentConstant.ENS001,EnrollmentConstant.ENS001_DESC);
		}

		ClientInfo clientInfo = new ClientInfo();

		// ClientId null or empty scenario

		if (clientInfo.getClientId() == null || " ".equals(clientInfo.getClientId())) {

			throw new EnrollmentReqValidationExcep("ens002", "invalid Client Id");

		}

		

		if (clientInfo.getChannelId() == null || " ".equals(clientInfo.getChannelId())) {

			throw new EnrollmentReqValidationExcep("ens003", "invalid Channel Id");

		}

		CustomerInfo customerInfo = new CustomerInfo();

	

		if (customerInfo.getCardNum() == null || " ".equals(customerInfo.getCardNum())) {

			throw new EnrollmentReqValidationExcep("ens004", "invalid Card Num");

		}

	}
}
