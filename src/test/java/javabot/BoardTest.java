package javabot;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

public class BoardTest {
	
	private Random random = new Random();
	
	@Test
	public void testTiles() throws Exception {
		Board board = Board.of(loadTextFile("/case2.txt"));
		System.out.println(board.printBoard());
		assertEquals(new Tile(0, 0, TileType.WALL), board.tileAt(location(0, 0)));
		assertEquals(new Tile(9, 8, TileType.ME), board.tileAt(location(9, 8)));
		assertEquals(new Tile(3, 1, TileType.FLOOR), board.tileAt(location(3, 1)));
	}

	private static Location location(int x, int y) {
		return new Location(x, y);
	}
	
	@Test
	public void testWidthHeight() throws Exception {
		Board board = Board.of(loadTextFile("/case2.txt"));
		assertEquals(38, board.width);
		assertEquals(19, board.height);
	}
	
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testOutOfBoundsNegativeX() throws Exception {
		Board.of(loadTextFile("/case2.txt")).tileAt(location(-1, 0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testOutOfBoundsNegativeY() throws Exception {
		Board.of(loadTextFile("/case2.txt")).tileAt(location(0, -1));
	}
	
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testOutOfBoundsWidth() throws Exception {
		Board board = Board.of(loadTextFile("/case2.txt"));
		board.tileAt(location(board.width, 0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class) 
	public void testOutOfBoundsHeight() throws Exception {
		Board board = Board.of(loadTextFile("/case2.txt"));
		board.tileAt(location(0, board.height));
	}
	
	@Test
	public void testNeighbors() throws Exception {
		Board board = Board.of(loadTextFile("/case2.txt"));
		// ignore order
		assertEquals(
				setOf(new Tile(2, 1, TileType.FLOOR), new Tile(1, 2, TileType.FLOOR)), 
				setOf(board.neighborsAt(location(1, 1))));	
	}
	
	@Test
	public void neighborsPerformanceTest() throws Exception {
		Board board = Board.of(loadTextFile("/case2.txt"));
		for (int i = 0; i < 1000000; i++) {
			board.neighborsAt(new Location(random.nextInt(board.width), random.nextInt(board.height)));
		}
	}
	
	@SafeVarargs
	private static <T> HashSet<T> setOf(T ... array) {
		return setOf(asList(array));
	}

	private static <T> HashSet<T> setOf(List<T> list) {
		return new HashSet<>(list);
	}

	private String loadTextFile(String name) throws Exception {
		try(Scanner scanner = new Scanner(getClass().getResourceAsStream(name))) {
			return scanner.useDelimiter("\\A").next();
		}
	}
}
