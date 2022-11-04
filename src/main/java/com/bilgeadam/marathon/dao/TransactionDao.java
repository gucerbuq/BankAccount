package com.bilgeadam.marathon.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.marathon.entity.Transaction;

import jakarta.persistence.TypedQuery;

public class TransactionDao implements Irepository<Transaction> {

	@Override
	public void create(Transaction entity) {

		Session session = null;

		try {
			session = databaseConnection();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Transaction data is added to db.");
		} catch (Exception e) {
			e.getMessage();
			System.err.println("Some problem occured while adding transaction data");
		} finally {
			session.close();
		}

	}

	@Override
	public void update(long id, Transaction entity) {

		Session session = null;

		try {
			Transaction updateTransaction = find(id);
			updateTransaction.setBalance(entity.getBalance());
			updateTransaction.setDate(entity.getDate());
			updateTransaction.setTransactionName(entity.getTransactionName());
			updateTransaction.setTransactionNo(entity.getTransactionNo());

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateTransaction);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING Transactions data.");
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {

		Session session = null;

		try {
			Transaction deleteTransaction = find(id);
			if (deleteTransaction == null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteTransaction);
				session.getTransaction().commit();

				System.out.println("Succesfully deleted.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING Transaction data.");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Transaction> listAll() {

		Session session = databaseConnection();

		String hql = "SELECT trans FROM Transaction as trans";

		TypedQuery<Transaction> typedQuery = session.createQuery(hql, Transaction.class);
		List<Transaction> customerTransaction = typedQuery.getResultList();

		return customerTransaction;

	}

	@Override
	public Transaction find(long id) {

		Transaction transaction = null;
		Session session = databaseConnection();
		
		try {
			transaction = session.find(Transaction.class, id);
			
			if(transaction != null) {
				System.out.println("Found transaction : " + transaction);
			}else {
				System.out.println("Customer not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while finding transaction data.");
		} finally {
			session.close();
		}
		
		return transaction;
		
	}

}
