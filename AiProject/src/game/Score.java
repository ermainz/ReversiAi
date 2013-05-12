package game;

public class Score {
	
	int black_score;
	int white_score;
	
	public Score(int black, int white){
		black_score = black;
		white_score = white;
	}
	
	public int getWhiteScore(){
		return white_score;
	}
	
	public int getBlackScore(){
		return black_score;
	}

}
