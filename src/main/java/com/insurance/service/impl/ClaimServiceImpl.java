package com.insurance.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exception.ResourceNotFoundException;
import com.insurance.model.Claim;
import com.insurance.repository.ClaimRepository;
import com.insurance.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {
//inject repository
	@Autowired
	private ClaimRepository claimRepository;

	@Override
	public Claim saveClaim(Claim claim) {
		Claim claim1 = claimRepository.save(claim);
		return claim1;
	}

	@Override
	public void deleteClaimById(Integer id) {
		Optional<Claim> deleteClaim = claimRepository.findById(id);

		if (deleteClaim.isPresent()) {
			claimRepository.delete(deleteClaim.get());
		} else {
			throw new ResourceNotFoundException("No Claim found with id: " + id);

		}
	}

	@Override
	public List<Claim> getAllClaimsList() {
		List<Claim> claimList = claimRepository.findAll();
		return claimList;
	}
}
