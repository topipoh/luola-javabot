package javabot;

import javabot.api.API;

public class Bot {

	public static void main(String[] args) throws Exception {
		Bot bot = new Bot();
		bot.run();
	}

	public void run() throws Exception {
		System.out.println("Running bot!");
		API.addPlayer();
		while(true) {
			final String board = API.getBoard();
			API.act(new AI(board).takeAction());
			System.out.println(board);
			Thread.sleep(100);
		}
	}
}
