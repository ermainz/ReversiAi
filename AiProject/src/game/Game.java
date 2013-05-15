package game;

import java.util.Scanner;

import ai.Ai;
import ai.Algorithms;

public class Game {

	/**
	 * @param args
	 */
	Board gameBoard;
	Color turn = Color.WHITE;

	public Game() {
		gameBoard = new Board();

	}

	public void switchTurn() {
		if (turn == Color.BLACK) {
			turn = Color.WHITE;
		} else
			turn = Color.BLACK;
	}

	public void displayBoard() {
		if (turn == Color.BLACK) {
			System.out.println("Turn: X");
		} else
			System.out.println("Turn: O");
		gameBoard.displayBoard();
	}

	public void applyMove(int row, int col) {
		gameBoard.addDisk(turn, row, col);
		// catch exception for illegal move, if caught, don't switch turn
	}

	public static void main(String[] args) {
		Game game = new Game();

		Scanner reader = new Scanner(System.in);
		// X - Color.BLACK goes 1st
		// O - Color.WHITE goes 2nd
		Ai ai_black = new Ai(2, Color.BLACK, Algorithms.RAND);
		Ai ai_white = new Ai(3, Color.WHITE, Algorithms.MINIMAX);
		//Ai ai_white = new Ai(3, Color.WHITE, Algorithms.RAND);
		game.turn = Color.BLACK;
		
		while (true) {


			// }
			/*//HUMAN PLAYER
			 * System.out.println("Enter row:"); int row = reader.nextInt() - 1;
			 * System.out.println("Enter col:"); int col = reader.nextInt() - 1;
			 * 
			 * game.applyMove(row, col);
			 */
			
			game.displayBoard();
			if (game.turn == Color.WHITE){
				ai_white.MakeMove(game.gameBoard);
			} else {
				ai_black.MakeMove(game.gameBoard);
			}
			ai_white.stats(game.gameBoard.numMovesMade);
			ai_black.stats(game.gameBoard.numMovesMade);
			game.switchTurn();
			

			if (game.gameBoard.numMovesMade >= 64) {
				System.out.println("Final Score: White - "
						+ game.gameBoard.getScoreOfBoard().white_score
						+ " Black - "
						+ game.gameBoard.getScoreOfBoard().black_score);
				game.displayBoard();
				break;
			}

		}

	}

}
