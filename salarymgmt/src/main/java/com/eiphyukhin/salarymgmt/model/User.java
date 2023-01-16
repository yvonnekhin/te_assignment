package com.eiphyukhin.salarymgmt.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User implements Serializable {

	@Id
	@Column(name="id", nullable = false, updatable = false)
	private String id;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "salary", precision = 15, scale = 2)
	private BigDecimal salary;
	

	public User() {
	}


	public User(String id, String login, String name, BigDecimal salary) {
		super();
		this.id = id;
		this.login = login;
		this.name = name;
		this.salary = salary;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	
		
	
}
