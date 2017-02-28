package javabot.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.apache.commons.lang3.tuple.Pair;

import javabot.board.Board;
import javabot.board.Location;

/**
 * Dijkstra's Algorithm from tutorial:
 * http://www.redblobgames.com/pathfinding/a-star/introduction.html
 */
public class Dijkstra {
	
	public static Result dijkstra(Board graph, Location start) {
		PriorityQueue<Pair<Location, Integer>> frontier = new PriorityQueue<>((a, b) -> a.getRight() - b.getRight());
		frontier.add(Pair.of(start, 0));

		Map<Location, Location> cameFrom = new HashMap<>();
		cameFrom.put(start, null);
		
		Map<Location, Integer> costSoFar = new HashMap<>();
		costSoFar.put(start, 0);
		
		while(!frontier.isEmpty()) {
			Location current = frontier.poll().getLeft();
			
			for(Location next : graph.neighborsAt(current)) {
				final Integer newCost = costSoFar.get(current) + graph.cost(current, next);
				if(!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {
					costSoFar.put(next, newCost);
					frontier.add(Pair.of(next, newCost));
					cameFrom.put(next, current);
				}
			}
		}
		return new Result(cameFrom, costSoFar);
	}
	
	public static class Result {
		public final Map<Location, Location> cameFrom;
		public final Map<Location, Integer> costSoFar;
		public Result(Map<Location, Location> cameFrom, Map<Location, Integer> costSoFar) {
			this.cameFrom = cameFrom;
			this.costSoFar = costSoFar;
		}
	}

	public static List<Location> reconstructPath(Map<Location, Location> cameFrom, Location start, Location goal) {
		Location current = goal;
		List<Location> path = new ArrayList<>();
		path.add(current);
		while(!current.equals(start)) {
			if(!cameFrom.containsKey(current)) {
				System.out.println("No path to " + current);
				return null;
			}
			current = cameFrom.get(current);
			path.add(current);
		}
		Collections.reverse(path);
		path.remove(0);
		return path;
	}
}
