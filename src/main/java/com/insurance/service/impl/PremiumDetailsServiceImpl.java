package com.insurance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.PremiumDetails;
import com.insurance.repository.PremiumDetailsRepository;
import com.insurance.service.PremiumDetailsService;

@Service
public class PremiumDetailsServiceImpl implements PremiumDetailsService {

	@Autowired
	private PremiumDetailsRepository premiumDetailsRepository;

	@Override
	public PremiumDetails addPremium(PremiumDetails premiumDetails) {
		PremiumDetails premiumDetails1 = premiumDetailsRepository.save(premiumDetails);
		return premiumDetails1;
	}

	@Override
	public List<PremiumDetails> getAllPremiumDetails() {
		List<PremiumDetails> premiumDetailsList = premiumDetailsRepository.findAll();
		return premiumDetailsList;
	}

	@Override
	public void deletePreimium(Integer id) {
		premiumDetailsRepository.deleteById(id);
		
	}

	

}
