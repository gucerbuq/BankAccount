package com.bilgeadam.marathon.dao;

import java.util.List;

import org.hibernate.Session;
import com.bilgeadam.marathon.entity.Customer;

import jakarta.persistence.TypedQuery;

public class CustomerDao implements Irepository<Customer> {

	@Override
	public void create(Customer entity) {
		
		Session session = null;

		try {
			session = databaseConnection();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Customer data is added to db.");
		} catch (Exception e) {
			e.getMessage();
			System.err.println("Some problem occured while adding customer data");
		} finally {
			session.close();
		}
		
	}

	@Override
	public void update(long id, Customer entity) {
		
		Session session = null;

		try {
			Customer updateCustomer = find(id);
			updateCustomer.setFirstName(entity.getFirstName());
			updateCustomer.setLastName(entity.getLastName());
			updateCustomer.setCustomerNo(entity.getCustomerNo());
			updateCustomer.setAccountList(entity.getAccountList());
			updateCustomer.setBranchList(entity.getBranchList());
			

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateCustomer);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING Customer data.");
		} finally {
			session.close();
		}
		
	}

	@Override
	public void delete(long id) {
		
		Session session = null;

		try {
			Customer deleteCustomer = find(id);
			if (deleteCustomer == null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteCustomer);
				session.getTransaction().commit();

				System.out.println("Succesfully deleted.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING Customer data.");
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Customer> listAll() {
		
		Session session = databaseConnection();

		String hql = "SELECT cust FROM Customer as cust";

		TypedQuery<Customer> typedQuery = session.createQuery(hql, Customer.class);
		List<Customer> customerList = typedQuery.getResultList();

		return customerList;
		
	}

	@Override
	public Customer find(long id) {
		
		Customer customer = null;
		Session session = databaseConnection();
		
		try {
			customer = session.find(Customer.class, id);
			
			if(customer != null) {
				System.out.println("Found customer : " + customer);
			}else {
				System.out.println("Customer not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while finding customer data.");
		} finally {
			session.close();
		}
		
		return customer;
		
	}

}
