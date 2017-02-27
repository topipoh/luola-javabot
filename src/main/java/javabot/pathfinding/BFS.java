package javabot.pathfinding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javabot.board.Board;
import javabot.board.Location;

/**
 * Breadth First Search from tutorial:
 * http://www.redblobgames.com/pathfinding/a-star/introduction.html
 */
public class BFS {
	
	public static List<Location> findPath(Board graph, Location start, Location goal) {
		return reconstructPath(bfs(graph, start, goal), start, goal);
	}

	public static Map<Location, Location> bfs(Board graph, Location start, Location goal) {
		Deque<Location> frontier = new ArrayDeque<>();
		frontier.addLast(start);
		Map<Location, Location> cameFrom = new HashMap<>();
		cameFrom.put(start, null);
		
		while(!frontier.isEmpty()) {
			Location current = frontier.removeFirst();
			
			if(current.equals(goal)) {
				break;
			}
			
			for(Location next : graph.neighborsAt(current)) {
				if(!cameFrom.containsKey(next)) {
					frontier.addLast(next);
					cameFrom.put(next, current);
				}
			}
		}
		return cameFrom;
	}

	public static List<Location> reconstructPath(Map<Location, Location> cameFrom, Location start, Location goal) {
		Location current = goal;
		List<Location> path = new ArrayList<>();
		path.add(current);
		while(!current.equals(start)) {
			current = cameFrom.get(current);
			path.add(current);
		}
		Collections.reverse(path);
		path.remove(0);
		return path;
	}
	
	
}
