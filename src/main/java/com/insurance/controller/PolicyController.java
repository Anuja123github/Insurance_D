package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.ApiResponseDto;
import com.insurance.model.Policy;
import com.insurance.service.PolicyService;

@RestController
public class PolicyController {

	@Autowired
	private PolicyService policyService;

	@PostMapping("/policy")
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		// CR-672
		Policy newPolicy = policyService.savePolicy(policy);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPolicy);
	}

	@PutMapping("/policy")
	public ResponseEntity<Policy> updatePolicyDetails(@RequestBody Policy Policy) {
		// CR-673
		Policy newPolicy = policyService.updatePolicyDetails(Policy);
		return ResponseEntity.status(HttpStatus.OK).body(newPolicy);
	}
	
	@DeleteMapping("/policy/{id}")
	public ResponseEntity<ApiResponseDto> removePolicy(@PathVariable("id") Integer id) {
		// CR-686
		policyService.deletePolicy(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record is deleted."));
	}
}
