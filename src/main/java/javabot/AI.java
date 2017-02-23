package javabot;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import javabot.api.Action;
import javabot.api.Direction;
import javabot.board.Board;

public class AI {
	
	private final Board board;

	public AI(String asciiBoard) {
		this.board = Board.of(asciiBoard);
	}
	
	public Pair<Action, Direction> takeAction() {
		return Pair.of(Action.move, anyValidDirection(board));
	}
	
	private Direction anyValidDirection(Board board) {
		// TODO: myLocation
		board.neighborsAt(board.myLocation());
		return randomDirection();
	}

	private static Direction randomDirection() {
		return Direction.values()[new Random().nextInt(Direction.values().length)];
	}
}
