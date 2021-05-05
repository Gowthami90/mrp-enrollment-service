package com.citi.membership.enrollment.dao;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDaoReq;
import com.citi.membership.enrollment.model.EnrollmentDaoRes;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;

public interface EnrollmentDao {

	public EnrollmentDaoRes createEnroll(EnrollmentDaoReq enrollmentDaoReq) throws BusinessException, SystemException;
}
