package com.axiomzen.mastermind.domain.model.game;

public class GameUfinishedValueObject extends GameValueObject {

	private ResultValueObject result;

	public GameUfinishedValueObject(int code_length, String[] colors, String game_key, String guess, int num_guesses,
			PastResultValueObject[] pastResults, boolean solved, boolean multiplayer, String user,
			ResultValueObject result) {
		super(code_length, colors, game_key, guess, num_guesses, pastResults, solved, multiplayer, user);
		this.result = result;
	}

	public GameUfinishedValueObject(int code_length, String[] colors, String game_key, String guess, int num_guesses,
			PastResultValueObject[] pastResults, boolean solved, boolean multiplayer, String user,
			ResultValueObject result, int turn) {
		this(code_length, colors, game_key, guess, num_guesses, pastResults, solved, multiplayer, user, result);
		this.turn = turn;
	}

	public ResultValueObject getResult() {
		return result;
	}

	@Override
	public GameValueObject changeTurnUserMultiPlayer(String firstUser, String secondUser) {
		int turn = (getPastResults().length % 2 == 0) ? 1 : 2;
		String user = (getPastResults().length % 2 == 0) ? firstUser : secondUser;
		return new GameUfinishedValueObject(code_length, colors, game_key, guess, num_guesses, pastResults, solved,
				true, user, result, turn);
	}

}
