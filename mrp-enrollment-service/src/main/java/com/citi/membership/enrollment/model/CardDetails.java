package com.citi.membership.enrollment.model;

import lombok.Data;

@Data
public class CardDetails {

	private String cardNum;
	private boolean primary;
	private String productType;
	private String status;
	private String fname;
	private String lname;
	private boolean pastDue;
	
}
