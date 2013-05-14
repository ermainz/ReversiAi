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
		Ai ai_black = new Ai(6, Color.BLACK, Algorithms.MINIMAX);
		Ai ai_white = new Ai(6, Color.WHITE, Algorithms.MINIMAX);
		int numTurns = 4;
		while (true) {
			// if(game.turn == Color.WHITE) {
			// Ai.GreedyBFSMove(game.gameBoard);

			// }
			/*
			 * System.out.println("Enter row:"); int row = reader.nextInt() - 1;
			 * System.out.println("Enter col:"); int col = reader.nextInt() - 1;
			 * 
			 * game.applyMove(row, col);
			 */
			game.displayBoard();
			//ai_white.MakeMove(game.gameBoard);
			ai_black.MakeMove(game.gameBoard);
			game.switchTurn();
			numTurns++;
			
			game.displayBoard();
			//ai_black.MakeMove(game.gameBoard);
			ai_white.MakeMove(game.gameBoard);
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
