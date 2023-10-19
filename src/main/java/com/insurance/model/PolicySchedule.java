package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "policySchedule")
public class PolicySchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String PolicyName;
	private String PlanName;
	private String UserName;
	private String PremiumAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPolicyName() {
		return PolicyName;
	}

	public void setPolicyName(String policyName) {
		PolicyName = policyName;
	}

	public String getPlanName() {
		return PlanName;
	}

	public void setPlanName(String planName) {
		PlanName = planName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPremiumAmount() {
		return PremiumAmount;
	}

	public void setPremiumAmount(String premiumAmount) {
		PremiumAmount = premiumAmount;
	}

	@Override
	public String toString() {
		return "PolicySchedule [id=" + id + ", PolicyName=" + PolicyName + ", PlanName=" + PlanName + ", UserName="
				+ UserName + ", PremiumAmount=" + PremiumAmount + "]";
	}

}
