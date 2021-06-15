package com.citi.membership.enrollment.model;

import lombok.Data;

@Data
public class EnrollmentRequest {

	private ClientInfo clientInfo;
	private CustomerInfo customerInfo;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnrollmentRequest [clientInfo=");
		builder.append(clientInfo);
		builder.append(", customerInfo=");
		builder.append(customerInfo);
		builder.append("]");
		return builder.toString();
	}

}
