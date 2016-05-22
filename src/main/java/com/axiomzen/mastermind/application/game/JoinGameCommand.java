package com.axiomzen.mastermind.application.game;

import javax.validation.constraints.NotNull;

public class JoinGameCommand {

	@NotNull
	private String user;
	
	@NotNull
	private String game_key;

	public JoinGameCommand() {
	}

	public JoinGameCommand(String user, String game_key) {
		this.user = user;
		this.game_key = game_key;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getGame_key() {
		return game_key;
	}

	public void setGame_key(String game_key) {
		this.game_key = game_key;
	}
}
