package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.PremiumDetails;

@Repository
public interface PremiumDetailsRepository extends JpaRepository<PremiumDetails, Integer> {

}
