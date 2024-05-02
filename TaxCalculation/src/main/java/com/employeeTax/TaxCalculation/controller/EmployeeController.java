package com.employeeTax.TaxCalculation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employeeTax.TaxCalculation.model.Employee;
import com.employeeTax.TaxCalculation.model.Tax;
import com.employeeTax.TaxCalculation.repository.EmployeeRepository;
import com.employeeTax.TaxCalculation.repository.TaxRepository;
import com.employeeTax.TaxCalculation.service.TaxCalculationService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TaxRepository taxRepository;

	@Autowired
	private 	TaxCalculationService	taxCalculationService;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {

		if (employee.getFirstName().isBlank()) {
			return new ResponseEntity<Object>("First name is mandetory ", HttpStatus.ALREADY_REPORTED);
			
			
			
		}else if (employee.getDoj().isBlank() ) {
			return new ResponseEntity<Object>("Doj is mandetory ", HttpStatus.ALREADY_REPORTED);
			
			
		}else if (employee.getLastName().isBlank())	{
			return new ResponseEntity<Object>("Last Name is mandetory ", HttpStatus.ALREADY_REPORTED);
			
		}else if (employee.getSalary()==null ||employee.getSalary().isNaN() ) {
			return new ResponseEntity<Object>("Salary is mandetory / Invalid ", HttpStatus.ALREADY_REPORTED);
		}else if ( employee.getPhoneNumber()==null) {
			return new ResponseEntity<Object>("Phone Number is mandetory ", HttpStatus.ALREADY_REPORTED);
			
		}else if (employee.getEmail().isBlank()) {
			
			return new ResponseEntity<Object>("Email is mandetory ", HttpStatus.ALREADY_REPORTED);
		} else {
			Employee craete= employeeRepository.save(employee); 
			 return new ResponseEntity<Object>(craete,HttpStatus.CREATED); 
		}

				
		  
		  
		 
	}

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		// Retrieve employee by ID
		return employeeRepository.findById(id).orElse(null);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		// Delete employee by ID
		employeeRepository.deleteById(id);
	}

	@GetMapping("/calculateTax")
	public List<Tax> calculateTax() {
		// Retrieve employee salary by ID and calculate tax
		List<Employee> employee = employeeRepository.findAll();
		try {
			if (employee != null) {
				
				employee.stream().forEach( n -> 	taxCalculationService.calculateTax(n));
				return taxRepository.findAll();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; // or throw exception
	}
}
