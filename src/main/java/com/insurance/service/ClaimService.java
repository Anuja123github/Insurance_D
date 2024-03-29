package com.insurance.service;

import java.util.List;

import com.insurance.model.Claim;

public interface ClaimService {

	public Claim saveClaim(Claim claim);

	public void deleteClaimById(Integer id);

	public List<Claim> getAllClaimsList(int pageNo, int pageSize, String sortBy);

	public List<Claim> saveAllClaims(List<Claim> claimlist);

	public Claim updateClaim(Claim claim);

	public List<Claim> getClaimsByPolicyId(Integer id);

	public List<Claim> getClaimList(Integer id);

}
