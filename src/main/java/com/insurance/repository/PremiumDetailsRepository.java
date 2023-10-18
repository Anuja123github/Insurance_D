package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.PremiumDetails;

@Repository
public interface PremiumDetailsRepository extends JpaRepository<PremiumDetails, Integer> {

	public List<PremiumDetails> getPremiumDetailsByPolicyId(Integer policyId);
}
