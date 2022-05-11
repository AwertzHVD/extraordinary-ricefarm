package com.stiwa.hashmap.auctionbidding;

import java.util.HashMap;

public class AuctionOverview {
	private int currentBid;
	private int previousHighestBid;
	private HashMap<String, Integer> biddings = new HashMap<String, Integer>();
	private HashMap<String, Integer> highestBidder = new HashMap<String, Integer>();
	private String outputText;

	public AuctionOverview(String input) {
		splitInput(input);
		prepareOutputText();

	}

	private void prepareOutputText() {
		this.outputText = this.highestBidder.toString().replace("{", "");
		this.outputText = this.outputText.replace("}", "");
		this.outputText = this.outputText.replace("=", ",");
		System.out.println(this.outputText);
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

	public void setHighestBidder(String participant, int bidAmount) {
		Object firstKey = this.highestBidder.keySet().toArray()[0];
		Object highestBid = this.highestBidder.get(firstKey);

		if ((int) highestBid < bidAmount) {
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
