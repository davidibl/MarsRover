package de.lv1871.dms.MarsRover.domain;

public enum Direction {

	// @formatter:off
	NORTH("WEST", "EAST"),
	EAST("NORTH", "SOUTH"),
	SOUTH("EAST", "WEST"),
	WEST("SOUTH", "NORTH");
	// @formatter:on

	private final String toLeft;
	private final String toRight;

	private Direction(String toLeft, String toRight) {
		this.toLeft = toLeft;
		this.toRight = toRight;
	}

	public Direction getToLeft() {
		return Direction.valueOf(toLeft);
	}

	public Direction getToRight() {
		return Direction.valueOf(toRight);
	}
}
