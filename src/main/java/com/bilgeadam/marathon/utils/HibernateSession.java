package com.bilgeadam.marathon.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bilgeadam.marathon.entity.Account;
import com.bilgeadam.marathon.entity.Branch;
import com.bilgeadam.marathon.entity.Customer;
import com.bilgeadam.marathon.entity.Transaction;


public class HibernateSession {
	
	private static SessionFactory sessionFactory;
	
	
	
	
	
	public static SessionFactory getSessionFactory() {    
	        
	        if(HibernateSession.sessionFactory == null ) {    
	            HibernateSession.sessionFactory = createSessionFactory();
	            }
	        return HibernateSession.sessionFactory;
	        
	        
	    }





	private static SessionFactory createSessionFactory() {
		Configuration conf = new Configuration();
		
		conf.addAnnotatedClass(Account.class);
		conf.addAnnotatedClass(Branch.class);
		conf.addAnnotatedClass(Customer.class);
		conf.addAnnotatedClass(Transaction.class);
		
		SessionFactory sessionFactory = conf.configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println("Connection to PostgreSQL via Hibernate is succesful.");
		
		return sessionFactory;
	}

}
