package com.bilgeadam.marathon.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor 
@Entity
@Table(name = "ACCOUNT")
@ToString

public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private long accountNo;
	@Column
	private String accountType;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customerId", nullable = true, referencedColumnName = "id")
	private Customer customer;
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactionList;

}
