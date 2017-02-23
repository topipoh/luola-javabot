package javabot.api;

import static io.restassured.RestAssured.with;

import org.apache.commons.lang3.tuple.Pair;

public class API {
	
	public static final String BASE_URL = "http://localhost:8080/api";
	public static final String BOARD = BASE_URL + "/board";
	public static final String ACT = BASE_URL + "/act";
	public static final String ADD_PLAYER = BASE_URL + "/add-player";
	public static final String NAME = "SMULTR0N";
	public static final String PASS = "SMULTR0N DESTROY!!1";
	
	public static void addPlayer() {
		with()
			.param("name", NAME)
			.param("pass", PASS)
			.get(ADD_PLAYER);
	}

	public static String getBoard() {
		return with()
				.param("name", NAME)
				.get(BOARD)
				.asString();
	}
	
	public static void act(Pair<Action, Direction> pair) {
		act(pair.getLeft(), pair.getRight());
	}

	public static void act(Action action, Direction direction) {
		with()
			.param("name", NAME)
			.param("pass", PASS)
			.param("action", action)
			.param("target", direction)
		.get(ACT);
	}


}
