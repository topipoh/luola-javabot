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
		assertEquals(Pair.of(Action.move, Direction.east), AI.takeAction(board));
	}
	
}
