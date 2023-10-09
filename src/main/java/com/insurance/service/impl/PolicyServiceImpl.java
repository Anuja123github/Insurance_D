package com.insurance.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exception.ResourceNotFoundException;
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

	@Override
	public void deletePolicy(Integer id) {

		Optional<Policy> returnOption = policyRepository.findById(id);
		if (returnOption.isPresent()) {
			policyRepository.delete(returnOption.get());
		} else {
			throw new ResourceNotFoundException("No Policy found with id: " + id);
		}
	}
}
