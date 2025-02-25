package org.dnyanyog.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductResponse {
	private String code;
	private String msg;

	@Autowired
	ProductRequest request;

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

	public ProductRequest getRequest() {
		return request;
	}

	public void setRequest(ProductRequest request) {
		this.request = request;
	}

}
