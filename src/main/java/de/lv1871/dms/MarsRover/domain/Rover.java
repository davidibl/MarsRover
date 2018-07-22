package de.lv1871.dms.MarsRover.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Rover {

	private static HashMap<Direction, BiFunction<RoverState, Integer, RoverState>> moveVectorMap = new HashMap<>();
	static {
		moveVectorMap.put(Direction.NORTH, Rover::moveDownUpOnYAxis);
		moveVectorMap.put(Direction.SOUTH, Rover::moveDownOnYAxis);
		moveVectorMap.put(Direction.EAST, Rover::moveRightOnXAxis);
		moveVectorMap.put(Direction.WEST, Rover::moveLeftOnXAxis);
	}

	private RoverState state;

	public Rover(int x, int y, Direction direction) {
		this.state = new RoverState(x, y, direction);
	}

	public int getX() {
		return state.getX();
	}

	public int getY() {
		return state.getY();
	}

	public void processCommands(String commands) {

		// @formatter:off
		this.state = Arrays
			.stream(commands.split(""))
			.map(Command::fromChar)
			.map(Command::getFunc)
			.map(func -> func.curryWith(Rover::getMoveVector))
			.reduce(Function.identity(), Function::andThen)
			.apply(state);
		// @formatter:on

	}

	public static RoverState getMoveVector(RoverState state, Integer step) {
		return Rover.moveVectorMap.get(state.getDirection()).apply(state, step);
	}

	private static RoverState moveDownUpOnYAxis(RoverState state, Integer step) {
		return Rover.moveOnYAxis(state, step, initialStep -> initialStep);
	}

	private static RoverState moveDownOnYAxis(RoverState state, Integer step) {
		return Rover.moveOnYAxis(state, step, Rover::inverseDirection);
	}

	private static RoverState moveRightOnXAxis(RoverState state, Integer step) {
		return Rover.moveOnXAxis(state, step, initialStep -> initialStep);
	}

	private static RoverState moveLeftOnXAxis(RoverState state, Integer step) {
		return Rover.moveOnXAxis(state, step, Rover::inverseDirection);
	}

	private static RoverState moveOnYAxis(RoverState state, Integer step, Function<Integer, Integer> transformer) {
		return new RoverState(state.getX(), state.getY() + transformer.apply(step), state.getDirection());
	}

	private static RoverState moveOnXAxis(RoverState state, Integer step, Function<Integer, Integer> transformer) {
		return new RoverState(state.getX() + transformer.apply(step), state.getY(), state.getDirection());
	}

	private static Integer inverseDirection(Integer step) {
		return step * -1;
	}

}
