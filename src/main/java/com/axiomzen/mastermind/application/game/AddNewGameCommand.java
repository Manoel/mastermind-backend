package com.axiomzen.mastermind.application.game;

import javax.validation.constraints.NotNull;

public class AddNewGameCommand {
	
	@NotNull
	private String user;
	
	public AddNewGameCommand() {
	}
	
	public AddNewGameCommand(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

}
