package com.bilgeadam.marathon.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.marathon.entity.Branch;

import jakarta.persistence.TypedQuery;

public class BranchDao implements Irepository<Branch> {

	@Override
	public void create(Branch entity) {
		
		Session session = null;

		try {
			session = databaseConnection();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Branch data is added to db.");
		} catch (Exception e) {
			e.getMessage();
			System.err.println("Some problem occured while adding branch data");
		} finally {
			session.close();
		}
		
	}

	@Override
	public void update(long id, Branch entity) {
		
		Session session = null;

		try {
			Branch updateBranch = find(id);
			updateBranch.setBranchName(entity.getBranchName());
			updateBranch.setBranchNo(entity.getBranchNo());

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateBranch);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING Branch data.");
		} finally {
			session.close();
		}
		
	}

	@Override
	public void delete(long id) {
		
		Session session = null;

		try {
			Branch deleteBranch = find(id);
			if (deleteBranch == null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteBranch);
				session.getTransaction().commit();

				System.out.println("Succesfully deleted.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING Branch data.");
		} finally {
			session.close();
		}
		
	}

	@Override
	public List<Branch> listAll() {
		
		Session session = databaseConnection();

		String hql = "SELECT branch FROM Branch as branch";

		TypedQuery<Branch> typedQuery = session.createQuery(hql, Branch.class);
		List<Branch> customerBranch = typedQuery.getResultList();

		return customerBranch;
		
	}

	@Override
	public Branch find(long id) {
		
		Branch branch = null;
		Session session = databaseConnection();
		
		try {
			branch = session.find(Branch.class, id);
			
			if(branch != null) {
				System.out.println("Found customer branch : " + branch);
			}else {
				System.out.println("Customer not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while finding customer data.");
		} finally {
			session.close();
		}
		
		return branch;
		
	}

}
