package com.hcl.shopforhome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.shopforhome.bean.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	public User findByEmailAndPassword(@Param("email") String user, @Param("password") String pass);

	public User findByEmailOrPassword(@Param("email") String user, @Param("password") String pass);

}
