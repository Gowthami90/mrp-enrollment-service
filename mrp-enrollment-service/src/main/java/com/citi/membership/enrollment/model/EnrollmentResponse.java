package com.citi.membership.enrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
// @JsonIgnoreProperties
@JsonInclude(value = Include.NON_NULL)   // i.e Dont send null values
public class EnrollmentResponse {

	private StatusBlock statusblock;
	private String enrollStatus;
	private String description;
	private String acknNum;

}
