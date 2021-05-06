package com.transferwise.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.transferwise.batch.ComputeRewardPoint;
import com.transferwise.model.Customer;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ComputeRewardPointTest {

	ComputeRewardPoint computeRewardPoint;
	Customer customer;

	@Autowired
	TestRestTemplate resttemplate;

	@Before
	public void init() {
		computeRewardPoint = new ComputeRewardPoint();
	}

	@Test
	public void shouldNotReturnRewardPoint() {
		assertTrue(computeRewardPoint.getRewardPoint(customer) == 0.0);
	}

	@Test
	public void shouldMatchForAmountLessThan0_ECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(0.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  for 0 ",
				computeRewardPoint.getRewardPoint(customer) == 0.15 * customer.getTransactionAmount());

	}

	@Test
	public void shouldMatchForValidRewardPoint() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(300.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  amount < 400", computeRewardPoint.getRewardPoint(customer) == 30.0);

	}

	@Test
	public void shouldMatchForAmountLessThanTwoPointFive_ECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(2.4);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  amount < 2.5", computeRewardPoint.getRewardPoint(customer) == 0);

	}

	@Test
	public void shouldMatchForAmountLessThan5_ECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(4.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  amount < 5", computeRewardPoint.getRewardPoint(customer) == 1);

	}

	@Test
	public void shouldMatchForAmountLessThan10_ECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(9.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  < 10", computeRewardPoint.getRewardPoint(customer) == 2);

	}

	@Test
	public void shouldMatchForAmountLessThan100_ECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(99.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  < 100", computeRewardPoint.getRewardPoint(customer) == 3);

	}

	@Test
	public void shouldMatchForAmountLessThan300_ECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(200.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  < 300",
				computeRewardPoint.getRewardPoint(customer) == 0.07 * customer.getTransactionAmount());

	}

	@Test
	public void shouldMatchForAmountLessThan1000_ECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(886.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Anglo");
		assertTrue("Validate reward point  < 1000 ",
				computeRewardPoint.getRewardPoint(customer) == 0.1 * customer.getTransactionAmount());

	}

	/* For Merchant Type = Airlines */

	@Test
	public void shouldMatchForAmountLessThan0_Airlines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(0.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Airlines");
		assertTrue("Validate reward point  for 0 ",
				computeRewardPoint.getRewardPoint(customer) == 0.15 * customer.getTransactionAmount());

	}

	@Test
	public void shouldMatchForValidRewardPoint_Arilines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(300.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Airlines");
		assertTrue("Validate reward point  amount < 400", computeRewardPoint.getRewardPoint(customer) == 60.0);

	}

	@Test
	public void shouldMatchForAmountLessThanTwoPointFive_Airlines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(2.4);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Airlines");
		assertTrue("Validate reward point  amount < 2.5", computeRewardPoint.getRewardPoint(customer) == 0);

	}

	@Test
	public void shouldMatchForAmountLessThan5_Airlines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(4.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Airlines");
		assertTrue("Validate reward point  amount < 5", computeRewardPoint.getRewardPoint(customer) == 2.0);

	}

	@Test
	public void shouldMatchForAmountLessThan10_Airlines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(9.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Airlines");
		assertTrue("Validate reward point  < 10", computeRewardPoint.getRewardPoint(customer) == 4.0);

	}

	@Test
	public void shouldMatchForAmountLessThan100_Airlines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(99.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Airlines");
		assertTrue("Validate reward point  < 100", computeRewardPoint.getRewardPoint(customer) == 6);

	}

	@Test
	public void shouldMatchForAmountLessThan300_Airlines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(200.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Arilines");
		assertTrue("Validate reward point  < 300",
				computeRewardPoint.getRewardPoint(customer) == 0.07 * customer.getTransactionAmount());

	}

	@Test
	public void shouldMatchForAmountLessThan1000_Airlines() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(886.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Airlines");
		assertTrue("Validate reward point  < 1000 ",
				computeRewardPoint.getRewardPoint(customer) == 2 * 0.1 * customer.getTransactionAmount());

	}

	@Test
	public void shouldgetRewardPointECOM() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOM");
		customer.setTransactionAmount(886.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Meenu2");
		assertTrue("Validate reward point  < 1000 ",
				computeRewardPoint.getRewardPoint(customer) == 0.1 * customer.getTransactionAmount());

	}

	@Test
	public void shouldgetRewardPointDefault() {
		customer = new Customer();
		customer.setBankName("SBI");
		customer.setTransactionType("ECOMM");
		customer.setTransactionAmount(886.0);
		customer.setMerchantName("Ramdev");
		customer.setMerchantType("Meenu");
		assertTrue("Validate reward point  < 1000 ",
				computeRewardPoint.getRewardPoint(customer) == 0.11 * customer.getTransactionAmount());

	}
}
