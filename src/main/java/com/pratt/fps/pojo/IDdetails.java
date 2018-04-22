package com.pratt.fps.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "id_table")
public class IDdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idID", unique = true, nullable = false)
	int idID;

	@ManyToOne
	Customer customer;

	@Column(name = "idNum", nullable = false)
	String idNum;

	@Column(name = "idType", nullable = false)
	String idType;

	@Column(name = "idExpDate", nullable = false)
	String idExpDate;

	public int getIdID() {
		return idID;
	}

	public void setIdID(int idID) {
		this.idID = idID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdExpDate() {
		return idExpDate;
	}

	public void setIdExpDate(String idExpDate) {
		this.idExpDate = idExpDate;
	}

	public IDdetails() {

	}

}
