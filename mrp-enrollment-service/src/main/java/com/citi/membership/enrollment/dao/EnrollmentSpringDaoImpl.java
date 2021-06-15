package com.citi.membership.enrollment.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.citi.membership.enrollment.exception.BusinessException;
import com.citi.membership.enrollment.exception.SystemException;
import com.citi.membership.enrollment.model.EnrollmentDaoReq;
import com.citi.membership.enrollment.model.EnrollmentDaoRes;

@Component
@Qualifier("enrollmentSpringDaoImpl")
public class EnrollmentSpringDaoImpl extends StoredProcedure implements EnrollmentDao {
	@Autowired
	public EnrollmentSpringDaoImpl(JdbcTemplate jdbcTemplate) {

		super(jdbcTemplate, "MRP_ENROLLMENT");
		System.out.println("*******EnrollmentSpringDaoImpl******");
		registerInputOutputParam();

	}

	private void registerInputOutputParam() {

		declareParameter(new SqlParameter("CLIENT_ID_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CHANNEL_ID_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CARD_NUM_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("CVV_IN", Types.INTEGER));
		declareParameter(new SqlParameter("EXPIRY_DATE_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("NAME_ON_CARD_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("FIRST_NAME_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("LAST_NAME_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("DOB_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("EMAIL_ID_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("MOBILE_NUM_IN", Types.VARCHAR));
		declareParameter(new SqlParameter("ENROLLMENT_DATE_IN", Types.VARCHAR));

		declareParameter(new SqlOutParameter("RESP_CODE_OUT", Types.VARCHAR));
		declareParameter(new SqlOutParameter("RESP_MESSAGE_OUT", Types.VARCHAR));
		declareParameter(new SqlOutParameter("ACK_NUM_OUT", Types.VARCHAR));

		compile();
	}

	public EnrollmentDaoRes createEnroll(EnrollmentDaoReq daoReq) throws BusinessException, SystemException {

		// Prepare the SP request
		EnrollmentDaoRes daoResp = null;

		try {

			Map<String, Object> requestMap = new HashMap<String, Object>();

			requestMap.put("CLIENT_ID_IN", daoReq.getClientId());
			requestMap.put("CHANNEL_ID_IN", daoReq.getChannelId());
			requestMap.put("CARD_NUM_IN", daoReq.getCardNum());
			requestMap.put("CVV_IN", daoReq.getCvv());
			requestMap.put("EXPIRY_DATE_IN", daoReq.getExpDate());
			requestMap.put("NAME_ON_CARD_IN", daoReq.getNameOnCard());
			requestMap.put("FIRST_NAME_IN", daoReq.getFirstName());
			requestMap.put("LAST_NAME_IN", daoReq.getLastName());
			requestMap.put("DOB_IN", daoReq.getDob());
			requestMap.put("EMAIL_ID_IN", daoReq.getEmailId());
			requestMap.put("MOBILE_NUM_IN", daoReq.getMobNum());
			requestMap.put("ENROLLMENT_DATE_IN", daoReq.getEnrollDate());

			Map<String, Object> respMap = super.execute(requestMap);

			String dbRespCode = (String) respMap.get("RESP_CODE_OUT");
			String dbRespMsg = (String) respMap.get("RESP_MESSAGE_OUT");

			if ("000".equals(dbRespCode)) {
				// TODO : replace the hard code values with database response
				daoResp = new EnrollmentDaoRes();
				daoResp.setAcknNum((String) respMap.get("ACK_NUM_OUT"));
				daoResp.setEnrollStatus("enrollment successfull");
				daoResp.setRespCode(dbRespCode);
				daoResp.setRespMsg(dbRespMsg);
			} else if ("100".equals(dbRespCode) || "101".equals(dbRespCode) || "1002".equals(dbRespCode)) {

				throw new BusinessException(dbRespCode, dbRespMsg);
			} else {
				throw new SystemException(dbRespCode, dbRespMsg);
			}

		}

		catch (BusinessException be) {
			// error log -
			// be.printStackTrace();
			// TODO : add error logs here
			be.printStackTrace();
			throw be;
		} catch (SystemException se) {
			// error log -
			// TODO : add error logs here
			se.printStackTrace();
			throw se;

		}

		catch (Exception e) {
			e.printStackTrace();
			throw new SystemException("8888", "Unknown Error from databse" + e.getMessage());
		}

		System.out.println("dao response is :" + daoResp);
		return daoResp;
	}

}
