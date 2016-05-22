package com.axiomzen.mastermind.application.game;

import javax.validation.constraints.NotNull;

public class GuessCommand {
	
	@NotNull
	private String code;
	
	@NotNull
	private String game_key;
	
	public GuessCommand() {
	}

	public GuessCommand(String code, String game_key) {
		super();
		this.code = code;
		this.game_key = game_key;
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
	
}
