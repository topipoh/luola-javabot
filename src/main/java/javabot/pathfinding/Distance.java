package javabot.pathfinding;

import static java.lang.Math.abs;

import javabot.board.Location;

public class Distance {
	public static int distance(Location a, Location b) {
		return abs(a.x - b.x) + abs(a.y - b.y);
	}

}
