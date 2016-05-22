package com.axiomzen.mastermind.domain.model.game;

import com.axiomzen.mastermind.domain.DomainObject;

public class NewGameValueObject extends DomainObject {

	private String[] colors;
	
    private int code_length;
    
    private String game_key;

    private int num_guesses;
    
    private String[] past_results;
    
    private boolean solved;
    
    private boolean multiplayer;
    
    private boolean can_start;
    
    private int turn;
    
    public NewGameValueObject(String[] colors, String game_key, boolean multiplayer, boolean can_start) {
    	this.colors = colors;
    	this.code_length = colors.length;
    	this.game_key = game_key;
    	this.num_guesses = 0;
    	this.past_results = new String[]{};
    	this.solved = false;
    	this.multiplayer = multiplayer;
    	this.can_start = can_start;
    	this.turn = 1;
    }

	public String[] getColors() {
		return colors;
	}

	public int getCode_length() {
		return code_length;
	}

	public String getGame_key() {
		return game_key;
	}

	public int getNum_guesses() {
		return num_guesses;
	}

	public String[] getPast_results() {
		return past_results;
	}

	public boolean isSolved() {
		return solved;
	}
	
	public boolean isMultiplayer() {
		return multiplayer;
	}
	
	public boolean isCan_start() {
		return can_start;
	}
	
	public int getTurn() {
		return turn;
	}
    
}
