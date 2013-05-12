package game;

public class GameDisk {

	final int black = 1;
	final int white = 0;
	
	private int color;
	
	public GameDisk (int color){
		if (color == black){
			this.color = black;
		}
		else if (color == white){
			this.color = white;
		}
		else {
			this.color = white;
		}
	}
	
	public boolean isBlack(){
		if (this.color == black){
			return true;
		}
		else return false;
	}
	
	public boolean isWhite(){
		if (this.color == white){
			return true;
		}
		else return false;
	}
	
	public boolean equals(int other_color){
		if (this.color == other_color){
			return true;
		}
		else return false;
	}
	
}
