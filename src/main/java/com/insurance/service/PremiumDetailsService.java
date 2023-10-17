package com.insurance.service;

import java.util.List;

import com.insurance.model.PremiumDetails;

public interface PremiumDetailsService {

	public PremiumDetails addPremium(PremiumDetails premiumDetails);

	public PremiumDetails updatePremium(PremiumDetails premiumDetails);

	public List<PremiumDetails> getAllPremiumDetails();

	public void deletePreimium(Integer id);

	public List<PremiumDetails> getPremiumDetailsByPolicyId(Integer policyId);

}
