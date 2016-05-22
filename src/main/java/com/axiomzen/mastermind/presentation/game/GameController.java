package com.axiomzen.mastermind.presentation.game;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axiomzen.mastermind.application.game.AddNewGameCommand;
import com.axiomzen.mastermind.application.game.GameApplicationService;
import com.axiomzen.mastermind.application.game.GuessCommand;
import com.axiomzen.mastermind.application.game.GuessMultiPlayerCommand;
import com.axiomzen.mastermind.application.game.JoinGameCommand;
import com.axiomzen.mastermind.domain.model.game.GameValueObject;
import com.axiomzen.mastermind.domain.model.game.NewGameValueObject;

@RestController
public class GameController {

	private GameApplicationService service;

	@Autowired
	public GameController(GameApplicationService service) {
		this.service = service;
	}

	@RequestMapping(value = "/new_game", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NewGameValueObject newGame(@RequestBody @NotNull @Valid AddNewGameCommand cmd) {
		return service.addNewGame(cmd);
	}

	@RequestMapping(value = "/guess", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GameValueObject guess(@RequestBody @NotNull @Valid GuessCommand cmd) {
		return service.guess(cmd);
	}
	
	@RequestMapping(value = "/new_multiplayer_game", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NewGameValueObject newMultiPlayerGame(@RequestBody @NotNull @Valid AddNewGameCommand cmd) {
		return service.addNewMultiPlayerGame(cmd);
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NewGameValueObject join(@RequestBody @NotNull @Valid JoinGameCommand cmd) {
		return service.joinGame(cmd);
	}
	
	@RequestMapping(value = "/guess_multiplayer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GameValueObject guessMultiPlayer(@RequestBody @NotNull @Valid GuessMultiPlayerCommand cmd) {
		return service.guessMultiPlayer(cmd);
	}

}
