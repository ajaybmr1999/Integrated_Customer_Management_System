package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
	private static final String dburl = "jdbc:mysql://localhost:3306/customer_management";
	private static final String dbusername = "root";
	private static final String dbpassword = "ajaybr";
	
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	
	private static final String customerValidateQry = "select * from admin where email=? and password=?";
	
	public static boolean validateAdmin(String email , String password) {
		boolean b = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dburl,dbusername, dbpassword);
			
			ps = con.prepareStatement(customerValidateQry);
			ps.setString(1, email);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			b = rs.next();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
}
