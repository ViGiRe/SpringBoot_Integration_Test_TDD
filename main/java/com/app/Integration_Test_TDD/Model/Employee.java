package com.app.Integration_Test_TDD.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Company Name")
	private String comName;
	
	@Column(name = "salary")
	private double sal;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the comName
	 */
	public String getComName() {
		return comName;
	}
	/**
	 * @param comName the comName to set
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}

	public Employee(String name, String comName, double sal) {
		super();
		this.name = name;
		this.comName = comName;
		this.sal = sal;
	}


	
	
}
