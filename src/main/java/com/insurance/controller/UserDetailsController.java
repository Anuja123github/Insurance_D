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
import com.insurance.model.UserDetails;
import com.insurance.service.ClaimService;
import com.insurance.service.UserDetailsService;

@RestController
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ClaimService claimService;

	@PostMapping("/userDetails")
	public ResponseEntity<UserDetails> addUserDetails(@RequestBody UserDetails userDetails) {
		// CR-740
		UserDetails newuserDetails = userDetailsService.addUserDetails(userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(newuserDetails);
	}

	@GetMapping("/userDetails")
	public ResponseEntity<List<UserDetails>> getAllUserDetails(
			@RequestParam(defaultValue = "1", name = "page") int pageNo,
			@RequestParam(defaultValue = "15", name = "records") int pageSize) {

		pageNo = (pageNo > 0) ? pageNo - 1 : 0;
		List<UserDetails> userDetailsList = userDetailsService.getAllUsers(pageNo, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(userDetailsList);
	}

//CR743
	@GetMapping("/userDetails/{id}")
	public ResponseEntity<UserDetails> getUserById(@PathVariable("id") Integer id) {
		UserDetails user = userDetailsService.getUserById(id);
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/userUpdate")
	public UserDetails updateUser(@RequestBody UserDetails userDetails) {
		UserDetails user = userDetailsService.updateUser(userDetails);
		return user;

	}

	@DeleteMapping("/userDetails/{id}")
	public ResponseEntity<ApiResponseDto> deleteUserDetails(@PathVariable("id") int id) {
		// CR-744
		userDetailsService.deleteUserDetails(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Record deleted successfully"));
	}

	@GetMapping("/updateUserPasswordById")
	public ResponseEntity<ApiResponseDto> updateUserPasswordById(@RequestParam("id") Integer id,
			@RequestParam("password") String password) {
		UserDetails userDetails = userDetailsService.updateUserPasswordById(id, password);
		if (userDetails != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto("Password updated successfully"));
		} else
			throw new ResourceNotFoundException("No UserDetails found with id: " + id);
	}

	@GetMapping("/getUserDetails/{id}/{claimId}")
	public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable("id") Integer id, @PathVariable("claimId") Integer claimId) {
		// get userDetails
		UserDetails userDetails = userDetailsService.getUserById(id);//1
		// multiple claim get call method
		List<Claim> claims = claimService.getClaimList(claimId);
		for (Claim claim : claims) {
			userDetails.setId(claim.getUserId());
			userDetails.setClaimList(claims);

		}
		return ResponseEntity.ok().body(userDetails);

	}

}
