package com.employeeTax.TaxCalculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeTax.TaxCalculation.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can add custom query methods if needed
}
