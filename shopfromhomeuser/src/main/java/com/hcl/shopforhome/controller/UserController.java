package com.hcl.shopforhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.shopforhome.bean.User;
import com.hcl.shopforhome.service.UserService;

@RestController
@RequestMapping("/UserActivity")
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signUp(@RequestBody User user) {
		return userService.signUpUser(user);
	}

	@PostMapping(value = "signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signIn(@RequestBody User user) {
		return userService.signInUser(user);
	}

	@GetMapping(value = "logout")
	public String logout() {
		return "Logged Out successfully";
	}

	@GetMapping("/retrieveUsers")
	public ResponseEntity<List<User>> getAllUser(){
		List<User>users=userService.retrieveUser();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	@PostMapping("/createUser")
	public ResponseEntity<User> addUser(@RequestBody User user){
		 User users=userService.createUser(user);
		return new ResponseEntity<>(users,HttpStatus.CREATED);
	}
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		 User users=userService.updateUser(user);
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> addUser(@PathVariable ("id") Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
