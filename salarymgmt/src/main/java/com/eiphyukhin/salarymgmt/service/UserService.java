package com.eiphyukhin.salarymgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eiphyukhin.salarymgmt.model.User;
import com.eiphyukhin.salarymgmt.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository employeeRepo;

	@Autowired
	public UserService(UserRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	};
	
	public User addEmployee(User employee) {
		return employeeRepo.save(employee);
	}
	
	public List<User> findAllEmployees() {
		return employeeRepo.findAll();
	}

}
