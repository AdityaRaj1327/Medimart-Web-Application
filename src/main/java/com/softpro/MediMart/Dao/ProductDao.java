package com.softpro.MediMart.Dao;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class ProductDao {

	@NotEmpty(message = "can not be null")
	private String pid;
	@NotEmpty(message = "can not be null")
	private String pname;
	@NotEmpty(message = "can not be null")
	private String pdesc;
	@NotEmpty(message = "can not be null")
	private int pprice;
	
	private MultipartFile pimage;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public MultipartFile getPimage() {
		return pimage;
	}

	public void setPimage(MultipartFile pimage) {
		this.pimage = pimage;
	}
	
}
