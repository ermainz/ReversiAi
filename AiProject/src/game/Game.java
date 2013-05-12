package game;

import java.util.Scanner;

public class Game {

	/**
	 * @param args
	 */
	Board gameBoard;
	Color turn = Color.BLACK;
	
	public Game(){
		gameBoard = new Board();
		gameBoard.addDisk(Color.BLACK, 3, 4);
		gameBoard.addDisk(Color.BLACK, 4, 3);
		gameBoard.addDisk(Color.WHITE, 3, 3);
		gameBoard.addDisk(Color.WHITE, 4, 4);
	}
	
	public void switchTurn(){
		if (turn == Color.BLACK){
			turn = Color.WHITE;
		}
		else turn = Color.BLACK;
	}
	
	public void applyMove(int row, int col){
		gameBoard.addDisk(turn, row, col);
		//catch exception for illegal move, if caught, don't switch turn
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		Scanner reader = new Scanner(System.in);
	
		while(true){
			game.gameBoard.displayBoard();
			
			System.out.println("Enter row:");
			int row = reader.nextInt() - 1;
			System.out.println("Enter col:");
			int col = reader.nextInt() - 1;
			
			game.applyMove(row, col);
			game.switchTurn();
			
		}

	}

}
