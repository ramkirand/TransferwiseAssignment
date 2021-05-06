package com.transferwise.batch;

import org.springframework.stereotype.Component;

import com.transferwise.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ComputeRewardPoint {

	public Double getRewardPoint(Customer customer) {
		Double rewardPoint = 0.0;
		if (customer != null && customer.getTransactionType() != null && customer.getTransactionAmount() != null
				&& customer.getMerchantType() != null) {
			log.debug("{}", customer.getTransactionType() + "," + customer.getMerchantType() + ","
					+ customer.getTransactionAmount());

			if (customer.getTransactionType().equals("ECOM")) {
				log.debug("{} ", customer.getMerchantType());
				rewardPoint = AmountRule.rewardPointEcom(Math.abs(customer.getTransactionAmount()));
			} else {
				log.debug("{} ", customer.getMerchantType());
				if (customer.getTransactionAmount() != null)
					rewardPoint = AmountRule.rewardPointDefault(Math.abs(customer.getTransactionAmount()));

			}
			if (customer.getMerchantType().equals("Airlines")) {
				log.debug("{}", customer.getMerchantType());
				rewardPoint *= 2;

			}

			if (customer.getTransactionAmount() < 0)
				rewardPoint *= -1;

		}
		return rewardPoint;
	}
}
