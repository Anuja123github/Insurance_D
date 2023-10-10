package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.model.UserDetails;
import com.insurance.service.UserDetailsService;

@RestController
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/userDetails")
	public ResponseEntity<UserDetails> addUserDetails(@RequestBody UserDetails userDetails) {
		UserDetails newuserDetails = userDetailsService.addUserDetails(userDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(newuserDetails);
	}
	
	@GetMapping("/userDetails")
	public ResponseEntity<List<UserDetails>> getAllUserDetails(@RequestParam(defaultValue = "1", name = "page") int pageNo,
			@RequestParam(defaultValue = "15", name = "records") int pageSize) {
		
		pageNo = (pageNo > 0) ? pageNo - 1 : 0;
		List<UserDetails> userDetailsList = userDetailsService.getAllUsers(pageNo, pageSize);
		return ResponseEntity.status(HttpStatus.OK).body(userDetailsList);
	}

}
