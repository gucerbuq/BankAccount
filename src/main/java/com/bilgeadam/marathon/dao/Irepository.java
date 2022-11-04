package com.bilgeadam.marathon.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.marathon.utils.HibernateSession;




public interface Irepository<T> {
	
public void create(T entity);
	
	public void update(long id, T entity);
	
	public void delete(long id);

	List<T> listAll();
	
	T find(long id);
	
	default Session databaseConnection() {
		return HibernateSession.getSessionFactory().openSession();
	}

}
