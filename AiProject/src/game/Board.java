package game;

public class Board {

	static final int boardSize = 8;
	
	//board[row][column]
	public GameDisk[][] board = new GameDisk[boardSize][boardSize];
	
	public Board(){
		for(int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				board[i][j] = null;
			}
		}
	}
	
	public Board(Board oldBoard){
		for(int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				board[i][j] = oldBoard.board[i][j];
			}
		}
	}
	
	public void addDisk(Color color, int row, int column){
		if (board[row][column] == null){
			board[row][column] = new GameDisk(color);
		}
	}
	
	public void displayBoard(){
		String toPrint = "";
		for(int i = 0; i < boardSize; i++){
			toPrint += "|";
			for(int j = 0; j < boardSize; j++){
				if(board[i][j] == null){
					toPrint += " ";
				}
				else {
					GameDisk disk = board[i][j];
					switch (disk.color){
						case BLACK:
							toPrint += "X";
							break;
						case WHITE:
							toPrint += "O";
							break;
					}
							
				}
			}
			toPrint += "|";
			System.out.println(toPrint);
			toPrint = "";
		}
		
		System.out.println();

		
	}
	
}
