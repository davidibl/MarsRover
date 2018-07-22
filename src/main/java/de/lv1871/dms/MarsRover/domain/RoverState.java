package de.lv1871.dms.MarsRover.domain;

public class RoverState {

	private final int x;
	private final int y;
	private final Direction direction;

	public RoverState(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Direction getDirection() {
		return direction;
	}
}
