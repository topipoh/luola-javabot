package javabot;

import static javabot.TestUtil.loadTextFile;
import static org.junit.Assert.*;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import javabot.api.Action;
import javabot.api.Direction;

public class AITest {
	
	@Test
	public void shouldNotRunIntoWalls() throws Exception {
		final String board = loadTextFile("/case1.txt");
		assertEquals(Pair.of(Action.move, Direction.east), new AI(board).takeAction());
	}
	
	@Test
	public void shouldGoToTreasure() throws Exception {
		final String board = loadTextFile("/case3.txt");
		assertEquals(Pair.of(Action.move, Direction.north), new AI(board).takeAction());
	}
	
	@Test
	public void shouldGoToTreasureAndAvoidMonsters() throws Exception {
		final String board = loadTextFile("/case4.txt");
		assertEquals(Pair.of(Action.move, Direction.west), new AI(board).takeAction());
	}
	
	@Test
	public void shouldNotGoToUnreachableTreasure() throws Exception {
		final String board = loadTextFile("/case5.txt");
		assertEquals(Pair.of(Action.move, Direction.south), new AI(board).takeAction());
	}
	
}
