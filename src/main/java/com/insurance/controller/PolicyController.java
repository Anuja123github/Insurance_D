package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.model.Policy;
import com.insurance.service.PolicyService;

@RestController
public class PolicyController {

	@Autowired
	private PolicyService policyService;
	
	@PostMapping("/policy")
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		Policy newPolicy = policyService.savePolicy(policy);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPolicy);
	}
}
