package com.axiomzen.mastermind.domain.model.game;

import java.util.Date;

public class MultiPlayerGame extends Game {

	private String secondPlayer;

	public MultiPlayerGame(String firstPlayer) {
		super(firstPlayer);
	}

	public MultiPlayerGame(GameId id, String code, Date startTime, String firstUser, String secondPlayer) {
		super(id, code, startTime, firstUser);
		this.secondPlayer = secondPlayer;
	}

	@Override
	public NewGameValueObject getNewGameValueObject() {
		return new NewGameValueObject(COLORS, getKey().value().toString(), true, false);
	}

	public NewGameValueObject getStartGameValueObject() {
		return new NewGameValueObject(COLORS, getKey().value().toString(), true, true);
	}

	@Override
	public GameValueObject addResult(String guess) {
		GameValueObject gameValueObject = super.addResult(guess);
		return gameValueObject.changeTurnUserMultiPlayer(getUser(), secondPlayer);
	}

	public String getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(String secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;

	}

}
