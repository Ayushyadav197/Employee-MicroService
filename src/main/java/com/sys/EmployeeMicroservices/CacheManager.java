package com.sys.EmployeeMicroservices;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheManager {
//this class is responsible for manage cache.
// In cache class we write Cron job
// For cacheManager we use hashmap datastructure,it is used for loading data.
	@Autowired
	EmployeeRepositery employeeRepositery;

	public static HashMap<Integer, Employee> cache = new HashMap<>();

// create cache--> Created/load Data	

    @Scheduled(cron = "0 * * * * *")
	public void loadData() {
		System.out.println("loading started");
		List<Employee> employeelist = employeeRepositery.findAll();
//now insert the employeelist in cache or load in cache
		employeelist.forEach(employee -> cache.put(employee.getEid(), employee));
		System.out.println("loading ended");
	}
}
