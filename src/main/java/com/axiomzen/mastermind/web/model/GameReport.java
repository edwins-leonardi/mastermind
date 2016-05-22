package com.axiomzen.mastermind.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameReport extends GameStatus {
	private Double time_taken;
	private String further_instructions;

	public GameReport(UserGame userGame, GameStatus gameStatus) {
		super(userGame);
		this.further_instructions = "You're good. Get in touch with us for more fun challenges!";
		this.time_taken = (System.currentTimeMillis() - userGame.getGame().getCreationTime().getTime()) / 1000.0;
		this.setResult(gameStatus.getResult());
		this.setGuess(gameStatus.getGuess());
	}
}
