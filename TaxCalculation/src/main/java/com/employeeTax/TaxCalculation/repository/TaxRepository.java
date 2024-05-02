package com.employeeTax.TaxCalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employeeTax.TaxCalculation.model.Tax;

public interface TaxRepository  extends JpaRepository<Tax, Long> {

}
