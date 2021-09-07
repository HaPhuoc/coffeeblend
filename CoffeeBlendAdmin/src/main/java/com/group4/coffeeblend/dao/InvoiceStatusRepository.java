package com.group4.coffeeblend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group4.coffeeblend.model.InvoiceStatus;

public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Integer>{

}
