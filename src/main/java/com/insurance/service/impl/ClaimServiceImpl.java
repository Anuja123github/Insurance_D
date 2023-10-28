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
import com.insurance.model.Claim;
import com.insurance.repository.ClaimRepository;
import com.insurance.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {

	// inject repository
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
	public List<Claim> getAllClaimsList(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Claim> claimPage = claimRepository.findAll(pageable);
		if (claimPage.hasContent()) {
			return claimPage.getContent();
		}
		else {
			return new ArrayList<Claim>();
		}
	}

	@Override
	public List<Claim> saveAllClaims(List<Claim> claimlist) {
		List<Claim> claimList1 = claimRepository.saveAll(claimlist);
		return claimList1;

	}

	@Override
	public Claim updateClaim(Claim claim) {
		Claim updatedClaim = claimRepository.save(claim);
		return updatedClaim;
	}

	@Override
	public List<Claim> getClaimsByPolicyId(Integer id) {
		List<Claim> claimList = claimRepository.findByPolicyId(id);
		return claimList;
	}

	@Transactional
	@Override
	public List<Claim> getClaimList(Integer id) {
		List<Claim> claim = claimRepository.findByClaimId(id);
		return claim;
	}

}
