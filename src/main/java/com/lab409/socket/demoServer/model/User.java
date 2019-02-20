package com.lab409.socket.demoServer.model;

import java.io.Serializable;

public class User implements Serializable {
	private Long id;
	private String name;
	private String pwd;

	public User() {}

	public User(Long id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "id: " + id + "name: " + name + "password: " + pwd;
	}
}