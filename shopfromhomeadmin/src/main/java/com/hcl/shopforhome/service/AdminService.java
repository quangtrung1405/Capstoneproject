package com.hcl.shopforhome.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.shopforhome.bean.AdminDetails;
import com.hcl.shopforhome.dao.AdminDao;

@Service
public class AdminService implements IAdminService {

	@Autowired
	AdminDao adminDao;

	public String signUp(AdminDetails admin) {
		AdminDetails ad = adminDao.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
		AdminDetails ade = adminDao.findByEmailOrPassword(admin.getEmail(), admin.getPassword());
		if (Objects.nonNull(ad)) {
			return "Username Already Taken, Try with different one and Should be unique";
		} else if (Objects.nonNull(ade)) {
			return "Username Already Taken, Try with different one and Should be unique";

		} else {
			adminDao.save(admin);
			return "User Registered Successfully";
		}
	}

	public String signIn(AdminDetails admin) {

		AdminDetails adm = adminDao.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
		if (Objects.nonNull(adm)) {
			int len = admin.getEmail().length() - 10;
			return "Welcome admin " + admin.getEmail().substring(0, len) + " You are Logged IN Successfully ";
		} else {
			return "Incorrect Password or Username";
		}
	}
}
