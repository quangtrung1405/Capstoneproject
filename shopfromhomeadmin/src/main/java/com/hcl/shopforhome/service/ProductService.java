package com.hcl.shopforhome.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.hcl.shopforhome.bean.ProductDetails;
import com.hcl.shopforhome.customexception.ResourceNotFoundException;
import com.hcl.shopforhome.dao.ProductDao;

@Service
@Transactional
public class ProductService {
	@Autowired
	ProductDao productDao;
	@Autowired
	private JavaMailSender mail;

	public ProductDetails getProduct(int id) {
		return productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	public String createProduct(ProductDetails prod) {

		if (productDao.existsById(prod.getId())) {
			return "This ID already Exists, Please Give different ID";
		} else {
			productDao.save(prod);
			return "Product Created successfully";
		}
	}
	public void createProduct(String image,String name,String description,double price,String category,int stocks) 
	{
		ProductDetails p = new ProductDetails();
		p.setImage(image);
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setCategory(category);
        p.setStocks(stocks);
        
        productDao.save(p);
	}
	

	public List<ProductDetails> retrieveProducts() {
		return productDao.findAll();
	}

	public String updateProduct(ProductDetails prod) {

		if (!productDao.existsById(prod.getId())) {
			return "This ID not Exist, Please give correct ID";
		} else {
			ProductDetails pd = productDao.getById(prod.getId());
			pd.setName(prod.getName());
			pd.setDescription(prod.getDescription());
			pd.setImage(prod.getImage());
			pd.setPrice(prod.getPrice());
			pd.setStocks(prod.getStocks());
			productDao.saveAndFlush(pd);
			return "Product Details updated Successfully";
		}
	}

	public String deleteProduct(int id) {
		if (!productDao.existsById(id)) {
			return "This ID not exists, Please give Valid ID";
		} else {
			productDao.deleteById(id);
			return "Product in the Id number " + id + " has Deleted successfully";
		}
	}
	public void sendMail(String toEmail,
							String subject,
							String body) {
		/*ProductDetails pds = null;
		ProductDetails pd=productDao.findByStocks(pds.getStocks());
		if (Objects.nonNull(pd)) {
			boolean len = pds.getStocks()<10;*/
	
		SimpleMailMessage message =new SimpleMailMessage();
		message.setFrom("quangtrung1405@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mail.send(message);
		
		System.out.println("Mail Sent Successfully!!!");
		
	}
}

