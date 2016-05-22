package com.axiomzen.mastermind.domain.model.game;

import com.axiomzen.mastermind.domain.DomainObject;

public class ResultValueObject extends DomainObject {
	
	private int exact;
	
	private int near;

	public ResultValueObject(int exact, int near) {
		this.exact = exact;
		this.near = near;
	}

	public int getExact() {
		return exact;
	}

	public int getNear() {
		return near;
	}
	
}
