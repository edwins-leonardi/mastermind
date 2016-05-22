package com.axiomzen.mastermind.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class UserGame {

	private Game game;
	private String gameKey;
	private User user;
	private Integer numOfGuesses;
	private List<GuessResult> pastResults;
	private GameMode gameMode;

	public UserGame(Game game, User user, GameMode gameMode) {
		this.game = game;
		this.user = user;
		this.gameKey = UUID.randomUUID().toString();
		this.pastResults = new ArrayList<GuessResult>();
		this.numOfGuesses = 0;
		this.gameMode = gameMode;
	}

	public GameStatus nextGuess(String code) {
		numOfGuesses++;
		GuessResult result = processResult(code);
		pastResults.add(result);
		game.setSolved(result.getExact() == game.getColorsCode().length);
		return new GameStatus(this, code, result);
	}

	private GuessResult processResult(String guessedCode) {
		GuessResult result = new GuessResult();
		List<Color> resultColors = new ArrayList<Color>(Arrays.asList(game.getColorsCode()));
		List<Color> guessedColors = convertCodeToColorsList(guessedCode);
		computeExactMatches(resultColors, guessedColors, result);
		computeNearMatches(resultColors, guessedColors, result);
		return result;
	}

	private void computeExactMatches(List<Color> resultColors, List<Color> guessedColors, GuessResult result) {
		Iterator<Color> guesses = guessedColors.iterator();
		for (Iterator<Color> results = resultColors.iterator(); results.hasNext();) {
			Color resultColor = results.next();
			if (isExactMatch(resultColor, guesses.next())) {
				result.addExactScore();
				results.remove();
				guesses.remove();
			}
		}
	}

	private boolean isExactMatch(Color resultColor, Color guessedColor) {
		return resultColor.equals(guessedColor);
	}

	private void computeNearMatches(List<Color> resultColors, List<Color> guessedColors, GuessResult result) {
		for (Iterator<Color> guesses = guessedColors.iterator(); guesses.hasNext();) {
			if (isNearMatch(resultColors, guesses.next())) {
				result.addNearScore();
				guesses.remove();
			}
		}
	}

	private boolean isNearMatch(List<Color> resultColors, Color guessedColor) {
		for (Iterator<Color> results = resultColors.iterator(); results.hasNext();) {
			Color color = results.next();
			if (color.equals(guessedColor)) {
				results.remove();
				return true;
			}
		}
		return false;
	}

	private List<Color> convertCodeToColorsList(String code) {
		List<Color> guessedColors = new ArrayList<Color>();
		for (int i = 0; i < code.length(); i++) {
			String guessedColor = "" + code.charAt(i);
			guessedColors.add(Color.valueOf(guessedColor));
		}
		return guessedColors;
	}

}
