package com.insurance.service;

import java.util.List;

import com.insurance.model.Policy;

public interface PolicyService {

	public Policy savePolicy(Policy policy);

	public Policy updatePolicyDetails(Policy policy);

	public Policy getPolicyById(Integer id);

	public void deletePolicy(Integer id);

	public List<Policy> getAllPolicyInformation(int pageNo, int pageSize, String sortBy);

	public List<Policy> getPolicyByUserId(Integer userId);

}
