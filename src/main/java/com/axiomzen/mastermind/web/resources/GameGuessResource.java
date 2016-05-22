package com.axiomzen.mastermind.web.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.axiomzen.mastermind.web.exception.InvalidGameKeyException;
import com.axiomzen.mastermind.web.exception.InvalidGuessException;
import com.axiomzen.mastermind.web.model.GameStatus;
import com.axiomzen.mastermind.web.model.Guess;
import com.axiomzen.mastermind.web.service.GameService;

@Produces("application/json")
@Consumes("application/json")
@Path(value = "guess")
public class GameGuessResource {
	@Inject
	private GameService gameService;

	@POST
	public GameStatus tryToDecipherCode(Guess guessDTO) throws InvalidGameKeyException, InvalidGuessException {
		return gameService.processUserGuess(guessDTO);
	}
}
