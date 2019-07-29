package com.mohyehia.bookstore.entities;

public class ChangePasswordRequest {
	private String oldPassword, newPassword;
	
	public ChangePasswordRequest() {}

	public ChangePasswordRequest(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPAssword) {
		this.oldPassword = oldPAssword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
