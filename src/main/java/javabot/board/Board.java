package javabot.board;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

	private final Tile[][] tiles;
	
	public final int width;
	
	public final int height;
	
	private final List<Tile> tilesList;
	
	private final Map<TileType, List<Tile>> tilesByType;

	private Board(Tile[][] tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
		this.tilesList = tilesToList(tiles);
		this.tilesByType = groupByType(tilesList);
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
	
	public List<Location> neighborsAt(Location location) {
		List<Location> list = new ArrayList<>(4);	
		maybeAdd(location.north(), list);
		maybeAdd(location.south(), list);
		maybeAdd(location.west(), list);
		maybeAdd(location.east(), list);
		return list;
	}

	private void maybeAdd(Location location, List<Location> list) {
		if(!outOfBounds(location)) {
			Tile tile = tileAt(location);
			if(tile.type.isWalkable()) {
				list.add(tile.location);
			}
		}
	}

	public Optional<Location> myLocation() {
		return locationsByType(TileType.ME).stream()
				.findAny();
	}

	public List<Location> locationsByType(TileType type) {
		if(!tilesByType.containsKey(type)) {
			return emptyList();
		}
		return tilesByType.get(type)
				.stream()
				.map(tile -> tile.location)
				.collect(toList());
	}
	
	private Map<TileType, List<Tile>> groupByType(List<Tile> tiles) {
		return tiles.stream().collect(Collectors.groupingBy(tile -> tile.type));
	}
	
	private List<Tile> tilesToList(Tile[][] tiles) {
		List<Tile> list = new ArrayList<>(width * height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				list.add(tiles[x][y]);
			}
		}
		return list;
	}

}
