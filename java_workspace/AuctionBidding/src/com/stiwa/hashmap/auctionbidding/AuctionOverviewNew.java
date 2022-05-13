package com.stiwa.hashmap.auctionbidding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AuctionOverviewNew {
	private int currentBid;
	private int previousHighestBid;
	private String highestBiddingUser;
	private HashMap<String, Integer> biddings = new HashMap<String, Integer>();
	private HashMap<String, Integer> highestBidder = new HashMap<String, Integer>();
	private String outputText;

	public AuctionOverviewNew(String input) {
		splitInput(input);
	}

	public void splitInput(String input) {
		String[] info = input.split(",");
		setCurrentBid(Integer.parseInt(info[0]));

		int counter = 1;
		for (int index = 1; index < info.length; index += 2) {
			this.biddings.put(info[index] + "_" + counter, Integer.parseInt(info[index + 1]));
			counter++;
		}
		Object[] keys = this.biddings.keySet().toArray();
		int[] values = new int[this.biddings.size()];
		for (int i = 0; i < this.biddings.size(); i++) {
			values[i] = this.biddings.get(keys[i]);
		}
		Arrays.sort(values);
		int max = values[values.length - 1];
		int previousHighest;
		if (values.length > 1) {
			previousHighest = values[values.length - 2] + 1;
		} else {
			previousHighest = values[values.length - 1] + 1;
		}
		String participant = "";

		participant = getKey(this.biddings, max);
		System.out.println(participant + "," + previousHighest);
	}

	public <K, V> K getKey(HashMap<K, V> map, V value) {
		for (HashMap.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
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
