package com.bilgeadam.marathon.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTIONS")
@ToString

public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private LocalDate date;
	@Column
	private long transactionNo;
	@Column
	private String transactionName;
	@Column
	private int balance;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
	private Account account;

}
