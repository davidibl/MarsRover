package de.lv1871.dms.MarsRover.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Command {

	// @formatter:off
	FORWARD("f", (moveVector, state) -> moveVector.apply(state, 1)),
	BACKWARD("b", (moveVector, state) -> moveVector.apply(state, -1)),
	TURN_RIGHT("r", (moveVector, state) -> new RoverState(state.getX(), state.getY(), state.getDirection().getToRight())),
	TURN_LEFT("l", (moveVector, state) -> new RoverState(state.getX(), state.getY(), state.getDirection().getToLeft()));
	// @formatter:on

	private final String sourceChar;
	private final ExtendedFunction<BiFunction<RoverState, Integer, RoverState>, RoverState, RoverState> func;

	private Command(String sourceChar,
			ExtendedFunction<BiFunction<RoverState, Integer, RoverState>, RoverState, RoverState> func) {
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

	public ExtendedFunction<BiFunction<RoverState, Integer, RoverState>, RoverState, RoverState> getFunc() {
		return func;
	}

}
