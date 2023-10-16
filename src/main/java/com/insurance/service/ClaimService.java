package com.insurance.service;

import java.util.List;

import com.insurance.model.Claim;

public interface ClaimService {
	
	public Claim saveClaim(Claim claim);

	public void deleteClaimById(Integer id);
	

	public List<Claim> getAllClaimsList();

	public List<Claim> saveAllClaims(List<Claim> claimlist);
	
	public Claim updateClaim(Claim claim);
}
