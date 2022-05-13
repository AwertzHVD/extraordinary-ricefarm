package com.stiwa.hashmap.auctionbidding;

import java.util.Vector;

public class AuctionOverviewLv3 {
	private int currentBidVal;
	private int currentBidKey;
	private int startingBid;
	private Vector<String> biddingKeys = new Vector<String>();
	private Vector<Integer> biddingVals = new Vector<Integer>();

	private String highestBidKey;
	private int highestBidVal;

	public AuctionOverviewLv3(String input) {
		splitInput(input);
		dasLetze();

	}

	public void splitInput(String input) {
		String[] info = input.split(",");

		setStartingBid(Integer.parseInt(info[0]));

		for (int i = 1; i < info.length; i += 2) {
			getBiddingKeys().add(info[i]);
			getBiddingVals().add(Integer.parseInt(info[i + 1]));
		}
	}

	public void dasLetze() {
		// "1,A,5,B,10,A,8,A,14,A,17,B,17"
		String output = "-," + getStartingBid();
		setHighestBidVal(getStartingBid());
		setHighestBidKey(getBiddingKeys().get(0));

		for (int index = 0; index < getBiddingVals().size(); index++) {
			if (getBiddingVals().get(index)==getHighestBidVal()) {
				setHighestBidVal(getBiddingVals().get(index));
				output += "," + getHighestBidKey() + "," + getHighestBidVal();
				continue;
			}
			if (getBiddingVals().get(index) < getHighestBidVal() && getBiddingVals().get(index) > getCurrentBid()) {
				setCurrentBid(getBiddingVals().get(index) + 1);
				output += "," + getHighestBidKey() + "," + getCurrentBid();
				continue;
			}
			if (getHighestBidVal() == getStartingBid()) {
				output += "," + getBiddingKeys().get(index) + "," + (getHighestBidVal());
				setCurrentBid(getHighestBidVal());
				setHighestBidVal(getBiddingVals().get(index));
				setHighestBidKey(getBiddingKeys().get(index));
				continue;
			}
			if (getBiddingVals().get(index) > getHighestBidVal()) {
				output += "," + getBiddingKeys().get(index) + "," + (getHighestBidVal() + 1);
				setCurrentBid(getHighestBidVal() + 1);
				setHighestBidVal(getBiddingVals().get(index));
				setHighestBidKey(getBiddingKeys().get(index));
				continue;
			}
			
		}
		System.out.println(output);
	}

	public void printTemp(String previousBidderKey, int previousBidderVal) {
		System.out.println(previousBidderKey + "=" + previousBidderVal);
		System.out.println(previousBidderKey + "," + getCurrentBid());
	}

	public int getCurrentBid() {
		return currentBidVal;
	}

	public void setCurrentBid(int currentBid) {
		this.currentBidVal = currentBid;
	}

	public int getStartingBid() {
		return startingBid;
	}

	public void setStartingBid(int startingBid) {
		this.startingBid = startingBid;
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

	public String getHighestBidKey() {
		return highestBidKey;
	}

	public void setHighestBidKey(String highestBidKey) {
		this.highestBidKey = highestBidKey;
	}

	public int getHighestBidVal() {
		return highestBidVal;
	}

	public void setHighestBidVal(int highestBidVal) {
		this.highestBidVal = highestBidVal;
	}

}
