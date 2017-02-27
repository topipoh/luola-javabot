package javabot;

import static java.util.Comparator.comparing;
import static javabot.pathfinding.Distance.distance;
import static javabot.util.CollectionUtil.any;
import static javabot.util.CollectionUtil.first;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import javabot.api.Action;
import javabot.api.Direction;
import javabot.board.Board;
import javabot.board.Location;
import javabot.board.TileType;
import javabot.pathfinding.BFS;
import javabot.util.CollectionUtil;

public class AI {
	
	private final Board board;

	public AI(String asciiBoard) {
		this.board = Board.of(asciiBoard);
	}
	
	public Pair<Action, Direction> takeAction() {
		Direction direction = move(board);
		return direction != null ? 
				Pair.of(Action.move, direction) :
				Pair.of(Action.attack, Direction.east);
	}

	private Direction move(Board board) {
		Direction direction = moveToTreasure(board);
		if(direction == null) {
			direction = anyValidDirection(board);
		}
		return direction;
	}

	private Direction moveToTreasure(Board board) {
		Location treasure = closestTreasure(board);
		return treasure != null ?
				nextMove(board, board.myLocation().get(), treasure) : null;
	}

	private Location closestTreasure(Board board) {
		return board.locationsByType(TileType.TREASURE).stream()
				.sorted(comparing(treasure -> distance(board.myLocation().get(), treasure)))
				.findFirst()
				.orElse(null);
	}

	private Direction nextMove(Board board, Location start, Location goal) {
		List<Location> path = BFS.findPath(board, start, goal);
		return any(path) ?
			start.stepTowards(first(path)) : null;
	}
	
	private Direction anyValidDirection(Board board) {
		final Location myLocation = board.myLocation().get();
		List<Location> neighbors = board.neighborsAt(myLocation);
		return !neighbors.isEmpty() ? 
				myLocation.stepTowards(CollectionUtil.getRandom(neighbors)) : null;
	}

}
