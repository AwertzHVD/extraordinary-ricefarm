package com.stiwa.distancechecker;

import java.awt.Point;
import java.util.Vector;

public class Lv2 {

	static class Directions {
		static int UP = 0;
		static int RIGHT = 1;
		static int DOWN = 2;
		static int LEFT = 3;
	}

	private int steps;
	private int inputMoves;
	private Vector<String> movement = new Vector<String>();
	private Vector<Integer> repition = new Vector<Integer>();

	public Lv2(String input) {
		process(input);
		System.out.println();
		distanceCheck();
		calcRectArea();
	}

	private void process(String input) {
		String[] data = input.split(" ");
		setInputMoves(Integer.parseInt(data[0]));

		for (int i = 1; i < data.length; i += 2) {
			getMovement().add(data[i]);
			getRepition().add(Integer.parseInt(data[i + 1]));
		}

	}

	private void distanceCheck() {
		int counter = 0;

		for (int index = 0; index < getMovement().size(); index++) {
			int stepsAmount = 0;
			for (int i = 0; i < getMovement().get(index).length(); i++) {
				if (getMovement().get(index).charAt(i) == 'F') {
					stepsAmount++;
				}

			}
			int repeat = getRepition().get(index);
			counter += stepsAmount * repeat;
		}
		setSteps(counter);
	}

	private void calcRectArea() {

		String fullSteps = "";
		for (int i = 0; i < getMovement().size(); i++) {
			for (int j = 0; j < getRepition().get(i); j++) {
				fullSteps += getMovement().get(i);
			}
		}

		int length = 0;
		int width = 0;

		Vector<Integer> xVal = new Vector<Integer>();
		Vector<Integer> yVal = new Vector<Integer>();

		int direction = Directions.UP;
		int x = 0;
		int y = 0;

		for (int index = 0; index < fullSteps.length(); index++) {
			char move = fullSteps.charAt(index);

			if (move == 'F') {
				if (direction == Directions.UP) {
					y++;
				}
				if (direction == Directions.RIGHT) {
					x++;
				}
				if (direction == Directions.DOWN) {
					y--;
				}
				if (direction == Directions.LEFT) {
					x--;
				}
			} else if (move == 'R') {
				if (direction == Directions.UP) {
					direction = Directions.RIGHT;
				} else if (direction == Directions.RIGHT) {
					direction = Directions.DOWN;
				} else if (direction == Directions.DOWN) {
					direction = Directions.LEFT;
				} else if (direction == Directions.LEFT) {
					direction = Directions.UP;
				}
			} else if (move == 'L') {
				if (direction == Directions.UP) {
					direction = Directions.LEFT;
				} else if (direction == Directions.LEFT) {
					direction = Directions.DOWN;
				} else if (direction == Directions.DOWN) {
					direction = Directions.RIGHT;
				} else if (direction == Directions.RIGHT) {
					direction = Directions.UP;
				}
			}
			xVal.add(x);
			yVal.add(y);

		}

		xVal.sort(null);
		yVal.sort(null);

		length = xVal.get(xVal.size() - 1)-xVal.get(0);
		width = yVal.get(yVal.size() - 1)-yVal.get(0);

		calcArea(length, width);
	}

	private void calcArea(int length, int width) {
		System.out.println(getSteps() + " " + (length * width));
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getInputMoves() {
		return inputMoves;
	}

	public void setInputMoves(int inputMoves) {
		this.inputMoves = inputMoves;
	}

	public Vector<String> getMovement() {
		return movement;
	}

	public void setMovement(Vector<String> movement) {
		this.movement = movement;
	}

	public Vector<Integer> getRepition() {
		return repition;
	}

	public void setRepition(Vector<Integer> repition) {
		this.repition = repition;
	}

}
