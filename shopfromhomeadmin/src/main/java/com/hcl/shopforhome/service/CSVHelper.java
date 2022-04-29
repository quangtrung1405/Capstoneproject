package com.hcl.shopforhome.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.hcl.shopforhome.bean.ProductDetails;
import com.hcl.shopforhome.controller.CSVController;

public class CSVHelper {
	public static String TYPE = "text/csv";
	public static String TYPE2="application/vnd.ms-excel";
	static String[] HEADERs = { "id", "name", "description", "image","category", "price" ,"stocks"};
	public static boolean hasCSVFormat(MultipartFile file) {
		
		if (!TYPE.equals(file.getContentType()) && !TYPE2.equalsIgnoreCase(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<ProductDetails> csvToTutorials(InputStream inputStream) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<ProductDetails> tutorials = new ArrayList<ProductDetails>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				ProductDetails Products = new ProductDetails(Integer.parseInt(csvRecord.get("id")),
						csvRecord.get("name"),
						csvRecord.get("description"),
						csvRecord.get("image"), 
						Float.parseFloat(csvRecord.get("price")),
						csvRecord.get("category"),
						Integer.parseInt(csvRecord.get("stocks")));

				tutorials.add(Products);
			}

			return tutorials;
		} catch (IOException exception) {
			throw new RuntimeException("fail to parse CSV file: " + exception.getMessage());
		}
	}
}
