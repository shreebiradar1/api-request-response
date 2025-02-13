package org.dnyanyog.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.dnyanyog.common.DBUtil;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.entity.Login;
import org.dnyanyog.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

	@Autowired
	LoginResponse response;

	@Autowired
	LoginRepo loginRepo;
	
	public LoginResponse login(String username, String password) { //Cyclometric complexity 4 + 1 = 5
		//Check if login name and password are not null
		if (username == null || password == null) {
			response.setCode("0911");
			response.setMsg("Username and password can not be empty");
			return response;
		}

		// DB Code
//		ResultSet result = DBUtil
//				.resultQuery("Select * from login where username = '" + username + "' and password = '" + password + "'");
		
		Login loginfromDB =  loginRepo.findByUserNameAndPassword(username, password);
		try {
			
			if (loginfromDB != null) { 
				//if user and password are match
				response.setCode("0000");
				response.setMsg("Login Successful");
				return response;
			} else {
				//it user id or password does not match
				response.setCode("0911");
				response.setMsg("Username and password might be wrong");
				return response;
			}
		} catch (Exception e) {
			//Error occur in sql syntax of other sql error
			response.setCode("0911");
			response.setMsg("Internal error occured, contant admin");
			e.printStackTrace();

			return response;

		}
	}
}
