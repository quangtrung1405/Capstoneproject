package com.hcl.shopforhome.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hcl.shopforhome.bean.Order;
import com.hcl.shopforhome.bean.OrderProduct;
import com.hcl.shopforhome.bean.OrderProductDto;
import com.hcl.shopforhome.bean.OrderStatus;
import com.hcl.shopforhome.customexception.ResourceNotFoundException;
import com.hcl.shopforhome.service.OrderProductService;
import com.hcl.shopforhome.service.OrderService;
import com.hcl.shopforhome.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderProductService orderProductService;

	public OrderController(ProductService productService, OrderService orderService,
			OrderProductService orderProductService) {
		this.productService = productService;
		this.orderService = orderService;
		this.orderProductService = orderProductService;

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Order> list() {
		return this.orderService.getAllOrders();
	}

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody OrderForm form) {
		List<OrderProductDto> formDtos = form.getProductOrders();
		validateProductsExistence(formDtos);
		Order order = new Order();
		order.setStatus(OrderStatus.PAID.name());
		order = this.orderService.create(order);

		List<OrderProduct> orderProducts = new ArrayList<>();
		for (OrderProductDto dto : formDtos) {
			orderProducts.add(orderProductService.create(new OrderProduct(order,
					productService.getProduct(dto.getProduct().getId()), dto.getQuantity())));
		}

		order.setOrderProducts(orderProducts);

		this.orderService.update(order);
		try {
			productService.StockDemandEmail();
		} catch (Exception e) {
			System.out.println(e);
		}

		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}")
				.buildAndExpand(order.getId()).toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);

		return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
	}

	private void validateProductsExistence(List<OrderProductDto> orderProducts) {
		List<OrderProductDto> list = orderProducts.stream()
				.filter(op -> Objects.isNull(productService.getProduct(op.getProduct().getId())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product not found");
		}

	}

	public static class OrderForm {

		private List<OrderProductDto> productOrders;

		public List<OrderProductDto> getProductOrders() {
			return productOrders;
		}

		public void setProductOrders(List<OrderProductDto> productOrders) {
			this.productOrders = productOrders;
		}

	}

   
}
