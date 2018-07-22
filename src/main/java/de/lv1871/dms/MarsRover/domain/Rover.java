package de.lv1871.dms.MarsRover.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Rover {

	private static HashMap<Direction, BiFunction<RoverState, Integer, RoverState>> moveVectorMap = new HashMap<>();
	static {
		moveVectorMap.put(Direction.NORTH, Rover::moveUpOnYAxis);
		moveVectorMap.put(Direction.SOUTH, Rover::moveDownOnYAxis);
		moveVectorMap.put(Direction.EAST, Rover::moveRightOnXAxis);
		moveVectorMap.put(Direction.WEST, Rover::moveLeftOnXAxis);
	}

	private static HashMap<Command, ExtendedFunction<BiFunction<RoverState, Integer, RoverState>, RoverState, RoverState>> implementationMap = new HashMap<>();
	static {
		implementationMap.put(Command.FORWARD, (moveVector, state) -> moveVector.apply(state, 1));
		implementationMap.put(Command.BACKWARD, (moveVector, state) -> moveVector.apply(state, -1));
		implementationMap.put(Command.TURN_RIGHT, (moveVector, state) -> Rover.turn(state, Direction::getToRight));
		implementationMap.put(Command.TURN_LEFT, (moveVector, state) -> Rover.turn(state, Direction::getToLeft));
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
			.map(Rover.implementationMap::get)
			.map(Rover::curryWithMoveVector)
			.reduce(Function.identity(), Function::andThen)
			.apply(state);
		// @formatter:on

	}

	public static RoverState getMoveVector(RoverState state, Integer step) {
		return Rover.moveVectorMap.get(state.getDirection()).apply(state, step);
	}

	private static RoverState moveUpOnYAxis(RoverState state, Integer step) {
		return Rover.moveOnYAxis(state, step, initialStep -> initialStep);
	}

	private static RoverState turn(RoverState state, Function<Direction, Direction> func) {
		return new RoverState(state.getX(), state.getY(), func.apply(state.getDirection()));
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

	private static Function<RoverState, RoverState> curryWithMoveVector(
			ExtendedFunction<BiFunction<RoverState, Integer, RoverState>, RoverState, RoverState> implementation) {
		return implementation.curryWith(Rover::getMoveVector);
	}

}
