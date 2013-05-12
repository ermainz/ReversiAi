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

	}
	
	public void switchTurn(){
		if (turn == Color.BLACK){
			turn = Color.WHITE;
		}
		else turn = Color.BLACK;
	}
	
	public void displayBoard(){
		if (turn == Color.BLACK){
			System.out.println("Turn: X");
		} else System.out.println("Turn: O");
		gameBoard.displayBoard();
	}
	
	public void applyMove(int row, int col){
		gameBoard.addDisk(turn, row, col);
		//catch exception for illegal move, if caught, don't switch turn
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		Scanner reader = new Scanner(System.in);
	
		while(true){
			game.displayBoard();
			
			System.out.println("Enter row:");
			int row = reader.nextInt() - 1;
			System.out.println("Enter col:");
			int col = reader.nextInt() - 1;
			
			game.applyMove(row, col);
			game.switchTurn();
			
		}

	}

}
