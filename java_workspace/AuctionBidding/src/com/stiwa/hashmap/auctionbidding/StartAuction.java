package com.stiwa.hashmap.auctionbidding;

public class StartAuction {
	public static void main(String[] args) {
		String[] inputs1 = { "1,A,5,B,10,A,8,A,17,B,17",
				"1,nepper,15,hamster,24,philipp,30,mmautne,31,hamster,49,thebenil,57,fliegimandi,59,ev,61,philipp,64,ev,74,philipp,69,philipp,71,fliegimandi,78,hamster,78,mio,95,hamster,103,macquereauxpl,135",
				"1,cable,5,ente,10,karl,19,moehe,14,moehe,23,michbex,24,melloy,25,achi,26",
				"1,cable,5,ente,10,karl,19,moehe,23,michbex,24,melloy,29,achi,26",
				"15,urtyp,17,neuli,16,schlurchi,25,tokay,75,horni,35,sue,60,sue,70", "15,urtyp,15" };

		String[] inputs2 = { "1,A,5,B,10,A,8,A,14,A,17,B,17",
				"1,nepper,15,hamster,24,philipp,30,mmautne,31,hamster,49,hamster,55,thebenil,57,fliegimandi,59,ev,61,philipp,64,philipp,65,ev,74,philipp,69,philipp,71,fliegimandi,78,hamster,78,mio,95,hamster,103,macquereauxpl,135",
				"15,urtyp,17,neuli,16,schlurchi,25,tokay,75,horni,35,sue,60,sue,65,gap,70",
				"100,A,100,A,115,A,119,A,144,A,145,A,157,A,158,A,171,A,179,A,194,A,206,A,207,A,227,A,229,A,231,A,234",
				"100,C,100,C,115,C,119,C,121,C,144,C,154,C,157,G,158,C,171,C,179,C,194,C,206,C,214,C,227,C,229,C,231,C,298" };

		for (String input : inputs2) {
			AuctionOverviewNewNEW ao1 = new AuctionOverviewNewNEW(input);
		}

	}
}
