package com.stiwa.distancechecker;

import java.awt.Point;
import java.util.Collections;
import java.util.Vector;

public class Lv5 {
	static class Directions {
		static int UP = 0;
		static int RIGHT = 1;
		static int DOWN = 2;
		static int LEFT = 3;
	}

	private int steps;
	private int area;
	private int areaFigure;
	private int inputMoves;
	private int pockets = 0;
	private Vector<String> movement = new Vector<String>();
	private Vector<Integer> repition = new Vector<Integer>();
	private int rectLength;
	private int rectWidth;

	public Lv5(String input) {
		process(input);
		distanceCheck();
		calcRectArea();
		System.out.println(getSteps() + " " + getArea() + " " + getAreaFigure() + " " + getPockets());
		System.out.println();
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

	private String getAllSteps() {
		String fullSteps = "";
		for (int i = 0; i < getMovement().size(); i++) {
			for (int j = 0; j < getRepition().get(i); j++) {
				fullSteps += getMovement().get(i);
			}
		}
		return fullSteps;
	}

	private void calcRectArea() {
		String fullSteps = getAllSteps();

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
				} else if (direction == Directions.RIGHT) {
					x++;
				} else if (direction == Directions.DOWN) {
					y--;
				} else if (direction == Directions.LEFT) {
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
		setPoints();

		xVal.sort(null);
		yVal.sort(null);

		setRectLength(xVal.get(xVal.size() - 1) - xVal.get(0));
		setRectWidth(yVal.get(yVal.size() - 1) - yVal.get(0));
		setArea(getRectLength() * getRectWidth());
	}

	private void setPoints() {
		int x = 0;
		int y = 0;

		String fullSteps = getAllSteps();

		Vector<Integer> xVal = new Vector<Integer>();
		Vector<Integer> yVal = new Vector<Integer>();

		int direction = Directions.UP;

		xVal.add(x);
		yVal.add(y);
		for (int index = 0; index < fullSteps.length(); index++) {
			char move = fullSteps.charAt(index);

			if (move == 'F') {
				if (direction == Directions.UP) {
					y++;
				} else if (direction == Directions.RIGHT) {
					x++;
				} else if (direction == Directions.DOWN) {
					y--;
				} else if (direction == Directions.LEFT) {
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

		createPointsTemp(xVal, yVal);

		setAreaFigure(calcArea(xVal, yVal));
		calcPockets(xVal, yVal);

	}

	private void createPointsTemp(Vector<Integer> xVal, Vector<Integer> yVal) {
		Vector<Point> points = new Vector<Point>();
		for (int i = 0; i < xVal.size(); i++) {
			points.add(new Point(xVal.get(i), yVal.get(i)));
		}
	}

	public int calcArea(Vector<Integer> xVal, Vector<Integer> yVal) {
		int area = 0;
		for (int index = 0; index < xVal.size(); index++) {
			if (index < xVal.size() - 1) {
				area += (xVal.get(index) * yVal.get(index + 1)) - (xVal.get(index + 1) * yVal.get(index));
			} else {
				area += (xVal.get(index) * yVal.get(0)) - (xVal.get(0) * yVal.get(index));
			}
		}
		return Math.abs(area / 2);
	}

	private void calcPockets(Vector<Integer> xVal, Vector<Integer> yVal) {
		Vector<Point> xMinMax = calcXMinMax(xVal, yVal);
		Vector<Point> yMinMax = calcYMinMax(xVal, yVal);
		Vector<Point> points = new Vector<Point>();

		int areaForX = 0;
		for (int index = 0; index < xMinMax.size() - 2; index += 2) {
			int distanceCurrent = Math.abs(xMinMax.get(index + 1).y - xMinMax.get(index).y);
			int distanceNext = Math.abs(xMinMax.get(index + 3).y - xMinMax.get(index + 2).y);
			int distanceToAdd = 0;
			if (distanceCurrent == distanceNext) {
				distanceToAdd = distanceCurrent;
			} else if (distanceCurrent <= distanceNext) {
				distanceToAdd = distanceCurrent;
			} else if (distanceCurrent >= distanceNext) {
				distanceToAdd = distanceNext;
			}
			for (int i = 0; i < distanceToAdd; i++) {
				points.add(new Point(xMinMax.get(index).x, (xMinMax.get(index).y + i)));
			}
			areaForX += distanceToAdd;
		}
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).getLocation());
		}
		System.out.println("-----");
		int areaForY = 0;
		int counter = 0;
		for (int index = 0; index < yMinMax.size() - 2; index += 2) {
			int distanceCurrent = Math.abs(yMinMax.get(index + 1).x - yMinMax.get(index).x);
			int distanceNext = Math.abs(yMinMax.get(index + 3).x - yMinMax.get(index + 2).x);
			int distanceToAdd = 0;
			if (distanceCurrent == distanceNext) {
				distanceToAdd = distanceCurrent;
			} else if (distanceCurrent <= distanceNext) {
				distanceToAdd = distanceCurrent;
			} else if (distanceCurrent >= distanceNext) {
				distanceToAdd = distanceNext;
			}
			for (int i = 0; i < distanceToAdd; i++) {
				Point temp = null;
				try {
					temp = new Point(yMinMax.get(index).x+i, yMinMax.get(index).y);
					System.out.println(temp.getLocation());
					if (!points.contains(temp)) {
						counter++;
					}
				} catch (Exception e) {
					System.out.println("landed here");
				}
			}
			areaForY += distanceToAdd;
		}

		int horizontal = Math.abs(areaForX - getAreaFigure());
		int vertical = Math.abs(areaForY - getAreaFigure());

		System.out.println("vertical:\t" + vertical + "\tarea: " + areaForY);
		System.out.println("horizontal:\t" + horizontal + "\tarea: " + areaForX);
		System.out.println("both:\t\t" + (vertical + horizontal));
		System.out.println("counter:\t" + counter);

		setPockets(horizontal + vertical - counter);
	}

	private Vector<Point> calcYMinMax(Vector<Integer> xVal, Vector<Integer> yVal) {
		Vector<Integer> everyYPos = new Vector<Integer>();
		for (int i = 0; i < yVal.size(); i++) {
			if (!everyYPos.contains(yVal.get(i))) {
				everyYPos.add(yVal.get(i));
			}
		}
		everyYPos.sort(null);
		Vector<Vector<Integer>> xValVec = new Vector<Vector<Integer>>();
		for (int i = 0; i < everyYPos.size(); i++) {
			Vector<Integer> xValOfY = new Vector<Integer>();
			for (int j = 0; j < xVal.size(); j++) {
				if (yVal.get(j) == everyYPos.get(i)) {
					xValOfY.add(xVal.get(j));
				}
			}
			xValVec.add(xValOfY);
		}

		Vector<Point> yMinMax = new Vector<Point>();
		for (int i = 0; i < everyYPos.size(); i++) {
			int min = Collections.min(xValVec.get(i));
			int max = Collections.max(xValVec.get(i));
			yMinMax.add(new Point(min, everyYPos.get(i)));
			yMinMax.add(new Point(max, everyYPos.get(i)));
		}
		return yMinMax;
	}

	private Vector<Point> calcXMinMax(Vector<Integer> xVal, Vector<Integer> yVal) {
		Vector<Integer> everyXPos = new Vector<Integer>();
		for (int i = 0; i < xVal.size(); i++) {
			if (!everyXPos.contains(xVal.get(i))) {
				everyXPos.add(xVal.get(i));
			}
		}
		everyXPos.sort(null);
		Vector<Vector<Integer>> yValVec = new Vector<Vector<Integer>>();
		for (int i = 0; i < everyXPos.size(); i++) {
			Vector<Integer> yValOfX = new Vector<Integer>();
			for (int j = 0; j < yVal.size(); j++) {
				if (xVal.get(j) == everyXPos.get(i)) {
					yValOfX.add(yVal.get(j));
				}
			}
			yValVec.add(yValOfX);
		}

		Vector<Point> xMinMax = new Vector<Point>();
		for (int i = 0; i < everyXPos.size(); i++) {
			int min = Collections.min(yValVec.get(i));
			int max = Collections.max(yValVec.get(i));
			xMinMax.add(new Point(everyXPos.get(i), min));
			xMinMax.add(new Point(everyXPos.get(i), max));
		}

		return xMinMax;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getAreaFigure() {
		return areaFigure;
	}

	public void setAreaFigure(int areaFigure) {
		this.areaFigure = areaFigure;
	}

	public int getInputMoves() {
		return inputMoves;
	}

	public void setInputMoves(int inputMoves) {
		this.inputMoves = inputMoves;
	}

	public int getPockets() {
		return pockets;
	}

	public void setPockets(int pockets) {
		this.pockets = pockets;
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

	public int getRectLength() {
		return rectLength;
	}

	public void setRectLength(int rectLength) {
		this.rectLength = rectLength;
	}

	public int getRectWidth() {
		return rectWidth;
	}

	public void setRectWidth(int rectWidth) {
		this.rectWidth = rectWidth;
	}

}
