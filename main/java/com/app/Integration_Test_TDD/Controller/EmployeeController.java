package com.app.Integration_Test_TDD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Integration_Test_TDD.Model.Employee;
import com.app.Integration_Test_TDD.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping("/emp")
	private Employee saveData(@RequestBody Employee emp) {
		Employee e = employeeService.saveData(emp);
		return e;
		
	}
	
	@DeleteMapping("/emp/{id}")
	private void deleteData(@PathVariable Long id) {
		employeeService.deleteData(id);
	}
	
	@GetMapping("/emp/{id}")
	private Employee getEmployee(@PathVariable Long id) {
		Employee e = employeeService.getEmployee(id);
		return e;
	}
	
	@GetMapping("/emps")
	private List<Employee> getAllEmployee() {
	
		return employeeService.getAllEmployee();
	}
	@PutMapping("/emp/{id}")
	private Employee updateEmployee(@RequestBody Employee emp, @PathVariable Long id) {
		
		Employee e = employeeService.updateData(emp,id);
		return e;
		
	}
}
