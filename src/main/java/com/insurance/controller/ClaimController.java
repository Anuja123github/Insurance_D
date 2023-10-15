package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.ApiResponseDto;
import com.insurance.exception.ResourceNotFoundException;
import com.insurance.model.Claim;
import com.insurance.model.Policy;
import com.insurance.service.ClaimService;
import com.insurance.service.PolicyService;

@RestController
public class ClaimController {

	// inject service
	@Autowired
	private ClaimService claimService;
	@Autowired
	private PolicyService policyService;

	@PostMapping("/saveClaim")
	public Policy savePolicy(@RequestBody Policy policy) {
		Policy policy1 = policyService.savePolicy(policy);
		List<Claim> claims = policy.getClaimlist();
		for (Claim claim : claims) {
			claim.setPolicyId(policy.getId());
			claimService.saveClaim(claim);
		}
		return policy1;

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponseDto> deleteClaim(@PathVariable("id") Integer id) {

		claimService.deleteClaimById(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record is deleted."));
	}

	@GetMapping("/get-all-claim-list")
	public ResponseEntity<List<Claim>> getClaimList() {
		// CR-772
		List<Claim> claimList = claimService.getAllClaimsList();
		return ResponseEntity.status(HttpStatus.OK).body(claimList);
	}
	
	@PutMapping("/updatePolicyClaim")
	public ResponseEntity<Policy> updatePolicyClaims(@RequestBody Policy policy) {
		// CR-771
		Policy updatedPolicy = policyService.updatePolicyDetails(policy);
		List<Claim> claims = policy.getClaimlist();
		for (Claim claim : claims) {
			claim.setPolicyId(updatedPolicy.getId());
			claimService.updateClaim(claim);
		}
		return ResponseEntity.status(HttpStatus.OK).body(updatedPolicy);
	}

	@PostMapping("/policy/id/claims/{id}")
	public ResponseEntity<List<Claim>> addClaimDetailsForPolicy(@PathVariable("id") Integer id,
			@RequestBody List<Claim> claimlist) {
		// CR-784
		Policy policy = policyService.getPolicyById(id);
		if (policy == null) {
			throw new ResourceNotFoundException("No Policy found with id: " + id);
		} else {
			for (Claim claim : claimlist) {
				claim.setPolicyId(policy.getId());
			}
			List<Claim> claimList1 = claimService.saveAllClaims(claimlist);
			return ResponseEntity.status(HttpStatus.OK).body(claimList1);
		}
	}
}
