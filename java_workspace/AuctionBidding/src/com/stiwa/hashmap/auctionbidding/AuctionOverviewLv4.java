package com.stiwa.hashmap.auctionbidding;

import java.util.Vector;

public class AuctionOverviewLv4 {
	private Vector<String> names = new Vector<String>();
	private Vector<Integer> bids = new Vector<Integer>();
	private int currentBid;
	private int buyNowVal;
	private int startingBid;
	private String highestBidder = "";
	private int highestBidAmount;

	private boolean buyNowFuction;

	public AuctionOverviewLv4(String input) {
		splitInput(input);
		run();

	}

	public void splitInput(String input) {
		String[] info = input.split(",");

		setStartingBid(Integer.parseInt(info[0]));
		setBuyNowVal(Integer.parseInt(info[1]));

		for (int index = 2; index < info.length; index += 2) {
			getNames().add(info[index]);
			getBids().add(Integer.parseInt(info[index + 1]));
		}
	}

	public void run() {
		// "1,A,5,B,10,A,8,A,14,A,17,B,17"
		String output = "-," + getStartingBid();

		for (int index = 0; index < getBids().size(); index++) {
			if (getHighestBidder().equalsIgnoreCase("")) {
				setCurrentBid(getStartingBid());
				setHighestBidAmount(getBids().get(index));
				setHighestBidder(getNames().get(index));
				output += "," + getHighestBidder() + "," + getCurrentBid();
			}

			if (getHighestBidder().equalsIgnoreCase(getNames().get(index))) {
				if (getBids().get(index) > getHighestBidAmount()) {
					setHighestBidAmount(getBids().get(index));
				}
			} else {
				if (getBids().get(index) == getHighestBidAmount()) {
					setCurrentBid(getBids().get(index));
					if (getCurrentBid() > getBuyNowVal()&&getBuyNowVal()>0) {
						setCurrentBid(getBuyNowVal());
					}
				} else if (getBids().get(index) < getHighestBidAmount()) {
					setCurrentBid(getBids().get(index) + 1);
					if (getCurrentBid() > getBuyNowVal()&&getBuyNowVal()>0) {
						setCurrentBid(getBuyNowVal());
					}
				} else if (getBids().get(index) > getHighestBidAmount()) {
					setCurrentBid(getHighestBidAmount() + 1);
					setHighestBidAmount(getBids().get(index));
					setHighestBidder(getNames().get(index));
					if (getCurrentBid() > getBuyNowVal()&&getBuyNowVal()>0) {
						setCurrentBid(getBuyNowVal());
					}
				}
				output += "," + getHighestBidder() + "," + getCurrentBid();
			}

		}
		System.out.println(output);
		System.out.println();
	}

	public void printTemp(String previousBidderKey, int previousBidderVal) {
		System.out.println(previousBidderKey + "=" + previousBidderVal);
		System.out.println(previousBidderKey + "," + getCurrentBid());
	}

	public Vector<String> getNames() {
		return names;
	}

	public void setNames(Vector<String> names) {
		this.names = names;
	}

	public Vector<Integer> getBids() {
		return bids;
	}

	public void setBids(Vector<Integer> bids) {
		this.bids = bids;
	}

	public int getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(int currentBid) {
		this.currentBid = currentBid;
	}

	public int getBuyNowVal() {
		return buyNowVal;
	}

	public void setBuyNowVal(int buyNowVal) {
		this.buyNowVal = buyNowVal;
	}

	public int getStartingBid() {
		return startingBid;
	}

	public void setStartingBid(int startingBid) {
		this.startingBid = startingBid;
	}

	public String getHighestBidder() {
		return highestBidder;
	}

	public void setHighestBidder(String highestBidder) {
		this.highestBidder = highestBidder;
	}

	public int getHighestBidAmount() {
		return highestBidAmount;
	}

	public void setHighestBidAmount(int highestBidAmount) {
		this.highestBidAmount = highestBidAmount;
	}

	public boolean isBuyNowFuction() {
		return buyNowFuction;
	}

	public void setBuyNowFuction(boolean buyNowFuction) {
		this.buyNowFuction = buyNowFuction;
	}

}
