package com.hcl.shopforhome.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hcl.shopforhome.bean.Order;
import com.hcl.shopforhome.bean.OrderProduct;
import com.hcl.shopforhome.dao.OrderDao;

import java.time.LocalDate;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderRepository;

	public OrderServiceImpl(OrderDao orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Iterable<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public Order create(Order order) {
		order.setDateCreated(LocalDate.now());
		return this.orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		this.orderRepository.save(order);
	}
}