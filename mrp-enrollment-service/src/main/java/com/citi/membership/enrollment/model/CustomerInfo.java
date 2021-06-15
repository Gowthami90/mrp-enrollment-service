package com.citi.membership.enrollment.model;

import lombok.Data;

@Data
public class CustomerInfo {
	
	private String clientId;
	private String channelId;
	private String msgts;
	private String cardNum;
	private String cvv;
	private String expDate;
	private String nameOnCard;
	private String firstName;
	private String lastName;
	private String dob;
	private String emailId;
	private String mobNum;
	private String enrollDate;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerInfo [clientId=");
		builder.append(clientId);
		builder.append(", msgts=");
		builder.append(msgts);
		builder.append(", nameOnCard=");
		builder.append(nameOnCard);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", mobNum=");
		builder.append(mobNum);
		builder.append(", enrollDate=");
		builder.append(enrollDate);
		builder.append("]");
		return builder.toString();
	}
	
}
