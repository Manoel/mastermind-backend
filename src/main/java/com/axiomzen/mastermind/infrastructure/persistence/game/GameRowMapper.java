package com.axiomzen.mastermind.infrastructure.persistence.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import com.axiomzen.mastermind.domain.model.game.Game;
import com.axiomzen.mastermind.domain.model.game.GameId;
import com.axiomzen.mastermind.domain.model.game.MultiPlayerGame;

public class GameRowMapper implements RowMapper<Game> {

	private Map<String, Game> games = new HashMap<>();

	@Override
	public Game mapRow(ResultSet result, int index) throws SQLException {
		String id = result.getString("GAME_ID");

		Game game = null;

		if (games.containsKey(id)) {
			game = games.get(id);
		} else {
			String code = result.getString("CODE");
			Date startTime = result.getTimestamp("START_TIME");
			String firstPlayer = result.getString("FIRST_PLAYER");
			String secondPlayer = result.getString("SECOND_PLAYER");
			int multiplayer = result.getInt("MULTIPLAYER");

			if (multiplayer == 1) {
				game = new MultiPlayerGame(new GameId(UUID.fromString(id)), code, startTime, firstPlayer, secondPlayer);
			} else {
				game = new Game(new GameId(UUID.fromString(id)), code, startTime, firstPlayer);
			}

			games.put(id, game);
		}

		return game;
	}

	public Game getGame(String id) {
		return games.get(id);
	}

}
