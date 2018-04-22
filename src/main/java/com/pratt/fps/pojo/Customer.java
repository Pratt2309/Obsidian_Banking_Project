package com.pratt.fps.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cust_table")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "custId", unique = true, nullable = false)
	int custId;

	@Column(name = "firstName", nullable = false)
	String firstName;

	@Column(name = "middleName", nullable = true)
	String middleName;

	@Column(name = "lastName", nullable = false)
	String lastName;

	@Column(name = "DOB", nullable = false)
	String DOB;

	@Column(name = "streetAddr1", nullable = false)
	String streetAddr1;

	@Column(name = "streetAddr2", nullable = true)
	String streetAddr2;

	@Column(name = "city", nullable = false)
	String city;

	@Column(name = "state", nullable = false)
	String state;

	@Column(name = "country", nullable = false)
	String country;

	@Column(name = "zipcode", nullable = false)
	int zipcode;

	@Column(name = "email1", unique = true, nullable = false)
	String email1;

	@Column(name = "email2", nullable = true)
	String email2;

	@Column(name = "homePhone", nullable = true)
	String homePhone;

	@Column(name = "officePhone", nullable = true)
	String officePhone;

	@Column(name = "mobilePhone", nullable = false)
	String mobilePhone;

	@Column(name = "username", unique = true, nullable = false)
	String username;

	@Column(name = "password", nullable = false)
	String password;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getStreetAddr1() {
		return streetAddr1;
	}

	public void setStreetAddr1(String streetAddr1) {
		this.streetAddr1 = streetAddr1;
	}

	public String getStreetAddr2() {
		return streetAddr2;
	}

	public void setStreetAddr2(String streetAddr2) {
		this.streetAddr2 = streetAddr2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer() {

	}
    
}
