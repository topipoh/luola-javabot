package javabot;

import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import javabot.api.Action;
import javabot.api.Direction;
import javabot.board.Board;
import javabot.board.Location;

public class AI {
	
	private final Board board;

	public AI(String asciiBoard) {
		this.board = Board.of(asciiBoard);
	}
	
	public Pair<Action, Direction> takeAction() {
		return Pair.of(Action.move, anyValidDirection(board));
	}
	
	private Direction anyValidDirection(Board board) {
		final Location myLocation = board.myLocation().get();
		Optional<Location> neighbor = board.neighborsAt(myLocation)
				.stream().findAny();
		return neighbor.isPresent() ? myLocation.stepTowards(neighbor.get()) : randomDirection();
	}

	private static Direction randomDirection() {
		return Direction.values()[new Random().nextInt(Direction.values().length)];
	}
}
