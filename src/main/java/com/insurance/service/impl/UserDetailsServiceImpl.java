package com.insurance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.UserDetails;
import com.insurance.repository.UserDetailsRepository;
import com.insurance.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails addUserDetails(UserDetails userDetails) {
		UserDetails newuserDetails = userDetailsRepository.save(userDetails);
		return newuserDetails;
	}

}
