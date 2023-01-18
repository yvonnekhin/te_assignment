package com.eiphyukhin.salarymgmt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eiphyukhin.salarymgmt.message.ResponseMessage;
import com.eiphyukhin.salarymgmt.model.User;
import com.eiphyukhin.salarymgmt.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/users/add")
	public ResponseEntity<User> addEmployee(@RequestBody User employee) {
		User newEmployee = userService.addUser(employee);
		return new ResponseEntity<User>(newEmployee, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<Map<String, Object>>getAllUsers(@RequestParam(defaultValue = "0") int offset,
	        @RequestParam(defaultValue = "30") int limit, @RequestParam(defaultValue = "id") String[] sort) {
		try {
			List<User> users = new ArrayList<User>();
			Pageable paging = PageRequest.of(offset, limit);
			Page<User> pageUsers  = userService.findAllUsers(paging);
			users = pageUsers.getContent();
			
			Map<String, Object> response = new HashMap<>();
			response.put("users", users);
		    response.put("currentPage", pageUsers.getNumber());
		    response.put("totalItems", pageUsers.getTotalElements());
		    response.put("totalPages", pageUsers.getTotalPages());
		    
		    
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		
	}
	
	@PostMapping("/users/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		
		try {
			userService.saveFile(file);
			
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Unable to upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
}
