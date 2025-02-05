package org.dnyanyog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dnyanyog.common.DBUtil;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	@Autowired
	UserResponse response;
	
	
	public UserResponse saveUser(UserRequest user) {
		
		String query = "Insert into user (id, first_name, last_name, email, mobile, login_name, password)"
					 + "Values ("+ user.getId() + ", '" + user.getFirstName() + "', '" + user.getLastName() + "', '"
					 + user.getEmail() + "', '" + user.getMobile() + "', '" + user.getLoginName() + "', '" + user.getPassword() + "');"; 
		
		try {
		
			DBUtil.executeQuery(query);
			response.setId(user.getId());
			response.setFirstName(user.getFirstName());
			response.setLastName(user.getLastName());
			response.setEmail(user.getEmail());
			response.setMobile(user.getMobile());
			response.setLoginName(user.getLoginName());
			
			response.setCode("0000");
			response.setMsg("User added Successfully");			
			
		} catch (SQLException e) {
			response.setCode("911");
			response.setMsg("Adding user failed");	
			e.printStackTrace();
		}
		
		return response;
	}
	
	public List<UserResponse> getAllUser(){
		
		String query = "Select * from user";
		
		List<UserResponse> users = new ArrayList<>();
		
		ResultSet result = DBUtil.resultQuery(query);
		
		try {
			while(result.next()) {
				response.setId(result.getInt(1));
				response.setFirstName(result.getString(2));
				response.setLastName(result.getString(3));
				response.setEmail(result.getString(4));
				response.setMobile(result.getString(5));
				response.setLoginName(result.getString(6));
				
				users.add(response);
				
			}
			response.setCode("0000");
			response.setMsg("Displaying user Successful");
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			response.setCode("911");
			response.setMsg("Displaying user failed");
			e.printStackTrace();
			return users;
		}
	}
}
