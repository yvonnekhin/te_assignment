package com.eiphyukhin.salarymgmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eiphyukhin.salarymgmt.helper.CSVHelper;
import com.eiphyukhin.salarymgmt.model.User;
import com.eiphyukhin.salarymgmt.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	};
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public void saveFile(MultipartFile file) {
		try {
			List<User> users = CSVHelper.csvToUsers(file.getInputStream());
			userRepository.saveAll(users);
		} catch (Exception e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}
	}

}
