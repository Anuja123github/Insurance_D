package com.insurance.service;

import com.insurance.model.PolicySchedule;

public interface PolicyScheduleService {

	public PolicySchedule addPolicySchedule(PolicySchedule policySchedule);
	
	public PolicySchedule updatePolicySchedule(PolicySchedule policySchedule);
	
	public PolicySchedule getPolicyScheduleById(Integer id);
	
	public void deletePolicyScheduleById(Integer id);

}
