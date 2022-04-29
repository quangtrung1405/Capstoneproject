package com.hcl.shopforhome.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hcl.shopforhome.bean.ProductDetails;
import com.hcl.shopforhome.dao.ProductDao;

@Service
public class CSVService implements ICSVService {
	@Autowired
	ProductDao repository;

	public void save(MultipartFile file) {
		try {
			List<ProductDetails> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
			repository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

	public List<ProductDetails> getAllTutorials() {
		return repository.findAll();
	}
}
