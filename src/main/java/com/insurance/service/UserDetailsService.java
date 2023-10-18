package com.insurance.service;

import java.util.List;

import com.insurance.model.UserDetails;

public interface UserDetailsService {

	public UserDetails addUserDetails(UserDetails userDetails);

	public List<UserDetails> getAllUsers(int pageNo, int pageSize);

	public UserDetails updateUser(UserDetails userDetails);

	public void deleteUserDetails(int id);

	public UserDetails updateUserPasswordById(Integer id, String password);

	public UserDetails getUserById(Integer id);
	
	public List<UserDetails> findByFirstnameAndLastnameAndEmail(String firstname, String lastname, String email);
}
