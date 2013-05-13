package ai;

import java.util.ArrayList;

import game.Board;
import game.Move;
import game.Score;

public class Node {
	public Move move;
	public Board board;
	public ArrayList<Node> children;
	public Score score;
	public int numChildren;
	
}
