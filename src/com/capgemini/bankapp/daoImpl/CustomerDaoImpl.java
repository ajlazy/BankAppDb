package com.capgemini.bankapp.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.capgemini.bankapp.dao.CustomerDao;
import com.capgemini.bankapp.model.Customer;
import com.capgemini.bankapp.util.DbUtil;

public class CustomerDaoImpl implements CustomerDao {
	
	
	@Override
	public Customer authenticate(Customer customer) {
String query = "SELECT * FROM customers WHERE customerEmail = ? AND customerPassword = ?";
		
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)
				) {
			
			statement.setString(1,customer.getCustomerEmail());
			statement.setString(2,customer.getCustomerPassword());
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				Customer cusm = new Customer();
				cusm.setCustomerId(result.getInt(1));
				cusm.setCustomerName(result.getString(2));
				cusm.setCustomerPassword(result.getString(3));
				cusm.setCustomerEmail(result.getString(4));
		//cusm.setCustomerDateOfBirth( Date.valueOf(LocalDate.of(result.getDate(6))));
				return cusm;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

		
	
	@Override
		public Customer updateProfile(Customer customer) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
			// TODO Auto-generated method stub
			return false;
		}
	
		

//Set<Customer> customers = DummyDataBase.getCustomer();
//
//	@Override
//	public Customer authenticate(Customer customer) {
//		for (Customer c : customers) {
//			if (c.getCustomerEmail().equals(customer.getCustomerEmail())) {
//				if (c.getCustomerPassword().equals(customer.getCustomerPassword())) {
//					return c;
//				}
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public Customer updateProfile(Customer customer) {
//	
//		for (Customer c : customers) {
//			if (c.getCustomerEmail().equals(customer.getCustomerEmail())) {
//				if (c.getCustomerPassword().equals(customer.getCustomerPassword())) {
//					c.setCustomerName(customer.getCustomerName());
//					c.setCustomerEmail(customer.getCustomerEmail());
//					c.setCustomerAddress(customer.getCustomerAddress());
//					c.setCustomerDateOfBirth(customer.getCustomerDateOfBirth());
//					DummyDataBase.setCustomers(customers);
//					return c;
//				}
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public boolean updatePassword(Customer customer, String oldPassword, String newPassword){
//		for (Customer c : customers) {	
//		if(c.getCustomerId()==customer.getCustomerId())
//		{
//			if (c.getCustomerPassword().equals(oldPassword)) {
//					customer.setCustomerPassword(newPassword);
//					DummyDataBase.setCustomers(customers);
//					return true;
//				}
//		}
//		}
//			
//		return false;
	}
