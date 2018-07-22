package de.lv1871.dms.MarsRover.domain;

import java.util.HashMap;
import java.util.function.BiFunction;

public enum Direction {

	// @formatter:off
	NORTH((state, value) -> new RoverState(state.getX(), state.getY() + value, state.getDirection())),
	EAST((state, value) -> new RoverState(state.getX() + value, state.getY(), state.getDirection())),
	SOUTH((state, value) -> new RoverState(state.getX(), state.getY() - value, state.getDirection())),
	WEST((state, value) -> new RoverState(state.getX() - value, state.getY(), state.getDirection()));
	// @formatter:on

	private BiFunction<RoverState, Integer, RoverState> vector;
	private static HashMap<Direction, Direction[]> directionsMap = new HashMap<>();
	static {
		directionsMap.put(Direction.NORTH, new Direction[] { Direction.EAST, Direction.WEST });
		directionsMap.put(Direction.EAST, new Direction[] { Direction.SOUTH, Direction.NORTH });
		directionsMap.put(Direction.SOUTH, new Direction[] { Direction.WEST, Direction.EAST });
		directionsMap.put(Direction.WEST, new Direction[] { Direction.NORTH, Direction.SOUTH });
	}

	private Direction(BiFunction<RoverState, Integer, RoverState> vector) {
		this.vector = vector;
	}

	public BiFunction<RoverState, Integer, RoverState> getVector() {
		return vector;
	}

	public Direction[] getNeigbours() {
		return directionsMap.get(this);
	}
}
