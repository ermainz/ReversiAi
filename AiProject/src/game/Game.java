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
		Ai ai_black = new Ai(0, Color.BLACK, Algorithms.RANDOMGREEDY);
		Ai ai_white = new Ai(0, Color.WHITE, Algorithms.RANDOMGREEDY);
		game.turn = Color.BLACK;
		int numTurns = 4;
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
			game.switchTurn();
			numTurns++;

			if (numTurns >= 64) {
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
