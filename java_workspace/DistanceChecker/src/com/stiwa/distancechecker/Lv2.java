package com.stiwa.distancechecker;

import java.awt.Point;
import java.util.Vector;

public class Lv2 {

	static class Directions {
		static int UP = 0;
		static int DOWN = 1;
		static int LEFT = 2;
		static int RIGHT = 3;
	}

	private int steps;
	private int inputMoves;
	private Vector<String> movement = new Vector<String>();
	private Vector<Integer> repition = new Vector<Integer>();

	public Lv2(String input) {
		System.out.println();
		process(input);
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
		int length = 0;
		int width = 0;

		Vector<Point> points = new Vector<Point>();

		points.add(new Point(0, 0));

		int x = 0;
		int y = 0;

		int direction = Directions.UP;

		for (int index = 0; index < getMovement().size(); index++) {
			for (int i = 0; i < getMovement().get(index).length(); i++) {
				int move = getMovement().get(index).charAt(i);

				if (direction == Directions.UP) {
					if (move == 'F') {
						y++;
					}
					if (move == 'L') {
						direction = Directions.LEFT;
					}
					if (move == 'R') {
						direction = Directions.RIGHT;
					}
				}

				if (direction == Directions.DOWN) {
					if (move == 'F') {
						y--;
					}
					if (move == 'L') {
						direction = Directions.RIGHT;
					}
					if (move == 'R') {
						direction = Directions.LEFT;
					}
				}

				if (direction == Directions.LEFT) {
					if (move == 'F') {
						x--;
					}
					if (move == 'L') {
						direction = Directions.DOWN;
					}
					if (move == 'R') {
						direction = Directions.UP;
					}
				}

				if (direction == Directions.RIGHT) {
					if (move == 'F') {
						x++;
					}
					if (move == 'L') {
						direction = Directions.UP;
					}
					if (move == 'R') {
						direction = Directions.DOWN;
					}
				}

			}
		}

		System.out.println(x + "|" + y);

//		calcArea(length, width);
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
