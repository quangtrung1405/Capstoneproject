package com.hcl.shopforhome.controller;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.shopforhome.bean.ProductDetails;
import com.hcl.shopforhome.service.ProductService;



@RestController
@RequestMapping("/ProductCRUDS")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService prodService;

	@PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String CreateUserInfo(@RequestBody ProductDetails prod) {
		return prodService.createProduct(prod);
	}

	@GetMapping(value = "retrieveProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDetails> retrieveUserDetails() {
		return prodService.retrieveProducts();
	}

	@PutMapping(value = "updateProduct")
	public String updateUserdetails(@RequestBody ProductDetails prod) {
		return prodService.updateProduct(prod);
	}

	@DeleteMapping(value = "deleteProduct/{id}")
	public String deleteUserDetails(@PathVariable("id") int id) {
		return prodService.deleteProduct(id);
	}
	@GetMapping(value="product/{id}")
	public ProductDetails getUser(@PathVariable("id") int id) {
		return prodService.getProduct(id);
	}
	
	
//	 public static void stockDemandEmail() throws AddressException,MessagingException,IOException{
//			
//		 Properties props = new Properties();
//		   props.put("mail.smtp.auth", "true");
//		   props.put("mail.smtp.starttls.enable", "true");
//		   props.put("mail.smtp.host", "smtp.hcl.com");
//		   props.put("mail.smtp.port", "587");
//
//		 Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//		      protected PasswordAuthentication getPasswordAuthentication() {
//		         return new PasswordAuthentication("govindaraj.g@hcl.com", "Govind");
//		      }
//		   });
//		   Message msg = new MimeMessage(session);
//		   msg.setFrom(new InternetAddress("govindaraj.g@hcl.com", false));
//
//		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("garaj3081@gmail.com"));
//		   msg.setSubject("Testing For Project from hcl machine");
//		   msg.setContent("Hello Admin, ", "text/html");
//		   msg.setSentDate(new Date());
//
//		   MimeBodyPart messageBodyPart = new MimeBodyPart();
//		   messageBodyPart.setContent("Stock needed in the product Id: ", "text/html");
//
//		   Multipart multipart = new MimeMultipart();
//		   multipart.addBodyPart(messageBodyPart);
//		  
//		   
//		   msg.setContent(multipart);
//		   Transport.send(msg);   
//
//         
//	 }


}