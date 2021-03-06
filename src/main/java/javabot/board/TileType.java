package javabot.board;

import java.util.stream.Stream;

public enum TileType {
	
	WALL(        '#', false), 
	FLOOR(       '.', true), 
	ME(          '@', false), 
	OTHER_PLAYER('P', false), 
	TREASURE(    '$', true), 
	MONSTER(     'e', false),
	UNKNOWN(     ' ', false),
	SPAWN_POINT( ':', true)
	;
	
	private final char c;
	
	private final boolean walkable;
	
	public static TileType of(char character) {
		return Stream.of(values())
				.filter(type -> type.c == character)
				.findFirst()
				.orElse(UNKNOWN);
	}
	
	TileType(char c, boolean walkable) {
		this.c = c;
		this.walkable = walkable;
	}
	
	public char getChar() {
		return c;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
}
