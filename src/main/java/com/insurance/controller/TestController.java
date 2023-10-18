package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.TestDto;
@RestController
public class TestController {
	@Autowired
	private Environment environment;
	
//design restful web service to return test obj
	@GetMapping("/getDriverDetails")
	public TestDto getTestDetails() {
		String driverName=environment.getProperty("spring.datasource.driver-class-name");
		String driverUrl=environment.getProperty("spring.datasource.url");
		String userName=environment.getProperty("spring.datasource.username");
		String password=environment.getProperty("spring.datasource.password");
		//set data into test obj
		TestDto test=new TestDto();
		test.setDriverName(driverName);
		test.setDriverUrl(driverUrl);
		test.setUserName(userName);
		test.setPassword(password);
		return test;
		
	}
}
