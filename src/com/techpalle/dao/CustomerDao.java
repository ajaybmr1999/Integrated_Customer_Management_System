package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techpalle.model.Customer;

public class CustomerDao {
	private static final String dburl = "jdbc:mysql://localhost:3306/customer_management";
	private static final String dbusername = "root";
	private static final String dbpassword = "ajaybr";
	
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static Statement s = null;
	private static ResultSet rs = null;
	
	private static final String customerListQry = "select * from customer";
	private static final String customerListInsert = "insert into customer(name, email, mobile) values(?,?,?)";
	private static final String customerEditQry = "select * from customer where id = ?";
	private static final String customerUpdateQry  = "update customer set name=?, email=?, mobile=? where id= ?";
	private static final String customerDeleteQry = "delete from customer where id = ?";
	
	public static void deleteCustomerDetail(int id) {
		
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(customerDeleteQry);
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void editCustomerDetails(Customer c) {
		
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(customerUpdateQry);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobile());
			ps.setInt(4, c.getId());
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Customer getOneCustomer(int id) {
		Customer c = null;
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(customerEditQry);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			rs.next();
			int i = rs.getInt("id");
			String n = rs.getString("name");
			String e = rs.getString("email");
			long m = rs.getLong("mobile");
			
			c = new Customer(i, n, e, m);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return c;
	}
	
	public static void insertCustomer(Customer customer) {
		
		try {
			con = getConnectionDef();
			
			ps = con.prepareStatement(customerListInsert);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getEmail());
			ps.setLong(3, customer.getMobile());
			
			ps.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static Connection getConnectionDef() 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dburl,dbusername, dbpassword);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static ArrayList<Customer> getAllCustomers()
	{
		ArrayList<Customer> al = new ArrayList<>();
		try {
			con = getConnectionDef();
			
			s = con.createStatement();
			
			rs = s.executeQuery(customerListQry);
			
			while(rs.next()) {
				int i = rs.getInt("id");
				String n = rs.getString("name");
				String e = rs.getString("email");
				long m = rs.getLong("mobile");
				
				Customer c = new Customer(i, n, e, m);
				al.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				try {
					rs.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(s != null) {
				try {
					s.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return al;
	}
}
