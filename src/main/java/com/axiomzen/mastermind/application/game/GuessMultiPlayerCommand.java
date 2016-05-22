package com.axiomzen.mastermind.application.game;

import javax.validation.constraints.NotNull;

public class GuessMultiPlayerCommand {
	
	@NotNull
	private String code;
	
	@NotNull
	private String game_key;
	
	@NotNull
	private int user;
	
	public GuessMultiPlayerCommand() {
	}

	public GuessMultiPlayerCommand(String code, String game_key, int user) {
		super();
		this.code = code;
		this.game_key = game_key;
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGame_key() {
		return game_key;
	}

	public void setGame_key(String game_key) {
		this.game_key = game_key;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public int getUser() {
		return user;
	}

}
