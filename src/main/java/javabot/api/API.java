package javabot.api;

import static io.restassured.RestAssured.with;

import org.apache.commons.lang3.tuple.Pair;

public class API {
	
	public static final String BASE_URL = "http://localhost:8080/api";
	public static final String BOARD = BASE_URL + "/board";
	public static final String ACT = BASE_URL + "/act";
	public static final String ADD_PLAYER = BASE_URL + "/add-player";
	
	public static void addPlayer(String name, String pass) {
		with()
			.param("name", name)
			.param("pass", pass)
			.get(ADD_PLAYER);
	}

	public static String getBoard(String name) {
		return with()
				.param("name", name)
				.get(BOARD)
				.asString();
	}
	
	public static void act(String name, String pass, Pair<Action, Direction> pair) {
		act(name, pass, pair.getLeft(), pair.getRight());
	}

	public static void act(String name, String pass, Action action, Direction direction) {
		with()
			.param("name", name)
			.param("pass", pass)
			.param("action", action)
			.param("target", direction)
		.get(ACT);
	}
}
