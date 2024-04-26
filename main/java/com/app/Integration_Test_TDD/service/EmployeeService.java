package com.app.Integration_Test_TDD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Integration_Test_TDD.Model.Employee;
import com.app.Integration_Test_TDD.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveData(Employee emp) {
		
		return employeeRepository.save(emp);
	}

	public void deleteData(Long id) {
		employeeRepository.deleteById(id);
	}

	public Employee getEmployee(Long id) {
		Employee e= employeeRepository.findById(id).get();
		return e;
	}

	public List<Employee> getAllEmployee() {
		
		return (List<Employee>) employeeRepository.findAll();
	}

	public Employee updateData(Employee emp, Long id) {
		Optional<Employee> op = employeeRepository.findById(id);
		if (op.isPresent()) {
			Employee e = op.get();
			e.setComName(emp.getComName());
			e.setSal(emp.getSal());
			e.setName(emp.getName());
			employeeRepository.save(e);
			return e;
		}else {
			return null;
		}
		
	}
}
