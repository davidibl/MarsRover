package de.lv1871.dms.MarsRover;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.lv1871.dms.MarsRover.domain.Direction;
import de.lv1871.dms.MarsRover.domain.Rover;

public class AppTest {

	@Test
	public void testMarsRoverSetup() {
		Rover rover = new Rover(1, 1, Direction.NORTH);
		assertEquals(1, rover.getX());
		assertEquals(1, rover.getY());
	}

	@Test
	public void testMoveInTwoDirections() {
		Rover rover = new Rover(1, 1, Direction.NORTH);
		rover.processCommands("ffrff");
		assertEquals(3, rover.getX());
		assertEquals(3, rover.getY());
	}

	@Test
	public void testTurnLeftThanBack() {
		Rover rover = new Rover(1, 1, Direction.NORTH);
		rover.processCommands("lbb");
		assertEquals(3, rover.getX());
		assertEquals(1, rover.getY());
	}
}
