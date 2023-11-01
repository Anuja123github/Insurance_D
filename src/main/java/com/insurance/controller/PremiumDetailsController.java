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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.exception.ResourceNotFoundException;
import com.insurance.model.Policy;
import com.insurance.model.PremiumDetails;
import com.insurance.service.PolicyService;
import com.insurance.service.PremiumDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PremiumDetailsController {

	@Autowired
	private PremiumDetailsService premiumDetailsService;

	@Autowired
	private PolicyService policyService;

	@PostMapping("/savePremiumDetails/{id}")
	@ApiOperation(value= "This method is used to save premium details")
	@ApiResponses(value= {@ApiResponse(code = 200, message = "ok"),@ApiResponse(code =  404 ,message = "Not_FOund")})
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

	// CR786
	@PutMapping("/updatePremiumDetails")
    @ApiOperation(value = "This method is used to update premium details")
	@ApiResponses(value = {@ApiResponse (code = 200 , message = "Update exitsting premium details with id")
	                       ,@ApiResponse(code = 500, message ="Internal server error")})
	public ResponseEntity<Policy> updatePremiumDetails(@RequestBody Policy policy) {
		Policy policy1 = policyService.updatePolicyDetails(policy);
		List<PremiumDetails> premiumDetailsList = policy.getPremiumDetailsList();
		for (PremiumDetails premiumDetails : premiumDetailsList) {
			premiumDetails.setPolicyId(policy.getId());
			premiumDetailsService.updatePremium(premiumDetails);
		}
		return ResponseEntity.ok().body(policy1);

	}
    @GetMapping("/premiumDetails")
    @ApiOperation(value = "This method is used to get all premium details")
    @ApiResponses(value = {@ApiResponse(code =200 ,message = "Get All Premium Details" )
                        ,@ApiResponse(code = 404 ,message = "Not_Found")})
	public ResponseEntity<List<PremiumDetails>> getAllPremiumDetails(@RequestParam(defaultValue = "10", name = "page") int pageNo,
			@RequestParam(defaultValue = "10", name = "records") int pageSize,
			@RequestParam(defaultValue = "PremiumId", name = "sort") String sortBy) {
    	pageNo = pageNo > 0 ? pageNo -1 : 0;
		// CR-787
		List<PremiumDetails> premiumDetailsList = premiumDetailsService.getAllPremiumDetails(pageNo, pageSize, sortBy);
		return ResponseEntity.status(HttpStatus.OK).body(premiumDetailsList);
	}

	@DeleteMapping("deletePriemium/{id}")
	@ApiOperation(value = "This method is used to  Delete premium details By ID")
    @ApiResponses(value = {@ApiResponse(code =200 ,message = "Delete Premium Details" )
                        ,@ApiResponse(code = 404 ,message = "Not_Found")})
    public void deletePremium (@PathVariable ("id") Integer id) {
    	premiumDetailsService.deletePreimium(id);
    }
}
