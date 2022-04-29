package com.hcl.shopforhome.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hcl.shopforhome.bean.User;
import com.hcl.shopforhome.customexception.ResourceNotFoundException;
import com.hcl.shopforhome.dao.UserDao;

@Service
@Transactional
public class UserService implements  IUserService {

	@Autowired
	UserDao userDao;

	public User getUser(int id) {
		return userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	public String signUpUser(User user) {
		User ue = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
		User ur = userDao.findByEmailOrPassword(user.getEmail(), user.getPassword());
		if (Objects.nonNull(ue)) {
			return "Username Already Taken, Try with different one and should be unique";

		} else if (Objects.nonNull(ur)) {
			return "Username Already Taken, Try with different one and should be unique";
		} else {
			userDao.save(user);
			return "User Registered Successfully";
		}
	}

	public String signInUser(User user) {
		User userent = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (Objects.nonNull(userent)) {
			int len = user.getEmail().length() - 10;
			return "Welcome user " + user.getEmail().substring(0, len) + " You are Logged IN Successfully ";
		} else {
			return "Incorrect Password or Username";
		}
	}

	public User createUser(User user) {

		if (userDao.existsById(user.getId())) {
			return null;
		} else {
			userDao.save(user);
			return null;
		}
	}

	public List<User> retrieveUser() {
		return userDao.findAll();
	}

	public User updateUser(User user) {

		if (!userDao.existsById(user.getId())) {
			return null;
		} else {
			User us = userDao.getById(user.getId());
			us.setUsername(user.getUsername());
			us.setContact(user.getContact());
			us.setGender(user.getGender());
			us.setAddress(user.getAddress());
			us.setCity(user.getCity());
			us.setState(user.getState());
			userDao.save(us);
			return user;
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
