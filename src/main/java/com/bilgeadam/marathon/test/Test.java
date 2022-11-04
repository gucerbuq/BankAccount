package com.bilgeadam.marathon.test;

import com.bilgeadam.marathon.utils.HibernateSession;

public class Test {

	public static void main(String[] args) {
		
		HibernateSession.getSessionFactory().openSession();

	}

}
