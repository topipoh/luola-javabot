package javabot;

import static io.restassured.RestAssured.*;

import java.util.Random;

public class Bot {
	
	private static final String BASE_URL = "http://localhost:8080/api";
	private static final String BOARD = BASE_URL + "/board";
	private static final String ACT = BASE_URL + "/act";
	private static final String ADD_PLAYER = BASE_URL + "/add-player";
	private static final String NAME = "SMULTR0N";
	private static final String PASS = "SMULTR0N DESTROY!!1";
	
	private Random random = new Random();

	public static void main(String[] args) throws Exception {
		Bot bot = new Bot();
		bot.run();
	}

	public void run() throws Exception {
		System.out.println("Running bot!");
		addPlayer();
		while(true) {
			move(randomDirection());
			System.out.println(getBoard());
			Thread.sleep(100);
		}
	}

	private void addPlayer() {
		with()
			.param("name", NAME)
			.param("pass", PASS)
			.get(ADD_PLAYER);
	}

	private String getBoard() {
		return with()
				.param("name", NAME)
				.get(BOARD)
				.asString();
	}

	private void move(Direction direction) {
		with()
			.param("name", NAME)
			.param("pass", PASS)
			.param("action", "move")
			.param("target", direction)
		.get(ACT);
	}

	private Direction randomDirection() {
		return Direction.values()[random.nextInt(Direction.values().length)];
	}

	/**
	 * Demonstrates deserialising JSON to Java object
	 */
	private Book getBook(String author, String title) {
		// http://echo.jsontest.com/key/value/one/two
		return 
			with()
				.pathParam("author", author)
				.pathParam("title", title)
			.get("http://echo.jsontest.com/author/{author}/title/{title}")
			.as(Book.class);
	}
	
	/**
	 * Demonstrates JsonPath
	 */
	private String getAuthor(String author, String title) {
		// http://echo.jsontest.com/key/value/one/two
		return 
			with()
				.pathParam("author", author)
				.pathParam("title", title)
			.get("http://echo.jsontest.com/author/{author}/title/{title}")
			.jsonPath()
			.get("author");
	}

}
