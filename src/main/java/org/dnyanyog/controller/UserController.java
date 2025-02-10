package org.dnyanyog.controller;

import java.util.List;

import org.dnyanyog.dto.User;
import org.dnyanyog.dto.UserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(path ="/user", produces = {"application/json" , "application/xml"}, consumes = {"application/json" , "application/xml"})
	public UserResponse saveUser(@RequestBody UserRequest user) {

		return userService.saveUser(user);
	}
	
	@GetMapping(path = "/user")
	public UserResponse getUser(){
		return userService.getAllUser();
	}
}