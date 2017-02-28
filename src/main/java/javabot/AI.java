package javabot;

import static javabot.pathfinding.Dijkstra.dijkstra;
import static javabot.pathfinding.Dijkstra.reconstructPath;
import static javabot.util.CollectionUtil.first;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import javabot.api.Action;
import javabot.api.Direction;
import javabot.board.Board;
import javabot.board.Location;
import javabot.board.TileType;
import javabot.pathfinding.Dijkstra.Result;

public class AI {
	
	private final Board board;

	public AI(String asciiBoard) {
		this.board = Board.of(asciiBoard);
	}
	
	public Pair<Action, Direction> takeAction() {
		Direction direction = moveToTreasure(board);
		return direction != null ? 
				Pair.of(Action.move, direction) :
				Pair.of(Action.attack, Direction.east);
	}

	private Direction moveToTreasure(Board board) {
		Result result = dijkstra(board, board.myLocation().get());
		Direction direction = moveToTreasure(board, result);
		return direction != null ? direction : moveAwayFromMonsters(board, result);
	}
	
	private Direction moveToTreasure(Board board, Result result) {
		Location goal = lowestCost(result, board.locationsByType(TileType.TREASURE));
		return moveTo(board, result, goal);
	}

	private Direction moveAwayFromMonsters(Board board, Result result) {
		Location goal = lowestCost(result, board.neighborsAt(board.myLocation().get()));
		return moveTo(board, result, goal);
	}

	private Direction moveTo(Board board, Result result, Location goal) {
		if(goal == null) {
			return null;
		}
		List<Location> path = reconstructPath(result.cameFrom, board.myLocation().get(), goal);
		return stepTowards(board, first(path));
	}

	private Location lowestCost(Result result, List<Location> locations) {
		return locations.stream()
			.filter(t -> result.costSoFar.containsKey(t)) // goal is reachable
			.sorted((a, b) -> result.costSoFar.get(a) - result.costSoFar.get(b)) // sort by cost
			.findFirst()
			.orElse(null);
	}

	private Direction stepTowards(Board board, Location goal) {
		return board.myLocation().get().stepTowards(goal);
	}
}
