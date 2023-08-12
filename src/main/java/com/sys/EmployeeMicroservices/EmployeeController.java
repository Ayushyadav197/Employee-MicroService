package com.sys.EmployeeMicroservices;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepositery employeeRepositery;

	@GetMapping("/getgreetings/{username}")
	public String getGreetings(@PathVariable String username) {
		return "good morning --> " + username;
	}
	@GetMapping("/getwellwishes/{username}")
	public String getWellWishes(@PathVariable String username) {
		return "Keep It My Brother --> " + username;
	}
	@PostMapping("/saveemployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		System.out.println(employee);
		employee.getPhonenumber().stream().forEach(phonenumber->phonenumber.setEmployee(employee));
		return employeeRepositery.save(employee);
	}

	@GetMapping("/getallemployeelist")
	public java.util.List<Employee> getAllEmployee() {
		return CacheManager.cache.values().stream().collect(Collectors.toList());

	}

	@GetMapping("/getallemployeelistascending")
	public java.util.List<Employee> getAllEmployeeascending() {
		return employeeRepositery.findByOrderBySalaryAsc();
	}

	@GetMapping("/getallemployeelistdecending")
	public java.util.List<Employee> getAllEmployeedecending() {

		return employeeRepositery.findByOrderBySalaryDesc();
	}

	@GetMapping("/getemployeebyeid/{eid}")
	public String getEmployeeByEid(@PathVariable int eid) {
	Employee employee=	CacheManager.cache.get(eid);
	if(employee!=null) {
		return employee.toString();
	}else {
		return "Employee is not Present for this Eid -> " + eid;
	}
//		Optional<Employee> ee = employeeRepositery.findById(eid);
//		System.out.println(ee);
//		if (ee.isPresent()) {
//			return ee.toString();
//		} else {
//			return "Employee is not Present for this Eid -> " + eid;
//		}
	}

	@PutMapping("/updateemployeebycheckingeid/{eid}")
	public String updateEmployeeByEid(@PathVariable int eid, @RequestBody Employee employee) {
		Optional<Employee> ee = employeeRepositery.findById(eid);
		if (ee.isPresent()) {
			ee.get().setEname(employee.getEname());
			ee.get().setEsalary(employee.getEsalary());
			return employeeRepositery.save(ee.get()).toString();
		} else {
			return "Employee is not present for given eid --> " + eid;
		}
	}

	@DeleteMapping("/deletebyeid/{eid}")
	public String deleteByEid(@PathVariable int eid) {
		Optional<Employee> employee = employeeRepositery.findById(eid);
		if (employee.isPresent()) {
			employeeRepositery.deleteById(eid);
			return "Entry deleted successfully of eid --> " + eid;
		} else {
			return "Entry for this " + eid + " id not present";
		}
	}
@GetMapping("/gethighestsalaryemployee")	
public Employee getHighestSalaryEmployee() {
return	employeeRepositery.findMaxSalaryFromEmployee();
}

@GetMapping("/gethighestesalary")	
public java.util.List<Employee> getHighestSalaryEmployeeCustom() {
java.util.List<Employee> elist= employeeRepositery.findAll();
   java.util.List<Employee> eelist=elist.stream().filter(employee->employee.getEsalary()>=190000).collect(Collectors.toList());
  return eelist;
}













}
