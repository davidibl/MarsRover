package de.lv1871.dms.MarsRover.domain;

import java.util.Arrays;

public enum Command {

	// @formatter:off
	FORWARD("f"),
	BACKWARD("b"),
	TURN_RIGHT("r"),
	TURN_LEFT("l");
	// @formatter:on

	private final String sourceChar;

	private Command(String sourceChar) {
		this.sourceChar = sourceChar;
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

}
