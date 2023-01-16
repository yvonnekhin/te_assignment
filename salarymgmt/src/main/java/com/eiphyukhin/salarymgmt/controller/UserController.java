package com.eiphyukhin.salarymgmt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eiphyukhin.salarymgmt.helper.CSVHelper;
import com.eiphyukhin.salarymgmt.message.ResponseMessage;
import com.eiphyukhin.salarymgmt.model.User;
import com.eiphyukhin.salarymgmt.service.UserService;

@RestController
public class UserController {
	private final UserService userService;
	
	public UserController(UserService employeeService) {
		this.userService = employeeService;
	}
	
	@PostMapping("/users/add")
	public ResponseEntity<User> addEmployee(@RequestBody User employee) {
		User newEmployee = userService.addUser(employee);
		return new ResponseEntity<User>(newEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllEmployees() {
		List<User> employeeList = userService.findAllUsers();
		return new ResponseEntity<List<User>>(employeeList, HttpStatus.OK);
	}
	
	@PostMapping("/users/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		
		if(CSVHelper.hasCSVFormat(file)) {
			try {
				userService.saveFile(file);
				
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload a csv file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
}
