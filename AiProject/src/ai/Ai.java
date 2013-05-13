package ai;

import java.awt.List;
import java.util.ArrayList;

import game.Board;
import game.Color;
import game.Game;
import game.Move;

public class Ai {

	// test comment
	public Move MakeMove() {
		Move current = new Move();
		return current;
	}

	public static ArrayList<Move> getPossibleMoves(Board b, Color c) {
		// Board temp = b;
		//System.out.print("Possible Moves: \n");
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (b.board[i][j] == Color.EMPTY) {
					if (b.isMoveLegal(c, i, j)) {
						// System.out.print("test");
						Move newm = new Move();
						newm.row = i;
						newm.column = j;
						moves.add(newm);

					}
				}
			}
		}
		return moves;
	}

	public static void GreedyBFSMove(Board b) {
		ArrayList<Move> moves = getPossibleMoves(b, Color.WHITE);
		Move movetomake = new Move();
		movetomake.column = 4;
		movetomake.row = 4;
		int currenthigh = 0;
		for (Move m : moves) {
			Board temp = new Board(b);
			int k = m.row + 1;
			int x = m.column + 1;
			temp.addDisk(Color.WHITE, m.row, m.column);
			System.out.print(Color.WHITE + " " + k + " " + x + "\n"
					+ temp.getScoreOfBoard().getWhiteScore() + "\n");
			if (temp.getScoreOfBoard().getWhiteScore() > currenthigh) {
				currenthigh = temp.getScoreOfBoard().getWhiteScore();
				movetomake = m;
			}
		}
		b.addDisk(Color.WHITE, movetomake.row, movetomake.column);
	}

	public static void MinimaxMove(Board b) {
		// int totalMoves = b.getScoreOfBoard().getBlackScore() +
		// b.getScoreOfBoard().getWhiteScore();
		ArrayList<Move> moves = getPossibleMoves(b, Color.WHITE);
		ArrayList<Node> nodes = new ArrayList<Node>();
		Node head = new Node();
		head.move = null;
		head.board = b;
		head.score = b.getScoreOfBoard();
		Node next = CreateNode(head, Color.WHITE, 0);
		b.addDisk(Color.WHITE, next.move.row, next.move.column);
		int k = next.move.row + 1;
		int x = next.move.column + 1;
		System.out.print(Color.WHITE + " " + k + " " + x + "\n"
				+ next.board.getScoreOfBoard().getWhiteScore() + "\n");
	}

	public static Node CreateNode(Node cur, Color c, int depth) {
		if (depth > 6) {
			return cur;
		}
		ArrayList<Move> moves = getPossibleMoves(cur.board, c);
		cur.children = new ArrayList<Node>();
		if(moves.size() == 0){
			return cur;
		}
		for (Move m : moves) {
			Board tempBoard = new Board(cur.board);
			Node temp = new Node();
			tempBoard.addDisk(c, m.row, m.column);
			temp.board = tempBoard;
			temp.score = tempBoard.getScoreOfBoard();
			temp.move = m;
			if (c == Color.WHITE) {
				cur.children.add(CreateNode(temp, Color.BLACK, depth + 1));
			} else {
				cur.children.add(CreateNode(temp, Color.WHITE, depth + 1));
			}
		}
		if (c == Color.WHITE) {
			Node maxNode = cur.children.get(0);
			int maxScore = maxNode.score.getWhiteScore();
			for (Node child : cur.children) {
				if (child.score.getWhiteScore() > maxScore) {
					maxScore = child.score.getWhiteScore();
					maxNode = child;
				}
			}
			return maxNode;
		} else {
			Node maxNode = cur.children.get(0);
			int maxScore = maxNode.score.getBlackScore();
			for (Node child : cur.children) {
				if (child.score.getBlackScore() >maxScore) {
					maxScore = child.score.getBlackScore();
					maxNode = child;
				}
			}
			return maxNode;
		}
	}
}
