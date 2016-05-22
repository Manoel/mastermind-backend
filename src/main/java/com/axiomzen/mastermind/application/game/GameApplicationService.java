package com.axiomzen.mastermind.application.game;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axiomzen.mastermind.domain.model.game.Game;
import com.axiomzen.mastermind.domain.model.game.GameId;
import com.axiomzen.mastermind.domain.model.game.GameRepository;
import com.axiomzen.mastermind.domain.model.game.GameValueObject;
import com.axiomzen.mastermind.domain.model.game.MultiPlayerGame;
import com.axiomzen.mastermind.domain.model.game.NewGameValueObject;

@Service
public class GameApplicationService {

	private GameRepository repository;

	@Autowired
	public GameApplicationService(GameRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public NewGameValueObject addNewGame(AddNewGameCommand cmd) {
		Game game = new Game(cmd.getUser());
		repository.add(game);
		return game.getNewGameValueObject();
	}

	@Transactional
	public GameValueObject guess(GuessCommand cmd) {
		GameId gameId = new GameId(UUID.fromString(cmd.getGame_key()));
		Game game = repository.load(gameId);
		GameValueObject gameValueObject = game.addResult(cmd.getCode());
		repository.update(game);
		if (game.isTimeOver()) {
			repository.remove(gameId);
		}
		return gameValueObject;
	}

	@Transactional
	public NewGameValueObject addNewMultiPlayerGame(AddNewGameCommand cmd) {
		MultiPlayerGame multiPlayerGame = new MultiPlayerGame(cmd.getUser());
		repository.add(multiPlayerGame);
		return multiPlayerGame.getNewGameValueObject();
	}

	@Transactional
	public NewGameValueObject joinGame(JoinGameCommand cmd) {
		GameId gameId = new GameId(UUID.fromString(cmd.getGame_key()));
		MultiPlayerGame game = (MultiPlayerGame) repository.load(gameId);
		game.setSecondPlayer(cmd.getUser());
		game.setStartTime(new Date());
		repository.join(game);
		return game.getStartGameValueObject();
	}

	@Transactional
	public GameValueObject guessMultiPlayer(GuessMultiPlayerCommand cmd) {
		GameId gameId = new GameId(UUID.fromString(cmd.getGame_key()));
		MultiPlayerGame game = (MultiPlayerGame) repository.load(gameId);
		GameValueObject gameValueObject = game.addResult(cmd.getCode());
		repository.update(game);
		if (game.isTimeOver()) {
			repository.remove(gameId);
		}
		return gameValueObject;
	}

}
