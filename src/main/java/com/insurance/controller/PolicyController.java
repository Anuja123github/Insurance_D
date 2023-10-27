package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.ApiResponseDto;
import com.insurance.model.Claim;
import com.insurance.model.Policy;
import com.insurance.model.PremiumDetails;
import com.insurance.service.ClaimService;
import com.insurance.service.PolicyService;
import com.insurance.service.PremiumDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PolicyController {

	@Autowired
	private PolicyService policyService;

	@Autowired
	private PremiumDetailsService premiumDetailsService;
	
	@Autowired
	private ClaimService claimService;

	@PostMapping("/policy")
	@ApiOperation(value = "Request to save Policy which includes Claim list and Premium Details List")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"), 
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error")
	})
	public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		// CR-672
		Policy newPolicy = policyService.savePolicy(policy);
		List<PremiumDetails> premiumList = policy.getPremiumDetailsList();
		for (PremiumDetails premiumDetail : premiumList) {
			premiumDetail.setPolicyId(newPolicy.getId());
			premiumDetailsService.addPremium(premiumDetail);
		}
		List<Claim> claimList = policy.getClaimlist();
		for (Claim claim : claimList) {
			claim.setPolicyId(newPolicy.getId());
			claimService.saveClaim(claim);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(newPolicy);
	}

	@PutMapping("/policy")
	@ApiOperation(value = "This method is used to update policy details")
	@ApiResponses(value = {@ApiResponse (code = 200 , message = "Update exitsting policy details ")
	                       ,@ApiResponse(code = 500, message ="Internal server error")})
	public ResponseEntity<Policy> updatePolicyDetails(@RequestBody Policy Policy) {
		// CR-673
		Policy newPolicy = policyService.updatePolicyDetails(Policy);
		List<PremiumDetails> premiumList = newPolicy.getPremiumDetailsList();
		for (PremiumDetails premiumDetail : premiumList) {
			premiumDetail.setPolicyId(newPolicy.getId());
			premiumDetailsService.updatePremium(premiumDetail);
		}
		List<Claim> claimList = newPolicy.getClaimlist();
		for (Claim claim : claimList) {
			claim.setPolicyId(newPolicy.getId());
			claimService.updateClaim(claim);
		}
		return ResponseEntity.status(HttpStatus.OK).body(newPolicy);
	}

	@GetMapping("/getPolicyById/{id}")
	@ApiOperation(value = "This method is used to  get policy details By ID")
    @ApiResponses(value = {@ApiResponse(code =200 ,message = "get  policy Details" )
                        ,@ApiResponse(code = 404 ,message = "Not_Found")})
	public Policy getPolicyById(@PathVariable("id") Integer id) {

		Policy policy = policyService.getPolicyById(id);
		List<PremiumDetails> premiumList = premiumDetailsService.getPremiumDetailsByPolicyId(id);
		policy.setPremiumDetailsList(premiumList);
		List<Claim> claimList = claimService.getClaimsByPolicyId(id);
		policy.setClaimlist(claimList);
		return policy;
	}

	@DeleteMapping("/policy/{id}")
	@ApiOperation(value = "Request to delete Policy")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), 
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 500, message = "Internal Error")
	})
	public ResponseEntity<ApiResponseDto> removePolicy(@PathVariable("id") Integer id) {
		// CR-686
		policyService.deletePolicy(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record is deleted."));
	}

	@GetMapping("/policy")
	@ApiOperation(value = "This method is used to  get policy details ")
    @ApiResponses(value = {@ApiResponse(code =200 ,message = "Get Policy Details" )
                        ,@ApiResponse(code = 404 ,message = "Not_Found")})
	public List<Policy> getAllPolicyInformation() {
		// CR-666
		List<Policy> policy = policyService.getAllPolicyInformation();
		return policy;
	}

	@PostMapping("/savePolicyPremiumDetails")
	@ApiOperation(value = "This method is used to  Create PolicyPremium details ")
    @ApiResponses(value = {@ApiResponse(code =200 ,message = "Save PolicyPremium Details" )
                        ,@ApiResponse(code = 201 ,message = "Created")})
	public ResponseEntity<Policy> savePolicyPremiumDetails(@RequestBody Policy policy) {
		Policy policy1 = policyService.savePolicy(policy);
		List<PremiumDetails> premiumDetailsList = policy.getPremiumDetailsList();
		for (PremiumDetails premiumDetails : premiumDetailsList) {
			premiumDetails.setPolicyId(policy.getId());
			premiumDetailsService.addPremium(premiumDetails);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(policy1);

	}

}
