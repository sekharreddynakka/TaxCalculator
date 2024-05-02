package com.employeeTax.TaxCalculation.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeTax.TaxCalculation.model.Employee;
import com.employeeTax.TaxCalculation.model.Tax;
import com.employeeTax.TaxCalculation.repository.TaxRepository;

@Service
public class TaxCalculationService {
 
	
	@Autowired
	private  TaxRepository taxRepository;

   
    
    public void  calculateTax(Employee e ) {
    	Double salary = (double)calculateTotalSalary(e.getSalary().longValue(),LocalDate.parse(e.getDoj()));
    	Double tax = 0.0;
    	Double cess =0.0;
        if (salary <= 250000) {
            tax = 0.0;
            System.out.println(tax);
        } else if (salary > 250000 && salary <= 500000) {
            tax = ((salary - 250000) * 0.05);
            System.out.println(tax);
        } else if (salary > 500000 && salary <= 1000000) {
            tax =  (12500 + (salary - 500000) * 0.1);
            System.out.println(tax);
        } else {
            tax = (62500 + (salary - 1000000) * 0.2);
            System.out.println(tax);
        }
        if (salary > 250000) {
        cess =((salary - 250000) * 0.02);
        System.out.println(cess);
        }
        
        
        Tax taxModel = new Tax();
        
        taxModel.setEmpId(e.getId());
        taxModel.setFirstName(e.getFirstName());
        taxModel.setLastName(e.getLastName());
        taxModel.setSalary(e.getSalary());
        taxModel.setTaxAmt(tax);
        taxModel.setCessAmt(cess);
        
        
        taxRepository.save(taxModel);
        
        
      
    }
    
    
     
    
    public static long calculateTotalSalary(long salary, LocalDate doj) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int dojYear = doj.getYear();

        if (currentYear != dojYear) {
            return salary; // If DOJ is in a different year, return full salary
        } else {
            long totalSalary = 0;
            for (int month = 1; month <= 12; month++) {
                LocalDate firstDayOfMonth = LocalDate.of(currentYear, month, 1);
                LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

                if (doj.isAfter(lastDayOfMonth)) {
                    continue; // Skip the month if DOJ is after the last day of the month
                }

                LocalDate start = (doj.isAfter(firstDayOfMonth) ? doj : firstDayOfMonth);
                LocalDate end = (doj.isAfter(firstDayOfMonth) ? lastDayOfMonth : doj.minusDays(1));

                long daysInMonth = start.until(end).getDays() + 1;
                double proportion = (double) daysInMonth / firstDayOfMonth.lengthOfMonth();
                totalSalary += (long) (proportion * salary);
            }
            return totalSalary;
        }
    }
}

