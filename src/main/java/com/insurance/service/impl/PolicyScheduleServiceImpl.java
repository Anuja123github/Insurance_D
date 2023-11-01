package com.insurance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exception.ResourceNotFoundException;
import com.insurance.model.PolicySchedule;
import com.insurance.repository.PolicyScheduleRepository;
import com.insurance.service.PolicyScheduleService;

@Service
public class PolicyScheduleServiceImpl implements PolicyScheduleService {

	@Autowired
	private PolicyScheduleRepository policyScheduleRepository;

	@Override
	public PolicySchedule addPolicySchedule(PolicySchedule policySchedule) {
		PolicySchedule policySchedules = policyScheduleRepository.save(policySchedule);
		return policySchedules;
	}

	@Override
	public PolicySchedule updatePolicySchedule(PolicySchedule policySchedule) {
		PolicySchedule policySchedules = policyScheduleRepository.save(policySchedule);
		return policySchedules;
	}

	@Override
	public PolicySchedule getPolicyScheduleById(Integer id) {
		PolicySchedule policySchedules = policyScheduleRepository.findPolicyScheduleById(id);
		return policySchedules;
	}

	@Override
	public void deletePolicyScheduleById(Integer id) {
		PolicySchedule policySchedules = policyScheduleRepository.findPolicyScheduleById(id);
		if(policySchedules!=null) {
		policyScheduleRepository.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException("No Policy Schedule found with id: " + id);
		}
	}

}
