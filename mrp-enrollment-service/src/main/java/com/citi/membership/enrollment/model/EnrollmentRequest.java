package com.citi.membership.enrollment.model;

import lombok.Data;

@Data
public class EnrollmentRequest {

	private ClientInfo clientInfo;
	private CustomerInfo customerInfo;

}
