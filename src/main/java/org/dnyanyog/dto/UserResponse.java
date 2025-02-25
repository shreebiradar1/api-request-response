package org.dnyanyog.dto;

import org.dnyanyog.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResponse {

	private String code;
	private String msg;
	private User user;

	@Autowired
	private UserRequest request;

	public UserRequest getRequest() {
		return request;
	}

	public void setRequest(UserRequest request) {
		this.request = request;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setRequest(User user) {
		this.user = user;

	}
}
