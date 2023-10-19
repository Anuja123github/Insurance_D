package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.PolicySchedule;

@Repository
public interface PolicyScheduleRepository extends JpaRepository<PolicySchedule, Integer> {

	public PolicySchedule findPolicyScheduleById(Integer id);
}
