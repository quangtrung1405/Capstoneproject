package com.hcl.shopforhome.service;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.shopforhome.bean.Products;
import com.hcl.shopforhome.bean.User;
import com.hcl.shopforhome.customexception.ResourceNotFoundException;
import com.hcl.shopforhome.dao.ProductDao;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	ProductDao productDao;

	public Products createProduct(Products user) {

		if (productDao.existsById(user.getId())) {
			return null;
		} else {
			productDao.save(user);
			return null;
		}
	}

	public List<Products> retrieveProducts() {
		return productDao.findAll();
	}

	public Products updateProduct(Products user) {

		if (!productDao.existsById(user.getId())) {
			return null;
		} else {
			Products us = productDao.getById(user.getId());
			us.setName(user.getName());
			us.setDescription(user.getDescription());
			us.setCategory(user.getCategory());
			us.setImage(user.getImage());
			us.setPrice(user.getPrice());
			us.setStocks(user.getStocks());
			productDao.save(us);
			return user;
		}
	}

	public String deleteProduct(int id) {
		if (!productDao.existsById(id)) {
			return "This ID not exists, Please give Valid ID";
		} else {
			productDao.deleteById(id);
			return "User in the Id number " + id + " has Deleted successfully";
		}
	}

	public void StockDemandEmail() throws AddressException, MessagingException, IOException {

		List<Products> listOfProd = productDao.findAll();
		Iterator<Products> list = listOfProd.iterator();

		while (list.hasNext()) {
			Products prod = list.next();

			if (prod.getStocks() < 10) {
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "192.168.0.106");
				props.put("mail.smtp.socketFactory.port", "25"); // 587 or 465

				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("quangtrung1405@gmail.com", "anhtrung2000");
					}
				});
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("quangtrung1405@hcl.com", false));

				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("quangtrung1405@gmail.com"));
				msg.setSubject("Stock in Demand");
				msg.setContent("Hello Admin, your action is mandatory", "text/html");
				msg.setSentDate(new Date());

				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent("product are", "text/html");

				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);

				msg.setContent(multipart);
				Transport.send(msg);

			}
		}
	}

	@Override
	public Products getProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}




}
