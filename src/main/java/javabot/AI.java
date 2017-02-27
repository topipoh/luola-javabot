package javabot;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import javabot.api.Action;
import javabot.api.Direction;
import javabot.board.Board;
import javabot.board.Location;
import javabot.util.CollectionUtil;

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
		List<Location> neighbors = board.neighborsAt(myLocation);
		return !neighbors.isEmpty() ? 
				myLocation.stepTowards(CollectionUtil.getRandom(neighbors)) : Direction.west;
	}

}
