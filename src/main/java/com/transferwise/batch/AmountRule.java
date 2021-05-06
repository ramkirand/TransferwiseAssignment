package com.transferwise.batch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AmountRule {
	
	public static Double rewardPointEcom(Double amount) {
		log.debug("In rewardPointEcom:", amount);
		if (amount < 2.5)
			return 0.0;
		if (amount < 5)
			return 1.0;
		if (amount < 10)
			return 2.0;
		if (amount < 100)
			return 3.0;
		if (amount < 300)
			return 0.07 * amount;
		if (amount < 1000)
			return 0.1 * amount;
		return 0.15 * amount;

	}

	public static Double rewardPointDefault(double amount) {
		log.debug("In rewardPointDefault:", amount);
		if (amount < 2.5)
			return 1.0;
		if (amount < 5)
			return 2.0;
		if (amount < 10)
			return 3.0;
		if (amount < 100)
			return 4.0;
		if (amount < 300)
			return 0.09 * amount;
		if (amount < 1000)
			return 0.11 * amount;
		return 0.13 * amount;

	}
}
