package com.eiphyukhin.salarymgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eiphyukhin.salarymgmt.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
