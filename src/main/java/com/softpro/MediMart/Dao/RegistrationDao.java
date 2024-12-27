package com.softpro.MediMart.Dao;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class RegistrationDao {

	@NotEmpty(message= "name can't be blank")
	private String name;
	
	@NotEmpty(message= "Please select gender")
	private String gender;
	
	@NotEmpty(message= "email can't be blank")
	private String email;
	
	@NotEmpty(message= "password can't be blank")
	private String password;
	
	@NotEmpty(message= "contact can't be blank")
	private String contactno;
	
	@NotEmpty(message= "dob can't be blank")
	private String dob;
	
	@NotEmpty(message= "address can't be blank")
	private String address;
	

	private MultipartFile file;
	
	private String postdate;
	
	//gettersettersrt*****

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

	
	
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getPostdate() {
		return postdate;
	}

	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}
	
	
	
	
}
