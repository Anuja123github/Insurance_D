package com.insurance.service;

import com.insurance.model.Policy;

public interface PolicyService {

	public Policy savePolicy(Policy policy);

	public Policy updatePolicyDetails(Policy policy);

	public Policy getPolicyById(Integer id);

	public void deletePolicy(Integer id);

}
