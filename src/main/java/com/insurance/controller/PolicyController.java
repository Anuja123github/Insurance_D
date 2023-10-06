package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping("/policy")
	public ResponseEntity<Policy> updatePolicyDetails(@RequestBody Policy Policy) {
		// CR-673
		Policy newPolicy = policyService.updatePolicyDetails(Policy);
		return ResponseEntity.status(HttpStatus.OK).body(newPolicy);
	}
	@GetMapping("/getPolicyById/{id}")
	public Policy getPolicyById (@PathVariable ("id") Integer id){
		
		Policy policy = policyService.getPolicyById(id);
		
		return policy;
	}
}
