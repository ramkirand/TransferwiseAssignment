package com.transferwise.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;
	private String transactionId;
	private String CustomerId;
	private String CustomerName;
	private String bankName;
	private Double transactionAmount;
	private String merchantName;
	private String merchantType;
	private String transactionCountry;
	private String transactionType;
	private Double rewardPointForOneTransaction;
}
