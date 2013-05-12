package game;

import java.util.LinkedList;
import java.util.List;

public class Board {

	static final int boardSize = 8;
	
	//board[row][column]
	/*
	 * 	cols  1 2 3 4 5 6 7 8
	 * rows	1 - - - - - - - -
	 * 		2 - - - - - - - -
	 * 		3 - - - - - - - -
	 * 		4 - - - - - - - -
	 * 		5 - - - - - - - -
	 * 		6 - - - - - - - -
	 * 		7 - - - - - - - -
	 * 		8 - - - - - - - -
	 * 		
	 */
	public Color[][] board = new Color[boardSize][boardSize];
	
	public Board(){
		for(int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				board[i][j] = Color.EMPTY;
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
	
	public boolean isMoveLegal(Color color, int row, int column){
		//check up
		for(int i = row - 1; i >= 0; i--){
			boolean surrounding = false;
			if(board[i][column] == null){
				break;
			}
			if(!colorEquals(board[i][column],color)){ // other persons disk
				surrounding = true;
			}
			else { //same color disk
				if(surrounding){
					return true;
				}
				else break;
			}
		}
		//check up, right
		//check right
		//check down, right
		//check down
		//check down, left
		//check left
		//check up, left
		return false;
	}
	
	public boolean colorEquals(Color color1, Color color2){
		if (color1 == color2){
			return true;
		}
		else return false;
	}
	
	public void flipDisk(int row, int col){
		if (board[row][col] == Color.BLACK){
			board[row][col] = Color.WHITE;
		}
		else {
			board[row][col] = Color.BLACK;
		}
	}
	
	public void addDisk(Color color, int row, int column){
		if (board[row][column] == Color.EMPTY){
			board[row][column] = color;
			//check for move not illegal
			//if illegal throw exception
		}
		else{
			System.out.println("Error: illegal move");
			return;
		}
		
		List<Integer> toFlip = new LinkedList<Integer>();

		//flip pieces
		//check up
		for(int i = row-1; i >= 0; i--){
			if(board[i][column] == null){
				toFlip.clear();
				break;
			}
			if(!colorEquals(board[i][column],color)){
				toFlip.add(i);
			}
			else { // board[row][i].colorEquals(color) == true
				break;
			}
		}
		for(Integer r : toFlip){
			flipDisk(r, column);
		}
		toFlip.clear();
		//check right, up diagonal
		//check right
		for(int i = column+1; i < boardSize; i++){
			if(board[row][i] == null){
				toFlip.clear();
				break;
			}
			if(!colorEquals(board[row][i],color)){
				toFlip.add(i);
			}
			else { // board[row][i].colorEquals(color) == true
				break;
			}
		}
		for(Integer c : toFlip){
			flipDisk(row, c);
		}
		toFlip.clear();
		//check down, right diagonal
		//check down
		for(int i = row+1; i < boardSize; i++){
			if(board[i][column] == null){
				toFlip.clear();
				break;
			}
			if(!colorEquals(board[i][column],color)){
				toFlip.add(i);
			}
			else { // board[row][i].colorEquals(color) == true
				break;
			}
		}
		for(Integer r : toFlip){
			flipDisk(r,column);
		}
		toFlip.clear();
		//check down, left diagonal
		//check left
		for(int i = column-1; i >= 0; i--){
			if(board[row][i] == null){
				toFlip.clear();
				break;
			}
			if(!colorEquals(board[row][i],color)){
				toFlip.add(i);
			}
			else { // board[row][i].colorEquals(color) == true
				break;
			}
		}
		for(Integer c : toFlip){
			flipDisk(row,c);
		}
		toFlip.clear();
		//check up, left diagonal
	}
	
	public void displayBoard(){
		String toPrint = "";
		for(int i = 0; i < boardSize; i++){
			toPrint += "| ";
			for(int j = 0; j < boardSize; j++){
				if(board[i][j] == null){
					toPrint += "- ";
				}
				else {
					switch (board[i][j]){
						case BLACK:
							toPrint += "X ";
							break;
						case WHITE:
							toPrint += "O ";
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
