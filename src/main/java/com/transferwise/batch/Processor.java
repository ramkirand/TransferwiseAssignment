package com.transferwise.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transferwise.model.Customer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class Processor implements ItemProcessor<Customer, Customer> {
	
	@Autowired
	ComputeRewardPoint computeRewardPoint;

	public Processor() {

	}

	@Override
	public Customer process(Customer customer) throws Exception {
		customer.setRewardPointForOneTransaction(computeRewardPoint.getRewardPoint(customer));
		log.debug("In process:", customer.getRewardPointForOneTransaction());
		return customer;
	}
}
