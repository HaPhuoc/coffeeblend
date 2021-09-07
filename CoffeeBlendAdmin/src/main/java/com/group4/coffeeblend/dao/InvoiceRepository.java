package com.group4.coffeeblend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.coffeeblend.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
