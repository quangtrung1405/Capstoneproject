package com.hcl.shopforhome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.shopforhome.bean.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {

}
