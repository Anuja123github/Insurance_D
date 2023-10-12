package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Policy savePolicy(@RequestBody Policy policy) {
		Policy policy1=policyService.savePolicy(policy);
		List<Claim>claims=policy.getClaimlist();
		for(Claim claim:claims ) {
			claim.setPolicyId(policy.getId());
			claimService.saveClaim(claim);
		}
		return policy1;
		
	}
	@DeleteMapping("/delete/{id}")
	public void deleteClaimById(@PathVariable ("id") Integer id) {
		claimService.deleteClaimById(id);
	}


}
