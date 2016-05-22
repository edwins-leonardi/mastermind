package com.axiomzen.mastermind.web.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.axiomzen.mastermind.web.model.GameStatus;
import com.axiomzen.mastermind.web.model.User;
import com.axiomzen.mastermind.web.service.GameService;

@Produces("application/json")
@Consumes("application/json")
@Path(value = "new_game")
public class NewGameResource {

	@Inject
	private GameService gameService;

	@POST
	public GameStatus startNewGame(User user) {
		return gameService.createNewGame(user);
	}
}
