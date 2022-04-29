package com.hcl.shopforhome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.shopforhome.bean.Products;

@Repository
public interface ProductDao extends JpaRepository<Products, Integer> {

}
