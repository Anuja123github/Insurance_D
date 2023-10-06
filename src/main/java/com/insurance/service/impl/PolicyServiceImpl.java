package com.insurance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.Policy;
import com.insurance.repository.PolicyRepository;
import com.insurance.service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;

	@Override
	public Policy savePolicy(Policy policy) {
		Policy newPolicy = policyRepository.save(policy);
		return newPolicy;
	}

	@Override
	public Policy updatePolicyDetails(Policy policy) {
		Policy newPolicy = policyRepository.save(policy);
		return newPolicy;
	}

	
	@Override
	public Policy getPolicyById(Integer id) {
		
		Policy policy = policyRepository.getPolicyById(id);
		return policy;
	}

}
