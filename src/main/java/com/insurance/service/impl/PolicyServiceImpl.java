package com.insurance.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

	@Transactional
	@Override
	public Policy savePolicy(Policy policy) {
		Policy newPolicy = policyRepository.save(policy);
		return newPolicy;
	}

	@Transactional
	@Override
	public Policy updatePolicyDetails(Policy policy) {
		
		Optional<Policy> returnedOption = policyRepository.findById(policy.getId());
		if (returnedOption.isPresent()) {
			Policy newPolicy = policyRepository.save(policy);
			return newPolicy;			
		}
		else {
			throw new ResourceNotFoundException("No policy found with id: "+policy.getId());
		}
	}

	@Override
	public Policy getPolicyById(Integer id) {

		Optional<Policy> returnedOption = policyRepository.findById(id);
		if (returnedOption.isPresent()) {
			return returnedOption.get();			
		}
		else {
			throw new ResourceNotFoundException("No policy found with id: "+id);
		}
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

	@Override
	public List<Policy> getAllPolicyInformation() {
		List<Policy> policy = policyRepository.findAll();
		return policy;

	}

	@Override
	public List<Policy> getPolicyByUserId(Integer userId) {
		List<Policy> policies = policyRepository.getPolicyByUserId(userId);
		return policies;
	}
}