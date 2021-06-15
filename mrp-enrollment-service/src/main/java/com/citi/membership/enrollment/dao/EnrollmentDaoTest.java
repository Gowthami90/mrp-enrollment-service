package com.citi.membership.enrollment.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class EnrollmentDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

			//System.out.println(" Connection is  :"+connection);
			
			String sql = "{call MRP_ENROLLMENTS_001(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

			CallableStatement cs = connection.prepareCall(sql);
			
			//System.out.println(" callable statement is :"+cs);

			// prepare the sp in params

			cs.setString(1, "web");
			cs.setString(2, "online");
			cs.setString(3, "23232323232");
			cs.setString(4, "123");
			cs.setString(5, "12-2021");
			cs.setString(6, "sreenu");
			cs.setString(7, "sreenu");
			cs.setString(8, "tech");
			cs.setString(9, "24-08-2020");
			cs.setString(10, "sreenu.sreenutech@gmail.com");
			cs.setString(11, "3434343434");
			cs.setString(12, "13-05-2021");

			// prepare the sp out params
			cs.registerOutParameter(13, Types.VARCHAR);
			cs.registerOutParameter(14, Types.VARCHAR);
			cs.registerOutParameter(15, Types.VARCHAR);

			boolean b = cs.execute();
			System.out.println(" b is :"+b);
			//ResultSet rs = cs.executeQuery();

			System.out.println(cs.getString(13));
			System.out.println(cs.getString(14));
			System.out.println(cs.getString(15));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

	}



}
