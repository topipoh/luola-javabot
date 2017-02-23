package javabot.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

	private final Tile[][] tiles;
	
	public final int width;
	
	public final int height;

	private Board(Tile[][] tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}

	public static Board of(String asciiBoard) {
		final String[] lines = asciiBoard.split("\n");
		final int width = Stream.of(lines).mapToInt(String::length).max().orElse(0);
		final int height = lines.length;
		final Tile[][] tileArray = new Tile[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				char c = lines[y].length() > x ? lines[y].charAt(x) : ' ';
				tileArray[x][y] = new Tile(x, y, TileType.of(c));
			}
		}

		return new Board(tileArray, width, height);
	}

	public boolean outOfBounds(Location location) {
		int x = location.x;
		int y = location.y;
		return outOfBounds(x, y);
	}

	public boolean outOfBounds(int x, int y) {
		return x < 0 || x > width - 1 || y < 0 || y > height - 1;
	}
	
	public boolean withinBounds(Location l) {
		return !outOfBounds(l);
	}
	
	public Tile tileAt(Location location) {
		int x = location.x;
		int y = location.y;
		return tileAt(x, y);
	}

	private Tile tileAt(int x, int y) {
		return tiles[x][y];
	}
	
	public String printBoard() {
		StringBuilder s = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				s.append(tileAt(x, y).type.getChar());
			}
			s.append("\n");
		}
		return s.toString();
	}

	@Override
	public String toString() {
		return "Board [width=" + width + ", height=" + height + "]";
	}
	

	// This was 5 times faster than neighborsAt2
	public List<Tile> neighborsAt(Location location) {
		List<Tile> list = new ArrayList<>(4);	
		maybeAdd(location.north(), list);
		maybeAdd(location.south(), list);
		maybeAdd(location.west(), list);
		maybeAdd(location.east(), list);
		return list;
	}

	private void maybeAdd(Location location, List<Tile> list) {
		if(!outOfBounds(location)) {
			Tile tile = tileAt(location);
			if(tile.type.isWalkable()) {
				list.add(tile);
			}
		}
	}
	
	public List<Tile> neighborsAt2(Location target) {	
		return Stream.of(target.north(), target.south(), target.east(), target.west())
			.filter(location -> withinBounds(location))
			.map(location -> tileAt(location))
			.filter(tile -> tile.type.isWalkable())
			.collect(Collectors.toList());
	}

	public Location myLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
