package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<UserDetails> getAllUsers(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<UserDetails> usersPage = userDetailsRepository.findAll(pageable);
		if (usersPage.hasContent()) {
			return usersPage.getContent();
		}
		return new ArrayList<>();
	}

	@Override
	public UserDetails getUserById(Integer id) {
		UserDetails user=userDetailsRepository.findById(id).get();
		return user;
	}

}
