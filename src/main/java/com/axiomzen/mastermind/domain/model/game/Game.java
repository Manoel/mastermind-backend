package com.axiomzen.mastermind.domain.model.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.axiomzen.mastermind.domain.Entity;

public class Game extends Entity<GameId> {

	public static final int NUM_COLORS = 8;

	public static final String[] COLORS = { "R", "B", "G", "Y", "O", "P", "C", "M" };
	
	public static final int FIVE_MINUTES = 5 * 60;

	protected Date startTime;

	private String code;

	private List<PastResult> pastResults;

	private String user;
	
	private double time_taken;

	public Game(String user) {
		this(new GameId(UUID.randomUUID()), createNewRandomCode(), new Date(), user);
	}

	public Game(GameId id, String code, Date startTime, String user) {
		super(id);
		this.startTime = startTime;
		this.code = code;
		this.user = user;
		pastResults = new ArrayList<>();
	}

	public Date getStartTime() {
		return startTime;
	}

	public String getCode() {
		return code;
	}

	public List<PastResult> getPastResults() {
		return pastResults;
	}
	
	public void addPastResult(PastResult pastResult) {
		getPastResults().add(pastResult);
	}

	public String getUser() {
		return user;
	}	
	
	public boolean isTimeOver() {
		time_taken = getTimeTaken();
		return time_taken >= FIVE_MINUTES;
	}
	
	public NewGameValueObject getNewGameValueObject() {
		return new NewGameValueObject(COLORS, getKey().value().toString(), false, true);
	}

	private static String createNewRandomCode() {  
		StringBuilder code = new StringBuilder();

		Random random = new Random();

		while (code.length() < NUM_COLORS) {
			int index = random.nextInt(NUM_COLORS);
			code.append(COLORS[index]);
		}

		return code.toString();

	}

	private GameValueObject getGameValueObject(String guess) {
		int code_length = NUM_COLORS;
		String[] colors = COLORS;
		String game_key = getKey().value().toString();
		ResultValueObject resultValueObject = getResultValueObject(getPastResults().get(getPastResults().size() - 1));
		PastResultValueObject[] pastResults = getPastResultsValueObject();
		int num_guesses = pastResults.length;
		boolean solved = code.equals(guess);

		GameValueObject gameValueObject = null;
		
		boolean timeOver = isTimeOver();

		if (solved || timeOver) {
			String result = timeOver ? "Time over!" :"You win!";
			gameValueObject = new GameFinishedValueObject(code_length, colors, game_key, guess, num_guesses, pastResults,
					solved, false, result, time_taken, user, timeOver);
		} else {
			gameValueObject = new GameUfinishedValueObject(code_length, colors, game_key, guess, num_guesses,
					pastResults, solved, false, user, resultValueObject);
		}

		return gameValueObject;
	}

	public GameValueObject addResult(String guess) {
		
		int exact = 0;
		int near = 0;

		StringBuilder code = new StringBuilder(this.code);

		Set<Integer> exactPositions = new HashSet<>();

		for (int i = 0; i < NUM_COLORS; i++) {
			if (guess.charAt(i) == code.charAt(i - exact)) {
				code = code.replace(i - exact, i - exact + 1, "");
				exact++;
				exactPositions.add(i);
			}
		}

		for (int i = 0; i < NUM_COLORS; i++) {
			if (!exactPositions.contains(i)) {

				int index = code.indexOf(guess.charAt(i) + "");

				if (index != -1) {
					near++;
					code = code.replace(index, index + 1, "");
				}

			}

		}

		getPastResults().add(new PastResult(exact, guess, near));
		
		return getGameValueObject(guess);
	}

	private double getTimeTaken() {
		Date now = new Date();
		long difference = now.getTime() - getStartTime().getTime();
		return difference / 1000;
	}

	private ResultValueObject getResultValueObject(PastResult pastResult) {
		return new ResultValueObject(pastResult.getExact(), pastResult.getNear());
	}

	private PastResultValueObject[] getPastResultsValueObject() {
		PastResultValueObject[] pastResultsVO = new PastResultValueObject[pastResults.size()];

		for (int i = 0; i < pastResults.size(); i++) {
			PastResult pastResult = pastResults.get(i);
			pastResultsVO[i] = new PastResultValueObject(pastResult.getExact(), pastResult.getGuess(),
					pastResult.getNear());
		}

		return pastResultsVO;
	}

	public void addAll(List<PastResult> pastResults) {
		getPastResults().addAll(pastResults);
	}

}
