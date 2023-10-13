package com.insurance.service;

import java.util.List;
import java.util.Optional;

import com.insurance.model.Claim;

public interface ClaimService {
	public Claim saveClaim(Claim claim);

	public void deleteClaimById(Integer id);

	public List<Claim> getAllClaimsList();

}
