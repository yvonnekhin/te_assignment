package com.eiphyukhin.salarymgmt.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.eiphyukhin.salarymgmt.model.User;

public class CSVHelper {
	
	static String[] HEADERS = { "Id", "Login", "Name", "Salary" };
	final String regExp = "[0-9]+([,.][0-9]{1,2})?";
		
	public static List<User> csvToUsers(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<User> filteredUsers = new ArrayList<User>();
	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      if (csvRecords instanceof Collection<?>) {
	    	  if(((Collection<?>)csvRecords).size() > 0) {
	    		  for (CSVRecord csvRecord : csvRecords) {
	    			  String specialCharactersString = "!@#$%&*()'+,-/:;<=>?[]^_`{|}";
	    			  if(validateSalary(csvRecord.get(3))) {
    					  System.out.println("Data: " + csvRecord.get(0).toString() +" "+ csvRecord.get(1).toString() +" "+ csvRecord.get(2).toString() +" "+ new BigDecimal(csvRecord.get(3)));
		    	    	  User user = new User(
		    	    		csvRecord.get(0).toString(),
		    	            csvRecord.get(1).toString(),
		    	            csvRecord.get(2).toString(),
		    	            new BigDecimal(csvRecord.get(3))
		    	          );
		    	    	  System.out.println("======== " + user.getId());
		    	    	  filteredUsers.add(user);
    				  }
//	    			  if(!csvRecord.get(3).matches("-?[0-9]+,-?[0-9]+")) {
//	    				  if(validateSalary(csvRecord.get(3))) {
//	    					  System.out.println("Data: " + csvRecord.get(0).toString() +" "+ csvRecord.get(1).toString() +" "+ csvRecord.get(2).toString() +" "+ new BigDecimal(csvRecord.get(3)));
//			    	    	  User user = new User(
//			    	    		csvRecord.get(0).toString(),
//			    	            csvRecord.get(1).toString(),
//			    	            csvRecord.get(2).toString(),
//			    	            new BigDecimal(csvRecord.get(3))
//			    	          );
//			    	    	  System.out.println("======== " + user.getId());
//			    	    	  filteredUsers.add(user);
//	    				  }
//	    			  }
	    	      }
	    	  }
	    	} 

	      return filteredUsers;
	    } 
	    catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage().toString());
	    }
	  }
	
	public static boolean validateSalary(String strSalary) {
		BigDecimal decimal = new BigDecimal(strSalary);
        return decimal.signum() > 0 && decimal.scale() < 3;
	}
}
