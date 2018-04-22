package com.pratt.fps.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fd_table")
public class FixedDeposit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fdId", unique = true, nullable = false)
	int fdId;

	@Column(name = "acctId", nullable = false)
	int acctId;

	@Column(name = "fdType", nullable = false)
	String fdType;

	@Column(name = "amount", nullable = false)
	int amount;

	@Column(name = "duration", nullable = false)
	int duration;

	@Column(name = "interestRate", nullable = false)
	int interestRate;

	@Column(name = "expDate", nullable = false)
	String expDate;

	public FixedDeposit() {

	}

	public int getFdId() {
		return fdId;
	}

	public void setFdId(int fdId) {
		this.fdId = fdId;
	}

	public int getAcctId() {
		return acctId;
	}

	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}

	public String getFdType() {
		return fdType;
	}

	public void setFdType(String fdType) {
		this.fdType = fdType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

}
