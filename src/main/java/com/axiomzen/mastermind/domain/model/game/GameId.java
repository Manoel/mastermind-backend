package com.axiomzen.mastermind.domain.model.game;

import java.util.UUID;

import com.axiomzen.mastermind.domain.KeyIdentity;

public class GameId extends KeyIdentity<UUID> {

	public GameId(UUID value) {
		super(value);
	}

}
