package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.model.Claim;
import com.insurance.model.Policy;
import com.insurance.service.ClaimService;
import com.insurance.service.PolicyService;

@RestController
public class ClaimController {
	
	//inject service
	@Autowired
	private ClaimService claimService;
	@Autowired
	private PolicyService policyService;
	@PostMapping("/saveClaim")
	public ResponseEntity<Policy> savePolicy(@RequestBody Policy policy) {
		Policy policy1=policyService.savePolicy(policy);
		List<Claim>claims=policy.getClaimlist();
		for(Claim claim:claims ) {
			claim.setPolicyId(policy.getId());
			claimService.saveClaim(claim);
		}
		return ResponseEntity.ok().body(policy1);
		
	}


}
