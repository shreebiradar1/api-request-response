package org.dnyanyog.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dnyanyog.common.DBUtil;
import org.dnyanyog.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

	@Autowired
	LoginResponse response;

	public LoginResponse login(String username, String password) {
		if (username == null || password == null) {
			response.setCode("0911");
			response.setMsg("Username and password can not be empty");
			return response;
		}

		// DB Code
		ResultSet result = DBUtil
				.resultQuery("Select * from login where username = '" + username + "' and password = '" + password + "'");

		try {
			if (result.next()) {
				response.setCode("0000");
				response.setMsg("Login Successful");
				return response;
			} else {
				response.setCode("0911");
				response.setMsg("Username and password might be wrong");
				return response;
			}
		} catch (SQLException e) {
			response.setCode("0911");
			response.setMsg("Internal error occured, contant admin");
			e.printStackTrace();

			return response;

		}
	}
}
