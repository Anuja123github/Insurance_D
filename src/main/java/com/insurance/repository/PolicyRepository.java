package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	public Policy getPolicyById(Integer id);

	public List<Policy> getPolicyByUserId(Integer userId);

}
