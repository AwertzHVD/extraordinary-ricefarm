package com.stiwa.hashmap.auctionbidding;

import java.util.Arrays;
import java.util.HashMap;

public class RealShid {
	private int currentBid;
	private int previousHighestBid;
	private HashMap<String, Integer> biddings = new HashMap<String, Integer>();
	private HashMap<String, Integer> highestBidder = new HashMap<String, Integer>();
	private String outputText;

	public RealShid(String input) {
		splitInput(input);
		prepareOutputText();
		System.out.println(getParticipantByMax() + " " + this.previousHighestBid);
	}

	private void prepareOutputText() {
		this.outputText = this.highestBidder.toString().replace("{", "");
		this.outputText = this.outputText.replace("}", "");
		this.outputText = this.outputText.replace("=", ",");
	}

	public void splitInput(String input) {
		String[] info = input.split(",");
		setCurrentBid(Integer.parseInt(info[0]));
		this.highestBidder.put(info[1], Integer.parseInt(info[2]));

		for (int index = 1; index < info.length; index += 2) {
			this.biddings.put(info[index], Integer.parseInt(info[index + 1]));
			setHighestBidder(info[index], Integer.parseInt(info[index + 1]));
		}

	}

	public String getParticipantByMax() {
		Object[] keys = this.biddings.keySet().toArray();
		int[] values = new int[this.biddings.size()];
		for (int i = 0; i < this.biddings.size(); i++) {
			values[i] = this.biddings.get(keys[i]);
		}
		Arrays.sort(values);
		int max = values[values.length - 1];

		setPreviousHighest(values);

		return getKey(this.biddings, max);
	}

	private void setPreviousHighest(int[] values) {
		int previousHighest;
		if (values.length > 1) {
			previousHighest = values[values.length - 2] + 1;
		} else {
			previousHighest = values[values.length - 1] + 1;
		}
		this.previousHighestBid = previousHighest;
	}

	public <K, V> K getKey(HashMap<K, V> map, V value) {
		for (HashMap.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public void setHighestBidder(String participant, int bidAmount) {
		Object firstKey = this.highestBidder.keySet().toArray()[0];
		int highestBid = this.highestBidder.get(firstKey);

		if (highestBid < bidAmount) {
			this.highestBidder.clear();
			this.highestBidder.put(participant, bidAmount);
		}

	}

	public int getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(int currentBid) {
		this.currentBid = currentBid;
	}

	public HashMap<String, Integer> getBiddings() {
		return biddings;
	}

	public void setBiddings(HashMap<String, Integer> biddings) {
		this.biddings = biddings;
	}

}
