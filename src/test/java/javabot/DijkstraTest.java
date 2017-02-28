package javabot;

import static javabot.TestUtil.loadTextFile;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import javabot.board.Board;
import javabot.board.Location;
import javabot.pathfinding.Dijkstra;
import javabot.pathfinding.Dijkstra.Result;

public class DijkstraTest {
	@Test
	public void shouldGoToTreasureAndAvoidMonsters() throws Exception {
		Board board = Board.of(loadTextFile("/case4.txt"));
		Result result = Dijkstra.dijkstra(board, board.myLocation().get());
		final Location treasureLeft = new Location(6, 4);
		final Location treasureRight = new Location(19, 4);
		
		assertTrue(result.costSoFar.get(treasureLeft) < result.costSoFar.get(treasureRight));
		
		List<Location> pathToRight = Dijkstra.reconstructPath(result.cameFrom, board.myLocation().get(), treasureRight);
		assertTrue(pathToRight.size() > 7);
		
		List<Location> pathToLeft = Dijkstra.reconstructPath(result.cameFrom, board.myLocation().get(), treasureLeft);
		assertEquals(8, pathToLeft.size());
	}
	
	@Test
	public void testNoPath() throws Exception {
		Board board = Board.of(loadTextFile("/case5.txt"));
		Result result = Dijkstra.dijkstra(board, board.myLocation().get());
		final Location treasure = new Location(6, 4);
		assertNull(result.costSoFar.get(treasure));
		List<Location> pathToLeft = Dijkstra.reconstructPath(result.cameFrom, board.myLocation().get(), treasure);
		assertNull(pathToLeft);
	}
}
