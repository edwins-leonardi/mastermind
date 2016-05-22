package com.axiomzen.mastermind.web.service;

import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Stateless;

import com.axiomzen.mastermind.web.exception.InvalidGameKeyException;
import com.axiomzen.mastermind.web.exception.InvalidGuessException;
import com.axiomzen.mastermind.web.model.Game;
import com.axiomzen.mastermind.web.model.GameMode;
import com.axiomzen.mastermind.web.model.GameReport;
import com.axiomzen.mastermind.web.model.GameStatus;
import com.axiomzen.mastermind.web.model.Guess;
import com.axiomzen.mastermind.web.model.User;
import com.axiomzen.mastermind.web.model.UserGame;

@Stateless
public class GameService {

	private static ConcurrentHashMap<String, UserGame> games = new ConcurrentHashMap<String, UserGame>();

	public GameStatus createNewGame(User user) {
		UserGame userGame = createUserGame(user, GameMode.SINGLE_PLAYER);
		games.put(userGame.getGameKey(), userGame);
		return new GameStatus(userGame);
	}

	public GameStatus processUserGuess(Guess guess) throws InvalidGameKeyException, InvalidGuessException {
		UserGame userGame = retrieveUserGame(guess);
		GameStatus gameStatus = userGame.nextGuess(guess.getCode());
		if (gameStatus.getSolved()) {
			games.remove(guess.getGame_key());
			return new GameReport(userGame, gameStatus);
		}
		return gameStatus;
	}

	protected UserGame createUserGame(User user, GameMode gameMode) {
		return new UserGame(new Game(), user, gameMode);
	}

	private UserGame retrieveUserGame(Guess guess) throws InvalidGameKeyException, InvalidGuessException {
		if (guess.getGame_key() == null || games.get(guess.getGame_key()) == null) {
			throw new InvalidGameKeyException();
		}
		UserGame userGame = games.get(guess.getGame_key());
		if (guess.getCode().length() != userGame.getGame().getColorsCode().length) {
			throw new InvalidGuessException(guess.getCode());
		}
		return userGame;
	}

}
