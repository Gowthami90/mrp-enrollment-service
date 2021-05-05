package com.citi.membership.enrollment.dao;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDaoReq;
import com.citi.membership.enrollment.model.EnrollmentDaoRes;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;

public class EnrollmentDaoImpl implements EnrollmentDao {

	@Override
	public EnrollmentDaoRes createEnroll(EnrollmentDaoReq daoReq) throws BusinessException, SystemException {

		System.out.println("Entered into dao - start");
		// 1.Get the request from the service;
		// 2.Prepare the request for database i.e prepare the database queries
		EnrollmentDaoRes daoResp = null;
		try {
			// 3.call db and get the db response i.e ResultSet

			String dbRespCode = "0"; // replace with db response
			String dbRespMsg = "Success";

			// 4.prepare the dao response

			daoResp = new EnrollmentDaoRes();
			if ("0".equals(dbRespCode)) {
				// replace daoResp with ResultSet replace hardcode values with db response
				daoResp.setAcknNum("1212ab");
				daoResp.setEnrollStatus("Enrollment successfull");
				daoResp.setRespCode("0");
				daoResp.setRespMsg("successfull");

			} else if ("100".equals(dbRespCode) || "101".equals(dbRespCode) || "102".equals(dbRespCode)) {

				// TODO Handle business exception
				throw new BusinessException(dbRespCode, dbRespMsg);

			} else {

				// TODO Handle System exception
				throw new SystemException(dbRespCode, dbRespMsg);

			}
		} catch (BusinessException be) {

			be.printStackTrace();
			throw be;
		} catch (SystemException se) {

			se.printStackTrace();
			throw se;
		} catch (Exception e) {

			throw new SystemException("8888", "unknown error from database " + e.getMessage());

		}
		System.out.println("Exit from dao - end" + daoResp);
		return daoResp;
	}

}
