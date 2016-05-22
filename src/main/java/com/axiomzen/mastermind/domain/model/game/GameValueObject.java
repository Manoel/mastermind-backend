package com.axiomzen.mastermind.domain.model.game;

import com.axiomzen.mastermind.domain.DomainObject;

public abstract class GameValueObject extends DomainObject {

	protected int code_length;

	protected String[] colors;

	protected String game_key;

	protected String guess;

	protected int num_guesses;

	protected PastResultValueObject[] pastResults;

	protected boolean solved;

	protected boolean multiplayer;

	protected int turn;
	
	protected String user;

	public GameValueObject(int code_length, String[] colors, String game_key, String guess, int num_guesses,
			PastResultValueObject[] pastResults, boolean solved, boolean multiplayer, String user) {
		this.code_length = code_length;
		this.colors = colors;
		this.game_key = game_key;
		this.guess = guess;
		this.num_guesses = num_guesses;
		this.pastResults = pastResults;
		this.solved = solved;
		this.multiplayer = multiplayer;
		this.turn = 1;
		this.user = user;
	}

	public int getCode_length() {
		return code_length;
	}

	public String[] getColors() {
		return colors;
	}

	public String getGame_key() {
		return game_key;
	}

	public String getGuess() {
		return guess;
	}

	public int getNum_guesses() {
		return num_guesses;
	}

	public PastResultValueObject[] getPastResults() {
		return pastResults;
	}

	public boolean isSolved() {
		return solved;
	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public int getTurn() {
		return turn;
	}
	
	public String getUser() {
		return user;
	}

	public abstract GameValueObject changeTurnUserMultiPlayer(String firstUser, String secondUser);
	

}
