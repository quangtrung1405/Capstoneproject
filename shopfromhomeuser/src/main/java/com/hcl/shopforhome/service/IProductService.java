package com.hcl.shopforhome.service;

import java.util.List;

import com.hcl.shopforhome.bean.Products;
import com.hcl.shopforhome.bean.User;

public interface IProductService {

	public Products getProduct(int id);
	
	public Products createProduct(Products prod);

	public List<Products> retrieveProducts();

	public Products updateProduct(Products prod);

	public String deleteProduct(int id);
}
