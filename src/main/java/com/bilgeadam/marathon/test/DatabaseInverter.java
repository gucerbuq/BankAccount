package com.bilgeadam.marathon.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.marathon.dao.AccountDao;
import com.bilgeadam.marathon.dao.BranchDao;
import com.bilgeadam.marathon.dao.CustomerDao;
import com.bilgeadam.marathon.dao.TransactionDao;
import com.bilgeadam.marathon.entity.Account;
import com.bilgeadam.marathon.entity.Branch;
import com.bilgeadam.marathon.entity.Customer;
import com.bilgeadam.marathon.entity.Transaction;
import com.bilgeadam.marathon.utils.HibernateSession;

public class DatabaseInverter {
	
	static CustomerDao customerDao = new CustomerDao();
	static BranchDao branchDao = new BranchDao();
	static TransactionDao transactionDao = new TransactionDao();
	static AccountDao accountDao = new AccountDao();
	
	public static Session databaseConnection = HibernateSession.getSessionFactory().openSession();
	
	public static void dataInsert() {
		
		Branch branch1 = new Branch();
		branch1.setBranchNo(67);
		branch1.setBranchName("Zonguldak");
		Branch branch2 = new Branch();
		branch2.setBranchNo(74);
		branch2.setBranchName("Bartın");
		Branch branch3 = new Branch();
		branch3.setBranchNo(78);
		branch3.setBranchName("Karabük");
		
		branchDao.create(branch1);
		branchDao.create(branch2);
		branchDao.create(branch3);
		

		Account account1 = new Account();
		account1.setAccountNo(7410010);
		account1.setAccountType("TL");
		Account account2 = new Account();
		account2.setAccountNo(6710011);
		account2.setAccountType("EURO");
		Account account3 = new Account();
		account3.setAccountNo(7810112);
		account3.setAccountType("USD");
		Account account4 = new Account();
		account4.setAccountNo(7410210);
		account4.setAccountType("TL");
		
		accountDao.create(account1);
		accountDao.create(account2);
		accountDao.create(account3);
		accountDao.create(account4);
		
		
		Transaction transaction1 = new Transaction();
		transaction1.setDate(LocalDate.of(2022, 1, 2));
		transaction1.setTransactionNo(100001);
		transaction1.setTransactionName("Deposit");
		transaction1.setBalance(400);
		transaction1.setAccount(account1);
		Transaction transaction2 = new Transaction();
		transaction2.setDate(LocalDate.of(2022, 1, 25));
		transaction2.setTransactionNo(100002);
		transaction2.setTransactionName("Withdraw");
		transaction2.setBalance(200);
		transaction2.setAccount(account1);
		Transaction transaction3 = new Transaction();
		transaction3.setDate(LocalDate.of(2022, 1, 20));
		transaction3.setTransactionNo(100003);
		transaction3.setTransactionName("Withdraw");
		transaction3.setBalance(89);
		transaction3.setAccount(account1);
		Transaction transaction4 = new Transaction();
		transaction4.setDate(LocalDate.of(2022, 1, 15));
		transaction4.setTransactionNo(100025);
		transaction4.setTransactionName("Deposit");
		transaction4.setBalance(200);
		transaction4.setAccount(account2);
		Transaction transaction5 = new Transaction();
		transaction5.setDate(LocalDate.of(2022, 2, 2));
		transaction5.setTransactionNo(100058);
		transaction5.setTransactionName("Withdraw");
		transaction5.setBalance(100);
		transaction5.setAccount(account3);
		Transaction transaction6 = new Transaction();
		transaction6.setDate(LocalDate.of(2022, 1, 20));
		transaction6.setTransactionNo(101005);
		transaction6.setTransactionName("Deposit");
		transaction6.setBalance(200);
		transaction6.setAccount(account3);
		Transaction transaction7 = new Transaction();
		transaction7.setDate(LocalDate.of(2022, 1, 20));
		transaction7.setTransactionNo(101048);
		transaction7.setTransactionName("Withdraw");
		transaction7.setBalance(115);
		transaction7.setAccount(account4);
		
		transactionDao.create(transaction1);
		transactionDao.create(transaction2);
		transactionDao.create(transaction3);
		transactionDao.create(transaction4);
		transactionDao.create(transaction5);
		transactionDao.create(transaction6);
		transactionDao.create(transaction7);
		
		List<Account> accountList1 = new ArrayList<>();
		accountList1.add(account1);
		accountList1.add(account2);
		List<Account> accountList2 = new ArrayList<>();
		accountList2.add(account3);
		accountList2.add(account4);
		
		Customer customer1 = new Customer();
		customer1.setFirstName("Ali");
		customer1.setLastName("Kara");
		customer1.setCustomerNo(100);
		customer1.setAccountList(accountList1);
		Customer customer2 = new Customer();
		customer2.setFirstName("Ayşe");
		customer2.setLastName("Sarı");
		customer2.setCustomerNo(101);
		customer2.setAccountList(accountList2);
		
		
		customerDao.create(customer1);
		customerDao.create(customer2);
		
		
		
		
	}

	


	
	

}
