package com.axiomzen.mastermind.web.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatus {
	private Color[] colors;
	private Integer code_length;
	private String game_key;
	private Integer num_guesses;
	private List<GuessResult> past_results;
	private Boolean solved;
	private String guess;
	private GuessResult result;

	public GameStatus(UserGame userGame) {
		copyPropertiesFromUserGame(userGame);
	}

	public GameStatus(UserGame userGame, String guess, GuessResult result) {
		this.result = result;
		this.guess = guess;
		copyPropertiesFromUserGame(userGame);
	}

	private void copyPropertiesFromUserGame(UserGame userGame) {
		colors = Color.values();
		code_length = colors.length;
		game_key = userGame.getGameKey();
		num_guesses = userGame.getNumOfGuesses();
		past_results = userGame.getPastResults();
		solved = userGame.getGame().getSolved();
	}

}
