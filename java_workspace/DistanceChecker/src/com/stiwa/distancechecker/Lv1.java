package com.stiwa.distancechecker;

import java.util.Vector;

public class Lv1 {
	private int steps;
	private int inputMoves;
	private Vector<String> movement = new Vector<String>();
	private Vector<Integer> repition = new Vector<Integer>();

	public Lv1(String input) {
		System.out.println();
		process(input);
		distanceCheck();
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
			int stepsAmount=0;
			for (int i = 0; i < getMovement().get(index).length(); i++) {
				if (getMovement().get(index).charAt(i) == 'F') {
					stepsAmount++;
				}
				
			}
			int repeat = getRepition().get(index);
			counter+=stepsAmount*repeat;
		}
		System.out.println(counter);
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
