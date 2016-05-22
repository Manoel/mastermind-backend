package com.axiomzen.mastermind.domain.model.game;

import com.axiomzen.mastermind.domain.IRepository;

public interface GameRepository extends IRepository<Game, GameId> {

	void add(Game game);

	void update(Game game);

	void remove(GameId gameId);
	
	void join(MultiPlayerGame game);
	
}
