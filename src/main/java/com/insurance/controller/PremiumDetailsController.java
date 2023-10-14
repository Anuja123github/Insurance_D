package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.exception.ResourceNotFoundException;
import com.insurance.model.Policy;
import com.insurance.model.PremiumDetails;
import com.insurance.service.PolicyService;
import com.insurance.service.PremiumDetailsService;

@RestController
public class PremiumDetailsController {

	@Autowired
	private PremiumDetailsService premiumDetailsService;

	@Autowired
	private PolicyService policyService;

	@PostMapping("/savePremiumDetails/{id}")
	public ResponseEntity<PremiumDetails> savePremiumDetailsByPolicyId(@PathVariable("id") Integer id,
			// CR-785
			@RequestBody PremiumDetails premiumDetails) {
		Policy policy = policyService.getPolicyById(id);
		if (policy != null) {
			premiumDetails.setPolicyId(policy.getId());
			PremiumDetails premiumDetails1 = premiumDetailsService.addPremium(premiumDetails);
			return ResponseEntity.status(HttpStatus.OK).body(premiumDetails1);
		}
		throw new ResourceNotFoundException("No Policy found with id: " + id);
	}
}