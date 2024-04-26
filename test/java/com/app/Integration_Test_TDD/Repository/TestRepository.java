package com.app.Integration_Test_TDD.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Integration_Test_TDD.Model.Employee;
@Repository
public interface TestRepository extends JpaRepository<Employee, Long>{

}
