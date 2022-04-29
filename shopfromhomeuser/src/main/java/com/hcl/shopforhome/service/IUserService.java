package com.hcl.shopforhome.service;

import java.util.List;

import com.hcl.shopforhome.bean.User;

public interface IUserService {

	public User getUser(int id);

	public String signUpUser(User user);

	public String signInUser(User user);

	public User createUser(User user);

	public List<User> retrieveUser();

	public User updateUser(User user);

	public String deleteUser(int id);
}
