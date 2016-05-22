package com.axiomzen.mastermind.domain.model.game;

import com.axiomzen.mastermind.domain.DomainObject;

public class PastResultValueObject extends DomainObject {
	
	private int exact;
	
	private String guess;
	
	private int near;

	public PastResultValueObject(int exact, String guess, int near) {
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
