package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer> {
	public List<Claim> findByClaimId(Integer id);

	public List<Claim> findByPolicyId(Integer id);
}
