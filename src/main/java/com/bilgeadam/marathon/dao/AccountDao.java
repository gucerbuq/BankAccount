package com.bilgeadam.marathon.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.marathon.entity.Account;
import com.bilgeadam.marathon.entity.Customer;

import jakarta.persistence.TypedQuery;

public class AccountDao implements Irepository<Account>{

	@Override
	public void create(Account entity) {
		
		Session session = null;

		try {
			session = databaseConnection();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Account data is added to db.");
		} catch (Exception e) {
			e.getMessage();
			System.err.println("Some problem occured while adding account data");
		} finally {
			session.close();
		}
		
	}

	@Override
	public void update(long id, Account entity) {
		
		Session session = null;

		try {
			Account updateAccount = find(id);
			updateAccount.setAccountType(entity.getAccountType());
			updateAccount.setAccountNo(entity.getAccountNo());
			updateAccount.setTransactionList(entity.getTransactionList());
			updateAccount.setCustomer(entity.getCustomer());

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateAccount);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING Account data.");
		} finally {
			session.close();
		}
		
	}
	
	
	public void update(long id, Customer customer) {
		
		Session session = null;

		try {
			Account updateAccount = find(id);
			updateAccount.setCustomer(customer);

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateAccount);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING Account data.");
		} finally {
			session.close();
		}
		
	}

	@Override
	public void delete(long id) {
		
		Session session = null;

		try {
			Account deleteAccount = find(id);
			if (deleteAccount == null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteAccount);
				session.getTransaction().commit();

				System.out.println("Succesfully deleted.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING Account data.");
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Account> listAll() {
		
		
		Session session = databaseConnection();

		String hql = "SELECT acc FROM Account as acc";

		TypedQuery<Account> typedQuery = session.createQuery(hql, Account.class);
		List<Account> customerAccount = typedQuery.getResultList();

		return customerAccount;
		
	}

	@Override
	public Account find(long id) {
		
		Account account = null;
		Session session = databaseConnection();
		
		try {
			account = session.find(Account.class, id);
			
			if(account != null) {
				System.out.println("Found customer account : " + account);
			}else {
				System.out.println("Customer not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while finding customer data.");
		} finally {
			session.close();
		}
		
		return account;
		
	}

}
