package com.axiomzen.mastermind.infrastructure.persistence.game;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.axiomzen.mastermind.domain.model.game.PastResult;

public class PastResultRowMapper implements RowMapper<PastResult> {

	@Override
	public PastResult mapRow(ResultSet result, int index) throws SQLException {
		String guess = result.getString("GUESS");
		int exact = result.getInt("EXACT");
		int near = result.getInt("NEAR");

		return new PastResult(exact, guess, near);
	}

}
