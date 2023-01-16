package com.eiphyukhin.salarymgmt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eiphyukhin.salarymgmt.model.User;
import com.eiphyukhin.salarymgmt.service.UserService;

@RestController
public class UserController {
	private final UserService employeeService;
	
	public UserController(UserService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping("/users/add")
	public ResponseEntity<User> addEmployee(@RequestBody User employee) {
		User newEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<User>(newEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllEmployees() {
		List<User> employeeList = employeeService.findAllEmployees();
		return new ResponseEntity<List<User>>(employeeList, HttpStatus.OK);
	}
}
