package com.eiphyukhin.salarymgmt.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import com.eiphyukhin.salarymgmt.model.User;

public class CSVHelper {

	public static String TYPE = "text/csv";
	static String[] HEADERS = { "Id", "Login", "Name", "Salary" };
	
	public static boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	}
	
	public static List<User> csvToUsers(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<User> userList = new ArrayList<User>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  System.out.println("Data: " + csvRecord.get(0).toString() +" "+ csvRecord.get(1).toString() +" "+ csvRecord.get(2).toString() +" "+ new BigDecimal(csvRecord.get(3)));
	    	  User user = new User(
	    		csvRecord.get(0).toString(),
	            csvRecord.get(1).toString(),
	            csvRecord.get(2).toString(),
	            new BigDecimal(csvRecord.get(3))
	          );

	    	  userList.add(user);
	      }

	      return userList;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage().toString());
	    }
	  }
}
