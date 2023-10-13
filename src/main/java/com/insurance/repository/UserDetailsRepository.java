package com.insurance.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Serializable> {

	UserDetails findUserDetailsById(Integer id);

}
