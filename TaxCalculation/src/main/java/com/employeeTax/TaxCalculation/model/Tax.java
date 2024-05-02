package com.employeeTax.TaxCalculation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tax {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	 private Long empId; 
    private String firstName;
    private Double salary;
    private String lastName ;
    private Double taxAmt;
    private Double cessAmt;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Double getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(Double tax) {
		this.taxAmt = tax;
	}
	public Double getCessAmt() {
		return cessAmt;
	}
	public void setCessAmt(Double cessAmt) {
		this.cessAmt = cessAmt;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
    
    

}
