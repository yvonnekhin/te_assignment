package com.eiphyukhin.salarymgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eiphyukhin.salarymgmt.exception.EmptyFileUploadException;
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
	
	public List<User> findAllUsers(int limit, int offset) {
		
		// create Pageable instance
		Pageable pageable = PageRequest.of(limit, offset);
		return userRepository.findAll(pageable).getContent();
	}
	
	public User findUserById(String id) {
		return userRepository.findById(id).get();
	}
	
	public void saveFile(MultipartFile file) throws Exception {
		try {
			List<User> userList = CSVHelper.csvToUsers(file.getInputStream());
			List<User> newUsers = new ArrayList<User>(); 
			//List<User> updatedUsers = new ArrayList<User>(); 
			if(!userList.isEmpty() && userList.size() > 0) {
				for (User user : userList) {
				    if(userRepository.findById(user.getId()).isPresent()) {
				    	User existingUser = userRepository.findById(user.getId()).get();
				    	existingUser.setLogin(user.getLogin());
				    	existingUser.setName(user.getName());
				    	existingUser.setSalary(user.getSalary());

				    	userRepository.save(existingUser);
				    } else {
				    	newUsers.add(user);
				    }
				}
				userRepository.saveAll(newUsers);
			} else {
	    		throw new EmptyFileUploadException("There is no data in uploaded file.");
	    	}
			
		} catch (EmptyFileUploadException efe) {
		      throw new EmptyFileUploadException("Uploaded File is empty!");
		}
	}
	
	

	
}
