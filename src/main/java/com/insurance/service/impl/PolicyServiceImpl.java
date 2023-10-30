package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public List<Policy> getAllPolicyInformation(int pageNo, int pageSize, String sortBy) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Policy> policyPage = policyRepository.findAll(pageable);
		if (policyPage.hasContent()) {
			return policyPage.getContent();
		}
		else {
			return new ArrayList<Policy>();
		}

	}

	@Override
	public List<Policy> getPolicyByUserId(Integer userId) {
		List<Policy> policies = policyRepository.getPolicyByUserId(userId);
		return policies;
	}
}