package com.axiomzen.mastermind.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String name;

	public User() {
	}

	public User(String name) {
		this.name = name;
	}
}
