package com.hcl.shopforhome.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hcl.shopforhome.bean.OrderProduct;
import com.hcl.shopforhome.dao.OrderProductDao;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
	private OrderProductDao orderProductRepository;

	public OrderProductServiceImpl(OrderProductDao orderProductRepository) {
		this.orderProductRepository = orderProductRepository;
	}
	@Override
	public OrderProduct create(OrderProduct orderProduct) {
		return this.orderProductRepository.save(orderProduct);
	}
}