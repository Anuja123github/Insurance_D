package com.insurance.service;

import java.util.List;

import com.insurance.model.PremiumDetails;

public interface PremiumDetailsService {

	public PremiumDetails addPremium(PremiumDetails premiumDetails);
	
	public List<PremiumDetails> getAllPremiumDetails();
}
