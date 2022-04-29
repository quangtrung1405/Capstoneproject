package com.hcl.shopforhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.shopforhome.bean.AdminDetails;
import com.hcl.shopforhome.service.AdminService;

@RestController
@RequestMapping("/AdminActivity")
@CrossOrigin
public class AdminController {

	@Autowired
	AdminService adminService; 

	@PostMapping(value = "signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signUp(@RequestBody AdminDetails admin) {
		return adminService.signUp(admin);
	}

	@PostMapping(value = "signIn", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String signIn(@RequestBody AdminDetails admin) {
		return adminService.signIn(admin);
	}

	@GetMapping(value = "logout")
	public String logout() {
		return "Logged Out successfully";
	}

}
