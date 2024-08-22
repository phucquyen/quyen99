package com.vti.dto;

public class LoginDto {
	private short id;

	private String fullName;

	private String status;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
