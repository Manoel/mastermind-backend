package com.axiomzen.mastermind.domain.model.game;

import com.axiomzen.mastermind.domain.Entity;

public class PastResult extends Entity<PastResultId> {
	
	private int exact;
	
	private String guess;
	
	private int near;

	public PastResult(PastResultId id, int exact, String guess, int near) {
		super(id);
		this.exact = exact;
		this.guess = guess;
		this.near = near;
	}
	
	public PastResult(int exact, String guess, int near) {
		this.exact = exact;
		this.guess = guess;
		this.near = near;
	}

	public int getExact() {
		return exact;
	}

	public String getGuess() {
		return guess;
	}

	public int getNear() {
		return near;
	}
	
}
