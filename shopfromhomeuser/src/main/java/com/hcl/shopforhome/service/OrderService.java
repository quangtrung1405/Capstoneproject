package com.hcl.shopforhome.service;

import org.springframework.validation.annotation.Validated;

import com.hcl.shopforhome.bean.Order;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {

	@NotNull
	Iterable<Order> getAllOrders();

	Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

	void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}