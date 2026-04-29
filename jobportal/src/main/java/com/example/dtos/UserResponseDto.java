package com.example.dtos;

import java.util.List;

public class UserResponseDto {

	private String message;
	private String status;
	private String statusCode;

	private List<UserResponse> list;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<UserResponse> getList() {
		return list;
	}

	public void setList(List<UserResponse> list) {
		this.list = list;
	}

}
