package com.insurance.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "policy")
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String policynumber;
	private Date policyEffectiveDate;
	private Date policyExpiryDate;
	private String paymentOption;
	private Double totalAmount;
	private Boolean status;
	private Date createdDate;
	private String additionalInfo;
	private Integer userId;
	
	@OneToMany(mappedBy = "policyId")
	private List<Claim> claimlist;
	
	@OneToMany(mappedBy = "policyId")
	private List<PremiumDetails> premiumDetailsList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolicynumber() {
		return policynumber;
	}

	public void setPolicynumber(String policynumber) {
		this.policynumber = policynumber;
	}

	public Date getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}

	public void setPolicyEffectiveDate(Date policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}

	public Date getPolicyExpiryDate() {
		return policyExpiryDate;
	}

	public void setPolicyExpiryDate(Date policyExpiryDate) {
		this.policyExpiryDate = policyExpiryDate;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Claim> getClaimlist() {
		return claimlist;
	}

	public void setClaimlist(List<Claim> claimlist) {
		this.claimlist = claimlist;
	}

	public List<PremiumDetails> getPremiumDetailsList() {
		return premiumDetailsList;
	}

	public void setPremiumDetailsList(List<PremiumDetails> premiumDetailsList) {
		this.premiumDetailsList = premiumDetailsList;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policynumber=" + policynumber + ", policyEffectiveDate=" + policyEffectiveDate
				+ ", policyExpiryDate=" + policyExpiryDate + ", paymentOption=" + paymentOption + ", totalAmount="
				+ totalAmount + ", status=" + status + ", createdDate=" + createdDate + ", additionalInfo="
				+ additionalInfo + ", userId=" + userId + ", claimlist=" + claimlist + ", premiumDetailsList="
				+ premiumDetailsList + "]";
	}
}
