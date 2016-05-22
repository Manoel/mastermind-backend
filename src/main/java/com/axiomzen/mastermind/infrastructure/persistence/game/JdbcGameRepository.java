package com.axiomzen.mastermind.infrastructure.persistence.game;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.axiomzen.mastermind.domain.model.game.Game;
import com.axiomzen.mastermind.domain.model.game.GameId;
import com.axiomzen.mastermind.domain.model.game.GameRepository;
import com.axiomzen.mastermind.domain.model.game.MultiPlayerGame;
import com.axiomzen.mastermind.domain.model.game.PastResult;

@Repository
public class JdbcGameRepository extends JdbcDaoSupport implements GameRepository {

	@Autowired
	public JdbcGameRepository(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public Game load(GameId id) {
		String query = "SELECT G.ID GAME_ID, G.START_TIME START_TIME, G.CODE CODE, G.MULTIPLAYER MULTIPLAYER, G.FIRST_PLAYER FIRST_PLAYER, G.SECOND_PLAYER SECOND_PLAYER "
				+ " FROM mastermind.GAME G WHERE G.ID = ?";

		String queryPastResult = "SELECT P.ID RESULT_ID, P.EXACT EXACT, P.NEAR NEAR, P.GUESS GUESS "
				+ " FROM mastermind.PAST_RESULT P WHERE P.GAME_ID = ? ORDER BY RESULT_ID";
		
		PastResultRowMapper pastResultRowMapper = new PastResultRowMapper();
		
		List<PastResult> pastResults = getJdbcTemplate().query(queryPastResult, pastResultRowMapper, id.value().toString());
		
		GameRowMapper rowMapper = new GameRowMapper();

		Game game = getJdbcTemplate().queryForObject(query, rowMapper, id.value().toString());
		
		game.addAll(pastResults);

		return game;
	}

	@Override
	public void add(Game game) {
		String insertGame = "INSERT INTO mastermind.GAME(ID, START_TIME, CODE, MULTIPLAYER, FIRST_PLAYER, SECOND_PLAYER) VALUES(?, ?, ?, ?, ?, ?)";

		int multiplayer = 0;
		String secondUser = null;
		if (game instanceof MultiPlayerGame) {
			MultiPlayerGame multiPlayerGame = (MultiPlayerGame) game;
			multiplayer = 1;
			secondUser = multiPlayerGame.getSecondPlayer();
		}
		
		getJdbcTemplate().update(insertGame, game.getKey().value().toString(), game.getStartTime(), game.getCode(), multiplayer, game.getUser(), secondUser);
	}

	@Override
	public void update(Game game) {
		String insertPastResult = "INSERT INTO mastermind.PAST_RESULT(EXACT, GUESS, NEAR, GAME_ID) VALUES(?, ?, ?, ?)";

		String deletePastResult = "DELETE FROM mastermind.PAST_RESULT WHERE GAME_ID = ?";
		
		getJdbcTemplate().update(deletePastResult, game.getKey().value().toString());

		for (PastResult p : game.getPastResults()) {
			getJdbcTemplate().update(insertPastResult, p.getExact(), p.getGuess(), p.getNear(),
					game.getKey().value().toString());
		}

	}

	@Override
	public void remove(GameId gameId) {
		String delete = "DELETE FROM mastermind.GAME G WHERE G.ID = ?";
		getJdbcTemplate().update(delete, gameId.value().toString());
	}

	@Override
	public void join(MultiPlayerGame game) {
		String updateSecondPlayer = "UPDATE mastermind.GAME G SET G.SECOND_PLAYER = ?, G.START_TIME = ?";
		getJdbcTemplate().update(updateSecondPlayer, game.getSecondPlayer(), game.getStartTime());
	}

}
