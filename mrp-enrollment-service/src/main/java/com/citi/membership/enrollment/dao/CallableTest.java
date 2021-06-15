package com.citi.membership.enrollment.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;


public class CallableTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		String sql = "{call GET_TRANSACTIONS(?,?,?,?,?,?,?,?)}";
		CallableStatement cs = connection.prepareCall(sql);

		cs.registerOutParameter(7, Types.VARCHAR);
		cs.registerOutParameter(8, Types.VARCHAR);

		cs.setString(1, "web");
		cs.setString(2, "online");
		cs.setString(3, "44444444444");
		cs.setLong(4, 123);
		cs.setString(5, "12-2018");
		cs.setString(6, "sunny");

		boolean  b = cs.execute();
		
		System.out.println(" boolean value is :"+b);
		/*ResultSet rs = cs.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString(1) + "--" + rs.getString(2) + "--" + rs.getString(3));
		}*/

		System.out.println(cs.getString(7));
		System.out.println(cs.getString(8));
	}



}
