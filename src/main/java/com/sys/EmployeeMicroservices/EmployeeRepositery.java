package com.sys.EmployeeMicroservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepositery extends JpaRepository<Employee,Integer>{
	
@Query(value="SELECT * FROM Employee ORDER BY esalary ASC",nativeQuery = true)	
public	List<Employee> findByOrderBySalaryAsc();

@Query(value="SELECT * FROM Employee ORDER BY esalary DESC",nativeQuery = true)
public	List<Employee> findByOrderBySalaryDesc();

@Query(value="select * from employee where esalary=(select Max(esalary) from employee)",nativeQuery = true)
public Employee findMaxSalaryFromEmployee();
}
