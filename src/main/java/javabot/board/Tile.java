package javabot.board;

public class Tile {
	public final Location location;
	public final TileType type;

	public Tile(int x, int y, TileType type) {
		this.location = new Location(x, y);
		this.type = type;
	}

	public Tile(Location location, TileType type) {
		this.location = location;
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tile [location=" + location + ", type=" + type + "]";
	}
}
