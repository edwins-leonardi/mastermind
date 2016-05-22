package com.axiomzen.mastermind.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guess {
	private String code;
	private String game_key;

	public Guess() {

	}

	public Guess(String code, String gameKey) {
		this.code = code;
		this.game_key = gameKey;
	}
}
