package game;

public class GameDisk {

	final int black = 1;
	final int white = 0;
	
	public Color color;
	
	public GameDisk (Color color){
		if (color == Color.BLACK){
			this.color = Color.BLACK;
		}
		else if (color == Color.WHITE){
			this.color = Color.WHITE;
		}
	}
	
	public boolean isBlack(){
		if (this.color == Color.BLACK){
			return true;
		}
		else return false;
	}
	
	public boolean isWhite(){
		if (this.color == Color.WHITE){
			return true;
		}
		else return false;
	}
	
	public boolean equals(GameDisk other){
		if (this.color == other.color){
			return true;
		}
		else return false;
	}
	
	public void flip(){
		if (this.color == Color.BLACK){
			this.color = Color.WHITE;
		}
		else{
			this.color = Color.BLACK;
		}
	}
	
}
