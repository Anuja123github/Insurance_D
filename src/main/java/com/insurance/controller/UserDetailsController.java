package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

}
