package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "premiumDetails")
public class PremiumDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String PremiumId;
	private Integer policyId;// fk
	private String policyHolderName;
	private String installmentPremiumAmount;
	private String policyCommencementDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPremiumId() {
		return PremiumId;
	}

	public void setPremiumId(String premiumId) {
		PremiumId = premiumId;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getPolicyHolderName() {
		return policyHolderName;
	}

	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}

	public String getInstallmentPremiumAmount() {
		return installmentPremiumAmount;
	}

	public void setInstallmentPremiumAmount(String installmentPremiumAmount) {
		this.installmentPremiumAmount = installmentPremiumAmount;
	}

	public String getPolicyCommencementDate() {
		return policyCommencementDate;
	}

	public void setPolicyCommencementDate(String policyCommencementDate) {
		this.policyCommencementDate = policyCommencementDate;
	}

	@Override
	public String toString() {
		return "PremiumDetails [id=" + id + ", PremiumId=" + PremiumId + ", PolicyId=" + policyId
				+ ", policyHolderName=" + policyHolderName + ", installmentPremiumAmount=" + installmentPremiumAmount
				+ ", policyCommencementDate=" + policyCommencementDate + "]";
	}

}
