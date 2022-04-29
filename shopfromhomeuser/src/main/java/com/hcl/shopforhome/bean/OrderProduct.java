package com.hcl.shopforhome.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderProduct {

	public OrderProduct() {
		super();
	}

	@EmbeddedId
	private OrderProductPack productPack;

	@Column(nullable = false)
	private Integer quantity;
	
	public OrderProduct(Order order, Products product, Integer quantity) {
		productPack = new OrderProductPack();
		productPack.setOrder(order);
		productPack.setProduct(product);

		this.quantity = quantity;
	}

	@Transient
	public Products getProduct() {
		return this.productPack.getProduct();
	}

	public OrderProductPack getPk() {
		return productPack;
	}

	public void setPk(OrderProductPack productPack) {
		this.productPack = productPack;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Transient
	public Double getTotalPrice() {
		return (double) (getProduct().getPrice() * getQuantity());
	}
}
