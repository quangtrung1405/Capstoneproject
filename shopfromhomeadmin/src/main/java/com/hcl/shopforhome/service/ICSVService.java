package com.hcl.shopforhome.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hcl.shopforhome.bean.ProductDetails;

public interface ICSVService {
	public void save(MultipartFile file);

	public List<ProductDetails> getAllTutorials();
}
