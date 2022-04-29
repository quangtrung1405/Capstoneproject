package com.hcl.shopforhome.service;

import java.util.List;

import com.hcl.shopforhome.bean.UserDetails;
import com.hcl.shopforhome.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	public String createUser(UserDetails user) {

		if (userDao.existsById(user.getId())) {
			return "This is ID already Exists, Please Give different ID";
		} else {
			userDao.save(user);
			return "User Created successfully";
		}
	}

	public List<UserDetails> retrieveUser() {
		return userDao.findAll();
	}

	public String updateUser(UserDetails user) {

		if (!userDao.existsById(user.getId())) {
			return "This ID not Exist, Please give correct ID";
		} else {
			UserDetails us = userDao.getById(user.getId());
			us.setUsername(user.getUsername());
			us.setContact(user.getContact());
			us.setGender(user.getGender());
			us.setAddress(user.getAddress());
			us.setCity(user.getCity());
			us.setState(user.getState());
			userDao.save(us);
			return "User Details updated Successfully";
		}
	}

	public String deleteUser(int id) {
		if (!userDao.existsById(id)) {
			return "This ID not exists, Please give Valid ID";
		} else {
			userDao.deleteById(id);
			return "User in the Id number " + id + " has Deleted successfully";
		}
	}
}
