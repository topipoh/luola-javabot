package javabot;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import javabot.api.API;
import javabot.api.Action;
import javabot.api.Direction;

public class Bot {
	
	public static final String NAME = "SMULTR0N";
	public static final String PASS = "SMULTR0N DESTROY!!1";
	
	private final String name;
	private final String pass;
	
	public Bot(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	
	public static void main(String[] args) throws Exception {
		for(Pair<String, String> pair : getBots(args)) {
			Bot bot = new Bot(pair.getLeft(), pair.getRight());
			new Thread(() -> bot.run()).start();
		}
	}
	
	public void run() {
		System.out.println("Running bot " + name);
		API.addPlayer(name, pass);
		while(true) {
			Pair<Action, Direction> action = new AI(API.getBoard(name)).takeAction();
			System.out.println(name + ": " + action);
			API.act(name, pass, action);
			sleep(100);
		}
	}

	private static List<Pair<String, String>> getBots(String[] args) {
		Integer bots = parseBotsCount(args);
		if(bots == null) {
			return asList(Pair.of(NAME, PASS));
		} else {
			return IntStream.rangeClosed(1, bots)
					.mapToObj(i -> (Integer) i)
					.map(i -> Pair.of("javabot-" + i, "javabot-" + i))
					.collect(toList());
		}
	}

	private static Integer parseBotsCount(String[] args) {
		if(args.length == 1) {
			try {
				return Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
