package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="policy")
public class Policy {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String policynumber;
	private String premiumamount;
	private String name;
	private String email;
	private String claimnumber;
	private String status;
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
	public String getPremiumamount() {
		return premiumamount;
	}
	public void setPremiumamount(String premiumamount) {
		this.premiumamount = premiumamount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClaimnumber() {
		return claimnumber;
	}
	public void setClaimnumber(String claimnumber) {
		this.claimnumber = claimnumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Policy [id=" + id + ", policynumber=" + policynumber + ", premiumamount=" + premiumamount + ", name="
				+ name + ", email=" + email + ", claimnumber=" + claimnumber + ", status=" + status + ", getId()="
				+ getId() + ", getPolicynumber()=" + getPolicynumber() + ", getPremiumamount()=" + getPremiumamount()
				+ ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getClaimnumber()=" + getClaimnumber()
				+ ", getStatus()=" + getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}
