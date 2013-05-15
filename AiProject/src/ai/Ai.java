package ai;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Color;
import game.Game;
import game.Move;
import game.Score;

public class Ai {
	public int totalNodesSearched = 0;
	public int maxNodesSearched = 0;
	public int nodesSearched = 0;
	int depthLimit_m;
	Color color_m;
	Algorithms algorithm_m;

	public void stats(int numMoves){
		totalNodesSearched += nodesSearched;//add number of nodes searched i the turn to the AI total
		if(nodesSearched > maxNodesSearched)
			maxNodesSearched = nodesSearched;
		System.out.println("========" + this.color_m + "===========\n"
							+"Nodes searched this turn: " + this.nodesSearched
							+"\nTotal Nodes Searched: " + totalNodesSearched
							+ "\nNumber of Moves: " + numMoves
							+ "\nMax nodes Searched on a turn: " + maxNodesSearched
							+ "\nAverage Nodes Searched: " + totalNodesSearched / numMoves);
		
	}
	
	public Ai(int depthLimit, Color c, Algorithms a) {
		depthLimit_m = depthLimit;
		color_m = c;
		algorithm_m = a;
	}

	// test comment
	public Move MakeMove() {
		Move current = new Move();
		return current;
	}

	public static ArrayList<Move> getPossibleMoves(Board b, Color c) {
		// Board temp = b;
		// System.out.print("Possible Moves: \n");
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

	public void GreedyBFSMove(Board b) {
		ArrayList<Move> moves = getPossibleMoves(b, color_m);
		this.nodesSearched = moves.size();
		this.totalNodesSearched += this.nodesSearched;
		if(moves.size() > this.maxNodesSearched)
			this.maxNodesSearched = moves.size();
		Move movetomake = new Move();
		movetomake.column = 4;
		movetomake.row = 4;
		int currenthigh = 0;
		for (Move m : moves) {
			Board temp = new Board(b);
			int k = m.row + 1;
			int x = m.column + 1;
			temp.addDisk(color_m, m.row, m.column);
			if (color_m == Color.WHITE) {
				System.out.print(color_m + " " + k + " " + x + "\n"
						+ temp.getScoreOfBoard().getWhiteScore() + "\n");
				if (temp.getScoreOfBoard().getWhiteScore() > currenthigh) {
					currenthigh = temp.getScoreOfBoard().getWhiteScore();
					movetomake = m;
				}
			} else {
				System.out.print(color_m + " " + k + " " + x + "\n"
						+ temp.getScoreOfBoard().getBlackScore() + "\n");
				if (temp.getScoreOfBoard().getBlackScore() > currenthigh) {
					currenthigh = temp.getScoreOfBoard().getBlackScore();
					movetomake = m;
				}
			}
		}
		b.addDisk(color_m, movetomake.row, movetomake.column);
	}

	public void RandomGreedyMove(Board b) {
		ArrayList<Move> moves = getPossibleMoves(b, color_m);
		List<Move> possibleMoves = new ArrayList<Move>();
		int highestScore = Integer.MIN_VALUE;
		for (Move m : moves) {
			Board temp = new Board(b);
			temp.addDisk(color_m, m.row, m.column);
			if (color_m == Color.WHITE) {
				if (temp.getScoreOfBoard().getWhiteScore() > highestScore) {
					highestScore = temp.getScoreOfBoard().getWhiteScore();
					possibleMoves.clear();
					possibleMoves.add(m);
				} else if (temp.getScoreOfBoard().getWhiteScore() == highestScore) {
					possibleMoves.add(m);
				}
			} else {
				if (temp.getScoreOfBoard().getBlackScore() > highestScore) {
					highestScore = temp.getScoreOfBoard().getBlackScore();
					possibleMoves.clear();
					possibleMoves.add(m);
				} else if (temp.getScoreOfBoard().getBlackScore() == highestScore) {
					possibleMoves.add(m);
				}
			}
		}
		if (possibleMoves.size() > 0) {
			Double index = Math.random() * possibleMoves.size();
			Move bestMove = possibleMoves.get(index.intValue());
			b.addDisk(color_m, bestMove.row, bestMove.column);
		}
	}

	public Node buildMoveTree(Board b, Color c, int depth) {
		if (depth > depthLimit_m)
			return null;
		Node head = new Node();
		head.board = b;

		Color other;
		if (c == Color.WHITE)
			other = Color.BLACK;
		else
			other = Color.WHITE;

		ArrayList<Move> moves = getPossibleMoves(b, c);
		head.children = new ArrayList<Node>();
		int num_childs = 0;
		for (Move m : moves) {
			Board childBoard = new Board(b);
			childBoard.addDisk(c, m.row, m.column);
			Node child = buildMoveTree(childBoard, other, depth + 1);
			if (child != null) {
				num_childs += child.numChildren;
				child.move = m;
				head.children.add(child);
			}
		}
		head.numChildren = head.children.size() + num_childs;

		return head;
	}

	public void MinimaxMove(Board b) {

		Node moveTree = buildMoveTree(b, color_m, 0);
		Minimax(moveTree, color_m);
		Node maxNode = null;
		for (Node child : moveTree.children) {
			if (maxNode == null) {
				maxNode = child;
			} else {
				if (color_m == Color.WHITE) {
					if (child.score.getWhiteScore() > maxNode.score
							.getWhiteScore()) {
						maxNode = child;
					}
				} else {
					if (child.score.getBlackScore() > maxNode.score
							.getBlackScore()) {
						maxNode = child;
					}
				}
			}
		}
		if (maxNode != null) {
			if (color_m == Color.WHITE) {
				System.out.print(color_m + " " + (maxNode.move.row + 1) + " "
						+ (maxNode.move.column + 1) + "\n"
						+ maxNode.board.getScoreOfBoard().getWhiteScore()
						+ "\n");
			} else {
				System.out.print(color_m + " " + (maxNode.move.row + 1) + " "
						+ (maxNode.move.column + 1) + "\n"
						+ maxNode.board.getScoreOfBoard().getBlackScore()
						+ "\n");
			}
			System.out.println("Num Searched: "
					+ Integer.toString(moveTree.numChildren));
			this.nodesSearched = moveTree.numChildren;
			b.addDisk(color_m, maxNode.move.row, maxNode.move.column);
		}

	}

	public void Minimax(Node cur, Color c) {
		if (cur.children.isEmpty()) {
			cur.score = cur.board.getScoreOfBoard();
			return;
		}
		Color other;
		if (c == Color.WHITE)
			other = Color.BLACK;
		else
			other = Color.WHITE;

		Score maxScore = null;
		for (Node child : cur.children) {
			Minimax(child, other);
			if (maxScore == null) {
				maxScore = child.score;
			}
			if (c == Color.WHITE) {
				if (child.score.getWhiteScore() > maxScore.getWhiteScore()) {
					maxScore = child.score;
				}
			} else {
				if (child.score.getBlackScore() > maxScore.getBlackScore()) {
					maxScore = child.score;
				}
			}
		}
		cur.score = maxScore;
	}

	public void MakeMove(Board b) {
		switch (algorithm_m) {
		case MINIMAX:
			MinimaxMove(b);
			break;
		case GREEDY:
			GreedyBFSMove(b);
			break;
		case RANDOMGREEDY:
			RandomGreedyMove(b);
			break;
		default:
			break;
		}
	}
}
