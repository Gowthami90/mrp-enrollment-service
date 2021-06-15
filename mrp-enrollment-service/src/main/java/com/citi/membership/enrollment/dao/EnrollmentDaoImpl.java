package com.citi.membership.enrollment.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.controller.EnrollmentController;
import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDaoReq;
import com.citi.membership.enrollment.model.EnrollmentDaoRes;
import com.citi.membership.enrollment.model.EnrollmentRequest;
import com.citi.membership.enrollment.model.EnrollmentResponse;


@Component
public class EnrollmentDaoImpl implements EnrollmentDao {
	
	
	private Logger logger = Logger.getLogger(EnrollmentController.class);

	@Override
	
		public EnrollmentDaoRes createEnroll( EnrollmentDaoReq daoReq) throws BusinessException, SystemException {
			
			System.out.println("Entered into  EnrollmentDaoImpl ");
			EnrollmentDaoRes daoResp = null;
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
				String sql = "{call MRP_ENROLLMENT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				CallableStatement cs = connection.prepareCall(sql);

				cs.registerOutParameter(13, Types.VARCHAR);
				cs.registerOutParameter(14, Types.VARCHAR);
				cs.registerOutParameter(15, Types.VARCHAR);

				cs.setString(1, daoReq.getClientId());
				cs.setString(2, "online");
				cs.setString(3, daoReq.getCardNum());
				cs.setString(4, daoReq.getCvv());
				cs.setString(5, daoReq.getExpDate());
				cs.setString(6, daoReq.getNameOnCard());
				cs.setString(7, daoReq.getFirstName());
				cs.setString(8, daoReq.getLastName());
				cs.setString(9, daoReq.getDob());
				cs.setString(10, daoReq.getEmailId());
				cs.setString(11, daoReq.getMobNum());
				cs.setString(12, daoReq.getEnrollDate());

				boolean  b = cs.execute();
				System.out.println("b is:"+b);
				String dbRespCode = cs.getString(13);
				String dbRespMsg = cs.getString(14);
				
				System.out.println("resp code :"+dbRespCode+" resp msg"+dbRespMsg);
				
				if( "000".equals(dbRespCode)) {
					//TODO : replace the hard code values with database response
					daoResp = new EnrollmentDaoRes();
					daoResp.setAcknNum(cs.getString(15));
					daoResp.setEnrollStatus("enrollment successfull");
					daoResp.setRespCode(dbRespCode);
					daoResp.setRespMsg(dbRespMsg);
				} else if( "100".equals(dbRespCode) || "101".equals(dbRespCode) || "1002".equals(dbRespCode)){
					
					throw new BusinessException(dbRespCode, dbRespMsg);
				} else {
					throw new SystemException(dbRespCode, dbRespMsg);
				}
			} 
			
			catch (BusinessException be) {
				//error log - 
				//be.printStackTrace();
				//TODO : add error logs here
				be.printStackTrace();
				throw be;
			} catch (SystemException se) {
				//error log - 
				//TODO : add error logs here
				se.printStackTrace();
				throw se;
				
			}
			
			catch( Exception  e) {
				e.printStackTrace();
				throw new SystemException("8888", "Unknown Error from databse"+e.getMessage());
			}
			
			System.out.println("dao response is :"+daoResp);
			return daoResp;
		}
	
	

}