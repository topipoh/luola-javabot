package javabot;

import java.util.Random;

import javabot.api.Api;
import javabot.api.Direction;

public class Bot {
		
	private Random random = new Random();

	public static void main(String[] args) throws Exception {
		Bot bot = new Bot();
		bot.run();
	}

	public void run() throws Exception {
		System.out.println("Running bot!");
		Api.addPlayer();
		while(true) {
			Api.move(randomDirection());
			System.out.println(Api.getBoard());
			Thread.sleep(100);
		}
	}

	private Direction randomDirection() {
		return Direction.values()[random.nextInt(Direction.values().length)];
	}
}
