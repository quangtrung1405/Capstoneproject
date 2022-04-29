package com.hcl.shopforhome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.shopforhome.bean.AdminDetails;

@Repository
public interface AdminDao extends JpaRepository<AdminDetails, String> {

	public AdminDetails findByEmailAndPassword(@Param("email") String user, @Param("password") String pass);

	public AdminDetails findByEmailOrPassword(@Param("email") String user, @Param("password") String pass);
}
