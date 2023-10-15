package com.insurance.service.impl;

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
	public PremiumDetails updatePremium(PremiumDetails premiumDetails) {
		PremiumDetails premiumDetails1=premiumDetailsRepository.save(premiumDetails);
		return premiumDetails1;
	}

}
