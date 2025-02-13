package org.dnyanyog.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dnyanyog.common.DBUtil;
import org.dnyanyog.dto.Users;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	UserResponse response;

	@Autowired
	Users users;

	public UserResponse saveUser(UserRequest user) {

		String query = "Insert into user (id, first_name, last_name, email, mobile, login_name, password)" + "Values ("
				+ user.getId() + ", '" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmail()
				+ "', '" + user.getMobile()+ "');";

		try {

			DBUtil.executeQuery(query);
			users.setId(user.getId());
			users.setFirstName(user.getFirstName());
			users.setLastName(user.getLastName());
			users.setEmail(user.getEmail());
			users.setMobile(user.getMobile());

			List<Users> userList = new ArrayList<>();
			userList.add(users);
			response.setUsers(userList);
			response.setCode("0000");
			response.setMsg("User added Successfully");

		} catch (SQLException e) {
			response.setCode("911");
			response.setMsg("Adding user failed");
			e.printStackTrace();
		}

		return response;
	}

	public UserResponse getAllUser() {

		String query = "Select * from user";

		ResultSet result = DBUtil.resultQuery(query);

		List<Users> userList = new ArrayList<>();
		try {
			while (result.next()) {
				Users users = new Users();
				users.setId(result.getInt(1));
				users.setFirstName(result.getString(2));
				users.setLastName(result.getString(3));
				users.setEmail(result.getString(4));
				users.setMobile(result.getString(5));
				userList.add(users);
				response.setUsers(userList);
				
			}
			response.setCode("0000");
			response.setMsg("Displaying user Successful");
			return response;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			response.setCode("911");
			response.setMsg("Displaying user failed");
			e.printStackTrace();
			return response;
		}
	}
}
