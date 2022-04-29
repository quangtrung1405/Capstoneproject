package com.hcl.shopforhome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.hcl.shopforhome.bean.ProductDetails;

@Repository
public interface ProductDao extends JpaRepository<ProductDetails, Integer> {

	public ProductDetails findByStocks(@Param("stocks") int i);
}
