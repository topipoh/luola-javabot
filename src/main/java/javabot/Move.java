package javabot;

import javabot.api.Action;
import javabot.api.Direction;

public class Move {
	
	public final Action action;
	public final Direction direction;
	
	public Move(Action action, Direction direction) {
		this.action = action;
		this.direction = direction;
	}
}
