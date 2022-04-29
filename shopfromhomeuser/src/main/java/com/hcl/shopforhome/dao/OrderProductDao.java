package com.hcl.shopforhome.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.shopforhome.bean.OrderProduct;
import com.hcl.shopforhome.bean.OrderProductPack;

@Repository
public interface OrderProductDao extends CrudRepository<OrderProduct, OrderProductPack> {
}