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

import com.insurance.dto.ApiResponseDto;
import com.insurance.exception.ResourceNotFoundException;
import com.insurance.model.Claim;
import com.insurance.model.Policy;
import com.insurance.model.PremiumDetails;
import com.insurance.model.UserDetails;
import com.insurance.service.ClaimService;
import com.insurance.service.PolicyService;
import com.insurance.service.UserDetailsService;
import com.insurance.service.PremiumDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private PremiumDetailsService premiumDetailsService;

	@Autowired
	private ClaimService claimService;

	@PostMapping("/userDetails")
	@ApiOperation(value = "Request to save user details")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<UserDetails> addUserDetails(@RequestBody UserDetails userDetails) {
		// CR-740
		UserDetails newuserDetails = userDetailsService.addUserDetails(userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(newuserDetails);
	}

	@GetMapping("/userDetails")
	@ApiOperation(value = "Request to get all user details ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<List<UserDetails>> getAllUserDetails(
			@RequestParam(defaultValue = "1", name = "page") int pageNo,
			@RequestParam(defaultValue = "15", name = "records") int pageSize) {

		pageNo = (pageNo > 0) ? pageNo - 1 : 0;
		List<UserDetails> userDetailsList = userDetailsService.getAllUsers(pageNo, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(userDetailsList);
	}

	@GetMapping("/userDetails/{id}")
	@ApiOperation(value = "Request to get user details using id ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<UserDetails> getUserById(@PathVariable("id") Integer id) {
		// CR743
		UserDetails user = userDetailsService.getUserById(id);
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/userUpdate")
	@ApiOperation(value = "Request to update user details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public UserDetails updateUser(@RequestBody UserDetails userDetails) {
		UserDetails user = userDetailsService.updateUser(userDetails);
		return user;
	}

	@DeleteMapping("/userDetails/{id}")
	@ApiOperation(value = "Request to delete user details by using id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<ApiResponseDto> deleteUserDetails(@PathVariable("id") int id) {
		// CR-744
		userDetailsService.deleteUserDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record deleted successfully"));
	}

	@GetMapping("/updateUserPasswordById")
	@ApiOperation(value = "Request to update user details password by using id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<ApiResponseDto> updateUserPasswordById(@RequestParam("id") Integer id,
			@RequestParam("password") String password) {
		UserDetails userDetails = userDetailsService.updateUserPasswordById(id, password);
		if (userDetails != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Password updated successfully"));
		} else
			throw new ResourceNotFoundException("No UserDetails found with id: " + id);
	}

//	@GetMapping("/getUserDetails/{id}/{claimId}")
//	public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable("id") Integer id,
//			@PathVariable("claimId") Integer claimId) {
//		// get userDetails
//		UserDetails userDetails = userDetailsService.getUserById(id);// 1
//		// multiple claim get call method
//		List<Claim> claims = claimService.getClaimList(claimId);
//		for (Claim claim : claims) {
//			userDetails.setId(claim.getUserId());
//			userDetails.setClaimList(claims);
//
//		}
//		return ResponseEntity.ok().body(userDetails);
//
//	}

	@GetMapping("/searchByNameAndEmail")
	@ApiOperation(value = "Request to get user details by using firstname,lastname AND email")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public List<UserDetails> findByFirstNameAndLastNameAndEmail(@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("email") String email) {
		List<UserDetails> searchDetails = userDetailsService.findByFirstnameAndLastnameAndEmail(firstname, lastname,
				email);
		return searchDetails;
	}

	@GetMapping("/getuserdetails-policy-premium-by-id/{id}")
	@ApiOperation(value = "Request to get user details with multiple policies with multiple premium details by user id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal Error") })
	public ResponseEntity<UserDetails> getUserPolicyPremiumById(@PathVariable("id") Integer id) {
		// CR-808
		UserDetails userDetails = userDetailsService.getUserById(id);
		List<Policy> policyList = policyService.getPolicyByUserId(userDetails.getId());
		for (Policy policy : policyList) {
			userDetails.setId(policy.getUserId());
			List<PremiumDetails> premiumList = premiumDetailsService.getPremiumDetailsByPolicyId(policy.getId());
			for (PremiumDetails premium : premiumList) {
				policy.setId(premium.getPolicyId());
			}
			policy.setPremiumDetailsList(premiumList);
		}
		userDetails.setPolicyList(policyList);
		return ResponseEntity.ok().body(userDetails);
	}

	@GetMapping("/userDetails/{id}/policies/claims")
	@ApiOperation(value = "Request to get user details with multiple policies with multiple claim details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 500, message = "Internal error") })
	public UserDetails getUserPolicyClaims(@PathVariable("id") Integer id) {

		// CR-807
		UserDetails returnedUser = userDetailsService.getUserById(id);
		List<Policy> policyList = policyService.getPolicyByUserId(id);
		for (Policy policy : policyList) {
			List<Claim> claimList = claimService.getClaimsByPolicyId(policy.getId());
			policy.setClaimlist(claimList);
		}
		returnedUser.setPolicyList(policyList);
		return returnedUser;
	}
}
