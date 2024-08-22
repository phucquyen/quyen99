package com.vti.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountDto {

	private short id;
	private String email;
	private String username;
	private String fullname;
	private String avatarImageName;
	private String mobile;
	private String address;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createDate;
	private String status;

	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatarImageName() {
		return avatarImageName;
	}

	public void setAvatarImageName(String avatarImageName) {
		this.avatarImageName = avatarImageName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
