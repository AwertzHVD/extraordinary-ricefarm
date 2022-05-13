package com.stiwa.hashmap.auctionbidding;

import java.util.Vector;

public class AuctionOverviewNewNEW {
	private int currentBid;

	private Vector<String> biddingKeys = new Vector<String>();
	private Vector<Integer> biddingVals = new Vector<Integer>();
	private String highestBidderKey;
	private int highestBiddingVal;

	public AuctionOverviewNewNEW(String input) {
		splitInput(input);

	}

	public void splitInput(String input) {
		String[] info = input.split(",");

		setCurrentBid(Integer.parseInt(info[0]));

		for (int i = 1; i < info.length; i += 2) {
			this.biddingKeys.add(info[i]);
			this.biddingVals.add(Integer.parseInt(info[i + 1]));
		}

		String previousBidderKey = this.biddingKeys.get(0);
		int previousBidderVal = this.biddingVals.get(0);
		String output = "-," + getCurrentBid();
		output += "," + previousBidderKey + "," + getCurrentBid();

		for (int index = 1; index < this.biddingKeys.size(); index++) {
			if (this.biddingVals.get(index) == previousBidderVal) {
				setCurrentBid(this.biddingVals.get(index));
				System.out.println("jump");
				continue;
			}
			if (this.biddingKeys.get(index).equalsIgnoreCase(previousBidderKey)) {
				if (this.biddingVals.get(index) > previousBidderVal) {
					setCurrentBid(previousBidderVal + 1);
					previousBidderKey = this.biddingKeys.get(index);
					previousBidderVal = this.biddingVals.get(index);
				}
			}
			output += "," + previousBidderKey + "," + (previousBidderVal+1);
		}
		System.out.println(output);
	}

	public void printTemp(String previousBidderKey, int previousBidderVal) {
		System.out.println(previousBidderKey + "=" + previousBidderVal);
		System.out.println(previousBidderKey + "," + getCurrentBid());
	}

	public int getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(int currentBid) {
		this.currentBid = currentBid;
	}

	public Vector<String> getBiddingKeys() {
		return biddingKeys;
	}

	public void setBiddingKeys(Vector<String> biddingKeys) {
		this.biddingKeys = biddingKeys;
	}

	public Vector<Integer> getBiddingVals() {
		return biddingVals;
	}

	public void setBiddingVals(Vector<Integer> biddingVals) {
		this.biddingVals = biddingVals;
	}

	public String getHighestBidderKey() {
		return highestBidderKey;
	}

	public void setHighestBidderKey(String highestBidderKey) {
		this.highestBidderKey = highestBidderKey;
	}

	public int getHighestBiddingVal() {
		return highestBiddingVal;
	}

	public void setHighestBiddingVal(int highestBiddingVal) {
		this.highestBiddingVal = highestBiddingVal;
	}

}
