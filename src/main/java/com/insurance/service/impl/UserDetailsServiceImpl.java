package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insurance.exception.ResourceNotFoundException;
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

	public UserDetails updateUser(UserDetails userDetails) {
		UserDetails user = userDetailsRepository.save(userDetails);
		return user;
	}

	@Override
	public void deleteUserDetails(int id) {
		Optional<UserDetails> userDetails = userDetailsRepository.findById(id);
		if (userDetails.isPresent()) {
			userDetailsRepository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("No UserDetails found with id: " + id);
		}

	}

	@Override
	public UserDetails updateUserPasswordById(Integer id, String password) {
		UserDetails userDetails = userDetailsRepository.findUserDetailsById(id);
		if (userDetails != null) {
			userDetails.setPassword(password);
			UserDetails userDetails1 = userDetailsRepository.save(userDetails);
			return userDetails1;
		} else
			throw new ResourceNotFoundException("No UserDetails found with id: " + id);

	}

	@Override
	public UserDetails getUserById(Integer id) {
		UserDetails userDetails = userDetailsRepository.findUserDetailsById(id);
		return userDetails;
	}

	@Override
	public List<UserDetails> findByFirstnameAndLastnameAndEmail(String firstname, String lastname, String email) {
		List<UserDetails> findDetails = userDetailsRepository.findByFirstnameAndLastnameAndEmail(firstname, lastname, email);
		return findDetails;
	}

	

	
}
