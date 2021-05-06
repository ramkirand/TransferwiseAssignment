package com.transferwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferwise.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
