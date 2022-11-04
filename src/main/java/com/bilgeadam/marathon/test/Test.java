package com.bilgeadam.marathon.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bilgeadam.marathon.dao.AccountDao;
import com.bilgeadam.marathon.dao.BranchDao;
import com.bilgeadam.marathon.dao.CustomerDao;
import com.bilgeadam.marathon.dao.TransactionDao;
import com.bilgeadam.marathon.entity.Account;
import com.bilgeadam.marathon.entity.Branch;
import com.bilgeadam.marathon.entity.Customer;
import com.bilgeadam.marathon.entity.Transaction;

public class Test {

	public static void main(String[] args) {

		DatabaseInverter.dataInsert();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Bank System!");
		System.out.println("---------------------------------------------------------------");

		int choosing = 1;

		while (choosing != 5) {
			System.out.println("1 - Show Customer");
			System.out.println("2 - Show Branches");
			System.out.println("3 - Show Accounts");
			System.out.println("4 - Show Transactions");
			System.out.println("5 - EXIT\n");
			System.out.print("Enter your choosing : ");
			choosing = scanner.nextInt();

			switch (choosing) {
			case 1:
				showCustomers();
				break;
			case 2:
				showBranches();
				break;
			case 3:
				showAccounts();
				break;
			case 4:
				showTransactions();
				break;

			default:
				System.err.println("Invalid selection!");
				break;
			}
		}

		System.out.println("Exit");
		
		scanner.close();

	}

	private static void showTransactions() {
		
		List<Transaction> transactions = new ArrayList<>();
		
		transactions = new TransactionDao().listAll();
		
		for (Transaction transaction : transactions) {
			System.out.println(transaction.getId() + " " + transaction.getTransactionNo() + " " + transaction.getAccount().getAccountNo() + " " + transaction.getTransactionName() + " " + transaction.getBalance() + " " + transaction.getDate());
		}
		
	}

	private static void showAccounts() {
		
		List<Account> accounts = new ArrayList<>();
		
		accounts = new AccountDao().listAll();
		
		for (Account account : accounts) {
			System.out.println(account.getId() + " " + account.getAccountNo() + " " + account.getAccountType());
		}
		
	}

	private static void showBranches() {

		List<Branch> branches = new ArrayList<Branch>();

		branches = new BranchDao().listAll();

		for (Branch branch : branches) {
			
			System.out.println(branch.getId() + " " + branch.getBranchNo() + " " + branch.getBranchName());

		}

	}

	private static void showCustomers() {

		List<Customer> customers = new ArrayList<Customer>();

		customers = new CustomerDao().listAll();

		for (Customer customer : customers) {
			System.out.println(customer.getId() + " " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getCustomerNo() + " " + customer.getAccountList());
//			customer.toString();
		}

	}

}
