package com.insurance.service;

import java.util.List;

import com.insurance.model.UserDetails;

public interface UserDetailsService {

	public UserDetails addUserDetails(UserDetails userDetails);
	
	public List<UserDetails> getAllUsers(int pageNo, int pageSize);
	
	public UserDetails getUserById(Integer id);
}
