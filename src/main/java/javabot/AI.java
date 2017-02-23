package javabot;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import javabot.api.Action;
import javabot.api.Direction;

public class AI {
	
	public static Pair<Action, Direction> takeAction(String board) {
		return Pair.of(Action.move, randomDirection());
	}
	
	private static Direction randomDirection() {
		return Direction.values()[new Random().nextInt(Direction.values().length)];
	}

}
