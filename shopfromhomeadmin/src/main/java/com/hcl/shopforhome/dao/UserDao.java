package com.hcl.shopforhome.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.shopforhome.bean.UserDetails;

public interface UserDao extends JpaRepository<UserDetails,Integer>{

}
