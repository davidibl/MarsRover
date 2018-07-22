package de.lv1871.dms.MarsRover.domain;

import java.util.Arrays;
import java.util.function.Function;

public class Rover {

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
			.reduce(Function.identity(), Function::andThen)
			.apply(state);
		// @formatter:on

	}
}
