package com.transferwise.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transferwise.model.Customer;
import com.transferwise.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DBWriter implements ItemWriter<Customer> {

	private static Map<String, Double> MAP_CUSTOMER_ID = new HashMap<>();
	private static Map<String, String> MAP_CUSTOMER_NAME = new HashMap<String, String>();

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void write(List<? extends Customer> customers) throws Exception {
		for (Customer customer : customers) {
			MAP_CUSTOMER_ID.put(customer.getCustomerId(), MAP_CUSTOMER_ID.getOrDefault(customer.getCustomerId(), 0.0)
					+ customer.getRewardPointForOneTransaction());

			MAP_CUSTOMER_NAME.put(customer.getCustomerId(), customer.getCustomerName());
			customerRepository.saveAll(customers);
		}
	}

	public static void footerNote() {
		log.info("Expected Output as below \n");
		MAP_CUSTOMER_ID.forEach((k, v) -> {
			if (!k.equals(null) && !k.isEmpty())
				log.info("Transaction ID:" + k + ", Customer ID: " + MAP_CUSTOMER_NAME.get(k) + ", Reward Points: "
						+ Math.floor(Math.max(0, v)));

		});
		log.info("\n");
	}
}
