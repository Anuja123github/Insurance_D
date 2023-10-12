package com.insurance.service;

import java.util.List;

import com.insurance.model.UserDetails;

public interface UserDetailsService {

	public UserDetails addUserDetails(UserDetails userDetails);

	public List<UserDetails> getAllUsers(int pageNo, int pageSize);

	public UserDetails getUserById(Integer id);

	public UserDetails updateUser(UserDetails userDetails);

	public void deleteUserDetails(int id);

<<<<<<< HEAD
	public UserDetails getUserById(Integer id);
=======
>>>>>>> 1b4bb1ca3674c6a049e6361b7ccb408a8ce88c96
}
