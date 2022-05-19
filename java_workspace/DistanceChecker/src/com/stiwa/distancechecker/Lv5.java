package com.stiwa.distancechecker;

import java.awt.Point;
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
		System.out.println();
		System.out.println(getSteps() + " " + getArea() + " " + getAreaFigure() + " " + getPockets());
	}

	private void calcPockets() {
		int temp = 0;
		setPockets(Math.abs(temp));
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

		String fullSteps = getAllSteps();

		int rectLength = 0;
		int rectWidth = 0;

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

		xVal.sort(null);
		yVal.sort(null);

		setRectLength(xVal.get(xVal.size() - 1) - xVal.get(0));
		setRectWidth(yVal.get(yVal.size() - 1) - yVal.get(0));
		setArea(getRectLength() * getRectWidth());
		setPoints(xVal.get(0), yVal.get(0));
	}

	private void setPoints(int xLow, int yLow) {

		String fullSteps = getAllSteps();

		Vector<Integer> xVal = new Vector<Integer>();
		Vector<Integer> yVal = new Vector<Integer>();

		int direction = Directions.UP;
		int x = Math.abs(xLow);
		int y = Math.abs(yLow);

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
				xVal.add(x);
				yVal.add(y);

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
		}

		setAreaFigure(calcArea(xVal, yVal));
		noname(xVal, yVal);

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

	private void noname(Vector<Integer> xVal, Vector<Integer> yVal) {
		Vector<Point> coords = new Vector<Point>();

		for (int posX = 0; posX < xVal.size(); posX++) {
			for (int index = 0; index < xVal.size(); index++) {
				if (xVal.get(index) == posX) {
					if (xVal.get(index) >= 0 && yVal.get(index) >= 0) {
						coords.add(new Point(xVal.get(index), yVal.get(index)));
					}
					if (xVal.get(index) < 0 && yVal.get(index) >= 0) {
						coords.add(new Point(xVal.get(index), yVal.get(index)));
					}
					if (xVal.get(index) < 0 && yVal.get(index) < 0) {
						coords.add(new Point(xVal.get(index), yVal.get(index)));
					}
					if (xVal.get(index) >= 0 && yVal.get(index) < 0) {
						coords.add(new Point(xVal.get(index), yVal.get(index)));
					}
				}
			}
		}

//		for (int posY = 0; posY < yVal.size(); posY++) {
//			for (int index = 0; index < xVal.size(); index++) {
//				if (yVal.get(index) == posY) {
//					if (xVal.get(index) >= 0 && yVal.get(index) >= 0) {
//					}
//					if (xVal.get(index) < 0 && yVal.get(index) >= 0) {
//						tempy.add(yVal.get(index));
//					}
//					if (xVal.get(index) < 0 && yVal.get(index) < 0) {
//						tempy.add(yVal.get(index));
//					}
//					if (xVal.get(index) >= 0 && yVal.get(index) < 0) {
//						tempy.add(yVal.get(index));
//					}
//				}
//			}
//		}

		Vector<Integer> xValues = new Vector<Integer>();
		Vector<Integer> yValues = new Vector<Integer>();

		int area = 0;
		for (int j = 0; j <= getRectLength(); j++) {
			Vector<Integer> temp = new Vector<Integer>();
			System.out.println();
			for (int i = 1; i < coords.size(); i++) {
				if (coords.get(i).x == j) {
					temp.add(Math.max(coords.get(i).y, coords.get(i - 1).y));
				}
			}
			temp.sort(null);
			xValues.add(j);
			yValues.add(temp.get(0));
			xValues.add(j + 1);
			yValues.add(temp.get(0));
			xValues.add(j + 1);
			yValues.add(temp.get(temp.size() - 1));
			xValues.add(j);
			yValues.add(temp.get(temp.size() - 1));
			System.out.println(j + " | " + temp.get(0));
			System.out.println((j + 1) + " | " + temp.get(0));
			System.out.println((j + 1) + " | " + temp.get(temp.size() - 1));
			System.out.println(j + " | " + temp.get(temp.size() - 1));
			System.out.println();
			System.out.println(temp);
			System.out.println("-------------------------------------");

			area += calcArea(xValues, yValues);
		}
		setPockets(area - getAreaFigure());
	}

	private void printAllCoords(Vector<Integer> xVal, Vector<Integer> yVal) {
		System.out.println();
		for (int i = 0; i < xVal.size(); i++) {
			if (xVal.get(i) >= 0 && yVal.get(i) >= 0) {
				System.out.println("+" + xVal.get(i) + " | +" + yVal.get(i));
			}
			if (xVal.get(i) < 0 && yVal.get(i) >= 0) {
				System.out.println(xVal.get(i) + " | +" + yVal.get(i));
			}
			if (xVal.get(i) < 0 && yVal.get(i) < 0) {
				System.out.println(xVal.get(i) + " | " + yVal.get(i));
			}
			if (xVal.get(i) >= 0 && yVal.get(i) < 0) {
				System.out.println("+" + xVal.get(i) + " | " + yVal.get(i));
			}
		}
	}

	private void printCoords(Vector<Integer> xVal, Vector<Integer> yVal) {
		System.out.println("TOP RIGHT");
		System.out.println("+" + xVal.get(0) + " | +" + yVal.get(0));
		for (int i = 1; i < xVal.size(); i++) {
			if (xVal.get(i) >= 0 && yVal.get(i) >= 0) {
				System.out.println("+" + xVal.get(i) + " | +" + yVal.get(i));
			}
		}

		System.out.println("\nTOP LEFT");
		System.out.println("+" + xVal.get(0) + " | +" + yVal.get(0));
		for (int i = 1; i < xVal.size(); i++) {
			if (xVal.get(i) < 0 && yVal.get(i) >= 0) {
				System.out.println(xVal.get(i) + " | +" + yVal.get(i));
			}
		}

		System.out.println("\nBOTTOM LEFT");
		System.out.println("+" + xVal.get(0) + " | +" + yVal.get(0));
		for (int i = 1; i < xVal.size(); i++) {
			if (xVal.get(i) < 0 && yVal.get(i) < 0) {
				System.out.println(xVal.get(i) + " | " + yVal.get(i));
			}
		}

		System.out.println("\nBOTTOM RIGHT");
		System.out.println("+" + xVal.get(0) + " | +" + yVal.get(0));
		for (int i = 0; i < xVal.size(); i++) {
			if (xVal.get(i) >= 0 && yVal.get(i) < 0) {
				System.out.println("+" + xVal.get(i) + " | " + yVal.get(i));
			}
		}
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

	public int getPockets() {
		return pockets;
	}

	public void setPockets(int pockets) {
		this.pockets = pockets;
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
