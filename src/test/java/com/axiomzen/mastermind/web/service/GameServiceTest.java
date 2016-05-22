package com.axiomzen.mastermind.web.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.axiomzen.mastermind.web.model.Color;
import com.axiomzen.mastermind.web.model.Game;
import com.axiomzen.mastermind.web.model.GameMode;
import com.axiomzen.mastermind.web.model.GameStatus;
import com.axiomzen.mastermind.web.model.Guess;
import com.axiomzen.mastermind.web.model.GuessResult;
import com.axiomzen.mastermind.web.model.User;
import com.axiomzen.mastermind.web.model.UserGame;

public class GameServiceTest {

	@Spy
	private GameService gameService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createNewGame() {
		User user = new User("Bill");
		GameStatus gameStatus = gameService.createNewGame(user);
		assertThat(gameStatus, notNullValue());
		assertThat(gameStatus.getGame_key(), notNullValue());
		assertThat(gameStatus.getCode_length(), notNullValue());
		assertThat(gameStatus.getColors(), notNullValue());
		assertThat(gameStatus.getSolved(), equalTo(false));
		assertThat(gameStatus.getNum_guesses(), equalTo(0));
		assertThat(gameStatus.getPast_results().size(), equalTo(0));
	}

	@Test
	public void processUserGuessWith3ExactAnd3Near() throws Exception {
		User user = new User("Bill");
		UserGame userGame = mockUserGame(user);
		when(gameService.createUserGame(user, GameMode.SINGLE_PLAYER)).thenReturn(userGame);
		GameStatus gameStatus = gameService.createNewGame(user);

		Guess guess = new Guess("CRYBCOCC", gameStatus.getGame_key());
		GuessResult result = new GuessResult(3, 3);
		GameStatus currentStatus = gameService.processUserGuess(guess);
		assertThat(currentStatus.getGame_key(), equalTo(gameStatus.getGame_key()));
		assertThat(currentStatus.getPast_results().size(), equalTo(1));
		assertThat(currentStatus.getSolved(), equalTo(false));
		assertThat(currentStatus.getResult(), equalTo(result));

	}

	@Test
	public void checkPastResults() throws Exception {
		User user = new User("Bill");
		UserGame userGame = mockUserGame(user);
		when(gameService.createUserGame(user, GameMode.SINGLE_PLAYER)).thenReturn(userGame);
		GameStatus gameStatus = gameService.createNewGame(user);

		Guess guess = new Guess("CRYBCOCC", gameStatus.getGame_key());
		GameStatus currentStatus = gameService.processUserGuess(guess);
		guess = new Guess("CRYBOCCC", gameStatus.getGame_key());
		currentStatus = gameService.processUserGuess(guess);

		assertThat(currentStatus.getPast_results().size(), equalTo(2));

	}

	@Test
	public void checkUserWinning() throws Exception {
		User user = new User("Bill");
		UserGame userGame = mockUserGame(user);
		when(gameService.createUserGame(user, GameMode.SINGLE_PLAYER)).thenReturn(userGame);
		GameStatus gameStatus = gameService.createNewGame(user);

		Guess guess = new Guess("CRYCOBGM", gameStatus.getGame_key());
		GuessResult result = new GuessResult(8, 0);
		GameStatus currentStatus = gameService.processUserGuess(guess);
		assertThat(currentStatus.getGame_key(), equalTo(gameStatus.getGame_key()));
		assertThat(currentStatus.getPast_results().size(), equalTo(1));
		assertThat(currentStatus.getSolved(), equalTo(true));
		assertThat(currentStatus.getResult(), equalTo(result));

	}

	private UserGame mockUserGame(User user) {
		Color[] colorsCode = new Color[] { Color.C, Color.R, Color.Y, Color.C, Color.O, Color.B, Color.G, Color.M };
		Game game = new Game();
		game.setColorsCode(colorsCode);
		return new UserGame(game, user, GameMode.SINGLE_PLAYER);
	}
}
