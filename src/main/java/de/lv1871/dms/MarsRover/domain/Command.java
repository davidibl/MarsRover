package de.lv1871.dms.MarsRover.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum Command {

	// @formatter:off
	FORWARD("f", (state) -> state.getDirection().getVector().apply(state, 1)),
	BACKWARD("b", (state) -> state.getDirection().getVector().apply(state, -1)),
	TURN_RIGHT("r", (state) -> new RoverState(state.getX(), state.getY(), state.getDirection().getNeigbours()[0])),
	TURN_LEFT("l", (state) -> new RoverState(state.getX(), state.getY(), state.getDirection().getNeigbours()[1]));
	// @formatter:on

	private final String sourceChar;
	private final Function<RoverState, RoverState> func;

	private Command(String sourceChar, Function<RoverState, RoverState> func) {
		this.sourceChar = sourceChar;
		this.func = func;
	}

	public static Command fromChar(String sourceChar) {
		// @formatter:off
		return Arrays.stream(Command.values())
				.filter(cmd -> cmd.getSourceChar().equalsIgnoreCase(sourceChar))
				.findFirst()
				.orElse(null);
		// @formatter:on
	}

	public String getSourceChar() {
		return sourceChar;
	}

	public Function<RoverState, RoverState> getFunc() {
		return func;
	}

}
