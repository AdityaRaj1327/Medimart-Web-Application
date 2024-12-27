package com.softpro.MediMart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name ="registration")
public class Registration {

	@Column(nullable = false, length = 60)
	 private String name;
	
	@Column(nullable = false, length = 60)
	 private String gender;
	@Id
	@Column(nullable = false, length = 60)
	 private String email;
	
	@Column(nullable = false, length = 60)
	 private String password;
	
	
	@Column(nullable = false, length = 60)
	 private String contactno;
	
	@Column(nullable = false, length = 60)
	 private String dob;
	
	@Column(nullable = false, length = 600)
	 private String address;
	
	@Column(nullable = false, length = 600)
	 private String file;
	
	@Column(nullable = false, length = 60)
	 private String postdate;

	
	
	
	//gettersrt********
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	

	
	
	
	
	
}
