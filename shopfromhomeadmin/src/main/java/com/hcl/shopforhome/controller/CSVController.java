package com.hcl.shopforhome.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.shopforhome.bean.ProductDetails;
import com.hcl.shopforhome.service.CSVHelper;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin()
public class CSVController {
 Logger logger = LoggerFactory.getLogger(CSVController.class);
	@Autowired
	com.hcl.shopforhome.service.CSVService fileService;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		logger.info("" + file.getContentType());
		logger.info("message " + CSVHelper.hasCSVFormat(file));
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception exception) {
				System.out.println(exception + "\n" + exception.getMessage());
				message = "Could not upload : " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/get")
	public ResponseEntity<List<ProductDetails>> getAllTutorials() {
		try {
			List<ProductDetails> products = fileService.getAllTutorials();

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}