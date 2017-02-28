package javabot;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
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
			new Thread(new Runnable() {
				@Override
				public void run() {
					Bot bot = new Bot(pair.getLeft(), pair.getRight());
					bot.run();
				}
			}).start();
		}
	}
	
	public void run() {
		System.out.println("Running bot " + name);
		API.addPlayer(name, pass);
		while(true) {
			final String board = API.getBoard(name);
			Pair<Action, Direction> action = new AI(board).takeAction();
			System.out.println(name + ": " + action);
			API.act(name, pass, action);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static List<Pair<String, String>> getBots(String[] args) {
		Integer bots = parseBotsCount(args);
		if(bots == null) {
			return Arrays.asList(Pair.of(NAME, PASS));
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

}
