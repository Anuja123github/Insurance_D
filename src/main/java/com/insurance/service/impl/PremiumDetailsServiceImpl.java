package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.insurance.model.PremiumDetails;
import com.insurance.repository.PremiumDetailsRepository;
import com.insurance.service.PremiumDetailsService;

@Service
public class PremiumDetailsServiceImpl implements PremiumDetailsService {

	@Autowired
	private PremiumDetailsRepository premiumDetailsRepository;

	@Transactional
	@Override
	public PremiumDetails addPremium(PremiumDetails premiumDetails) {
		PremiumDetails premiumDetails1 = premiumDetailsRepository.save(premiumDetails);
		return premiumDetails1;
	}

	@Transactional
	@Override
	public PremiumDetails updatePremium(PremiumDetails premiumDetails) {
		PremiumDetails premiumDetails1 = premiumDetailsRepository.save(premiumDetails);
		return premiumDetails1;
	}

	public List<PremiumDetails> getAllPremiumDetails(int pageNo, int pageSize, String sortBy) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<PremiumDetails> premiumPage = premiumDetailsRepository.findAll(pageable);
		if (premiumPage.hasContent()) {
			return premiumPage.getContent();
		}
		else {
			return new ArrayList<PremiumDetails>();
		}
	}

	@Override
	public void deletePreimium(Integer id) {
		premiumDetailsRepository.deleteById(id);

	}

	@Override
	public List<PremiumDetails> getPremiumDetailsByPolicyId(Integer policyId) {
		List<PremiumDetails> premiumDetailsList = premiumDetailsRepository.getPremiumDetailsByPolicyId(policyId);
		return premiumDetailsList;
	}

}
