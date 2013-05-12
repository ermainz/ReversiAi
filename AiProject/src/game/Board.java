package game;

public class Board {

	static final int boardSize = 8;
	
	//board[row][column]
	GameDisk[][] board = new GameDisk[boardSize][boardSize];
	
	public Board(){
		for(int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				board[i][j] = null;
			}
		}
	}
	
	public void addDisk(Color color, int row, int column){
		if (board[row][column] == null){
			board[row][column] = new GameDisk(color);
		}
	}
	
}
