package com.stiwa.distancechecker;

public class StartDistanceChecker {

	public static void main(String[] args) {
		String[] inputs = { "1 FFFR 4", "9 F 6 R 1 F 4 RFF 2 LFF 1 LFFFR 1 F 2 R 1 F 5",
				"14 L 1 FR 1 FFFFFL 1 FFFFL 1 F 12 L 1 F 12 L 1 F 12 L 1 FFFFL 1 FFFFFFFFR 1 FFFR 1 FFFL 1",
				"32 FFRFLFLFFRFRFLFF 3 R 1 FFLFRFRFLFFF 3 R 1 FFFFFF 3 L 1 FFFRFLFLFRFF 2 R 1 FFFRFLFLFRFF 3 R 1 FFFFFF 1 L 1 FFRFLFLFFRFRFLFF 3 R 1 FFLFRFRFFLFLFRFF 2 L 1 FFLFRFRFFLFLFRFF 3 R 1 FFRFLFLFFRFRFLFF 2 R 1 FFRFLFLFFRFRFLFF 2 L 1 FFFFFF 3 R 1 FFFRFLFLFRFF 5 R 1 FFLFRFRFLFFF 1 L 1 FFLFRFRFFLFLFRFF 2 R 1 FFRFLFLFFRFRFLFF 2 L 1",
				"10 FFLFRFRFFLFLFRFF 5 L 1 FFFRFLFLFRFF 4 L 1 FFLFRFRFFLFLFRFF 8 L 1 FFLFRFRFFLFLFRFF 4 L 1 FFFFFF 3 R 1" };

//
		for (String input : inputs) {
//			Lv5 lv4 = new Lv5(input);
		}

		String temp1 = "1 FFL 1 FFR 2 FFL 1 FFR 1 FFFFFR 1 FFR 1 FFFL 1 FFL 1 FFFFR 1 FFR 1 F 6";

		String temp2 = "1 L 1 FFR 2 FFL 1 FFR 1 FFFFFR 1 FFR 1 FFFL 1 FFL 1 FFFFR 1 FFR 1 F 6 R 1 F 2";

//		Lv3 lv3 = new Lv3(inputs[1]);

		Lv5OLD lv5 = new Lv5OLD("1 L 1 FR 1 FFFFR 2 F 6 R 1 FFR 1 FR 1 FL 1 F 4 L 1 FFL 1 F 3"); 

//		Lv5 lv5 = new Lv5(inputs[2]); //111 /102
		
		
	}

}
