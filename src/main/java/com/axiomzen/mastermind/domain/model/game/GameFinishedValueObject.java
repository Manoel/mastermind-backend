package com.axiomzen.mastermind.domain.model.game;

public class GameFinishedValueObject extends GameValueObject {

	private String result;

	private double time_taken;

	private boolean time_over;

	public GameFinishedValueObject(int code_length, String[] colors, String game_key, String guess, int num_guesses,
			PastResultValueObject[] pastResults, boolean solved, boolean multiplayer, String result, double time_taken,
			String user, boolean time_over) {
		super(code_length, colors, game_key, guess, num_guesses, pastResults, solved, multiplayer, user);
		this.result = result;
		this.time_taken = time_taken;
		this.time_over = time_over;
	}

	public GameFinishedValueObject(int code_length, String[] colors, String game_key, String guess, int num_guesses,
			PastResultValueObject[] pastResults, boolean solved, boolean multiplayer, String result, double time_taken,
			String user, boolean time_over, int turn) {
		this(code_length, colors, game_key, guess, num_guesses, pastResults, solved, multiplayer, result, time_taken,
				user, time_over);
		this.turn = turn;
	}

	public String getResult() {
		return result;
	}

	public double getTime_taken() {
		return time_taken;
	}

	public boolean isTime_over() {
		return time_over;
	}

	@Override
	public GameValueObject changeTurnUserMultiPlayer(String firstUser, String secondUser) {
		int turn = (getPastResults().length % 2 == 0) ? 1 : 2;
		String user = (getPastResults().length % 2 == 0) ? firstUser : secondUser;
		return new GameFinishedValueObject(code_length, colors, game_key, guess, num_guesses, pastResults, solved, true,
				result, time_taken, user, time_over, turn);
	}

}
