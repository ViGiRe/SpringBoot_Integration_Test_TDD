package com.app.Integration_Test_TDD;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.app.Integration_Test_TDD.Model.Employee;
import com.app.Integration_Test_TDD.Repository.TestRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTestTddApplicationTests {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRepository repository;
	
	private String baseURL = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	@BeforeEach
	public void setUp() {
		baseURL = baseURL.concat(":").concat(port+"").concat("/emp");
	}
	
	@Test
	public void testSaveData() {
		Employee employee = new Employee("Aniket","HCL",18000.00);
		Employee response = restTemplate.postForObject(baseURL, employee, Employee.class);
		assertEquals("Aniket", response.getName());
		assertEquals(1, repository.findAll().size());
	}
	
	@Test
	@Sql(statements = "INSERT INTO EMPLOYEE(id,name,comName,sal) VALUES (4,'Rahul','Cognizant',34500.0)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM EMPLOYEE WHERE name ='Rahul'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetAllEmployee() {
		List<Employee> employee = restTemplate.getForObject(baseURL, List.class);
		assertEquals(1, employee.size());
		
	}
	
	
	@Test
	@Sql(statements = "INSERT INTO EMPLOYEE(id,name,comName,sal) VALUES (5,'Ani','Cognizant',65500.0)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM EMPLOYEE WHERE id = 1",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testFindEmployeeById() {
		Employee employee = restTemplate.getForObject(baseURL+"{/id}", Employee.class, 1);
		assertNotNull(employee);
		assertEquals(1, Employee.class);
		assertEquals("Ani", employee.getName());
	
	}
	
	@Test
	@Sql(statements = "INSERT INTO EMPLOYEE(id,name,comName,sal) VALUES (6,'Suraj','TCS',65500.0)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM EMPLOYEE WHERE id = 1",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEmployee() {
		Employee employee = new Employee("Suraj", "TCS", 15000);
		restTemplate.put(baseURL+"/emp/{id}", employee, 6);
		Employee employeeByDb = repository.findById((long) 6).get();
		
		assertNotNull(employeeByDb);
		assertEquals(157000, employeeByDb.getSal());
	}
	
	@Test
	@Sql(statements = "INSERT INTO EMPLOYEE(id,name,comName,sal) VALUES (7,'Dhanu','TCS',65500.0)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testDeleteEmployee() {
		int recordCount = repository.findAll().size();
		assertEquals(1, recordCount);
		restTemplate.delete(baseURL+"/emp/{id}", 7);
		assertEquals(1, repository.findAll().size());
	}
}

//	@Test
//	void contextLoads() {
//	}


