package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	public int numMovesMade = 4;

	private class Location {
		int row;
		int column;
		Location(int row, int column){
			this.row = row;
			this.column = column;
		}
	}
	
	static final int boardSize = 8;
	
	static final int diskSize = 32;
	
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
		board[3][4] = Color.BLACK;
		board[4][3] = Color.BLACK;
		board[3][3] = Color.WHITE;
		board[4][4] = Color.WHITE;
		
		graphicsInit();
	}
	
	public Board(Board oldBoard){
		for(int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				board[i][j] = oldBoard.board[i][j];
			}
		}
		graphicsInit();
	}
	
	public void graphicsInit(){
		/*JFrame frame = new JFrame("Reversi");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(12*diskSize, 16*diskSize);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);*/
	}

	public boolean colorEquals(Color color1, Color color2){
		if (color1 == color2){
			return true;
		}
		else return false;
	}
	
	public boolean isEmpty(Color color){
		if (color == Color.EMPTY){
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
	
	public boolean isMoveLegal(Color color, int row, int column){
		boolean surrounding = false;
		//check up
		for(int i = row - 1; i >= 0; i--){
			if(isEmpty(board[i][column])){
				break;
			}
			else if(!colorEquals(board[i][column],color)){ // other persons disk
				surrounding = true;
			}
			else { //same color disk
				if(surrounding){ //surrounds other players disks
					return true;
				}
				else break;
			}
		}
		//check up, right
		int row_t = row - 1;
		int col_t = column + 1;
		surrounding = false;
		while(row_t >= 0 && col_t < boardSize){
			if(isEmpty(board[row_t][col_t])){
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				surrounding = true;
			}
			else {
				if(surrounding){
					return true;
				}
				else break;
			}
			row_t = row_t - 1;
			col_t = col_t + 1;
		}
		//check right
		surrounding = false;
		for(int i = column + 1; i < boardSize; i++){
			if(isEmpty(board[row][i])){
				break;
			}
			else if(!colorEquals(board[row][i],color)){ // other persons disk
				surrounding = true;
			}
			else { //same color disk
				if(surrounding){ //surrounds other players disks
					return true;
				}
				else break;
			}
		}
		//check down, right
		row_t = row + 1;
		col_t = column + 1;
		surrounding = false;
		while(row_t < boardSize && col_t < boardSize){
			if(isEmpty(board[row_t][col_t])){
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				surrounding = true;
			}
			else {
				if(surrounding){
					return true;
				}
				else break;
			}
			row_t = row_t + 1;
			col_t = col_t + 1;
		}
		//check down
		surrounding = false;
		for(int i = row + 1; i < boardSize; i++){
			if(isEmpty(board[i][column])){
				break;
			}
			else if(!colorEquals(board[i][column],color)){ // other persons disk
				surrounding = true;
				continue;
			}
			else { //same color disk
				if(surrounding){ //surrounds other players disks
					return true;
				}
				else break;
			}
		}
		//check down, left
		row_t = row + 1;
		col_t = column - 1;
		surrounding = false;
		while(row_t < boardSize && col_t >= 0){
			if(isEmpty(board[row_t][col_t])){
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				surrounding = true;
			}
			else {
				if(surrounding){
					return true;
				}
				else break;
			}
			row_t = row_t + 1;
			col_t = col_t - 1;
		}
		//check left
		surrounding = false;
		for(int i = column - 1; i >= 0; i--){
			if(isEmpty(board[row][i])){
				break;
			}
			else if(!colorEquals(board[row][i],color)){ // other persons disk
				surrounding = true;
			}
			else { //same color disk
				if(surrounding){ //surrounds other players disks
					return true;
				}
				else break;
			}
		}
		//check up, left
		row_t = row - 1;
		col_t = column - 1;
		surrounding = false;
		while(row_t >= 0 && col_t >= 0){
			if(isEmpty(board[row_t][col_t])){
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				surrounding = true;
			}
			else {
				if(surrounding){
					return true;
				}
				else break;
			}
			row_t = row_t - 1;
			col_t = col_t - 1;
		}
		return false;
	}
	
	
	public void addDisk(Color color, int row, int column){
		if (board[row][column] == Color.EMPTY){
			if (isMoveLegal(color, row, column)){
				board[row][column] = color;
				++this.numMovesMade;
			}
			else {
				//System.out.println("Error: illegal move");
				return; 
			}
			//check for move not illegal
			//if illegal throw exception
		}
		else {
			//System.out.println("Error: illegal move");
			return;
		}
		
		List<Location> toFlip = new LinkedList<Location>();
		boolean seenSameColor = false;
		//flip pieces
		//check up
		for(int i = row-1; i >= 0; i--){
			if(isEmpty(board[i][column])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[i][column],color)){
				toFlip.add(new Location(i,column));
			}
			else { // board[row][i].colorEquals(color) == true
				seenSameColor = true;
				break;
			}
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		
		//check right, up diagonal
		int row_t = row - 1;
		int col_t = column + 1;
		while(row_t >= 0 && col_t < boardSize){
			if(isEmpty(board[row_t][col_t])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				toFlip.add(new Location(row_t, col_t));
			}
			else {
				seenSameColor = true;
				break;
			}
			row_t = row_t - 1;
			col_t = col_t + 1;
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		
		//check right
		for(int i = column+1; i < boardSize; i++){
			if(isEmpty(board[row][i])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[row][i],color)){
				toFlip.add(new Location(row, i));
			}
			else { // board[row][i].colorEquals(color) == true
				seenSameColor = true;
				break;
			}
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		toFlip.clear();
		//check down, right diagonal
		row_t = row + 1;
		col_t = column + 1;
		while(row_t < boardSize && col_t < boardSize){
			if(isEmpty(board[row_t][col_t])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				toFlip.add(new Location(row_t, col_t));
			}
			else {
				seenSameColor = true;
				break;
			}
			row_t = row_t + 1;
			col_t = col_t + 1;
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		
		//check down
		for(int i = row+1; i < boardSize; i++){
			if(isEmpty(board[i][column])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[i][column],color)){
				toFlip.add(new Location(i, column));
			}
			else { // board[row][i].colorEquals(color) == true
				seenSameColor = true;
				break;
			}
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		
		//check down, left diagonal
		row_t = row + 1;
		col_t = column - 1;
		while(row_t < boardSize && col_t >= 0){
			if(isEmpty(board[row_t][col_t])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				toFlip.add(new Location(row_t, col_t));
			}
			else {
				seenSameColor = true;
				break;
			}
			row_t = row_t + 1;
			col_t = col_t - 1;
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		
		//check left
		for(int i = column-1; i >= 0; i--){
			if(isEmpty(board[row][i])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[row][i],color)){
				toFlip.add(new Location(row, i));
			}
			else { // board[row][i].colorEquals(color) == true
				seenSameColor = true;
				break;
			}
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		
		//check up, left diagonal
		row_t = row - 1;
		col_t = column - 1;
		while(row_t >= 0 && col_t >= 0){
			if(isEmpty(board[row_t][col_t])){
				toFlip.clear();
				break;
			}
			else if(!colorEquals(board[row_t][col_t],color)){
				toFlip.add(new Location(row_t, col_t));
			}
			else {
				seenSameColor = true;
				break;
			}
			row_t = row_t - 1;
			col_t = col_t - 1;
		}
		if(seenSameColor)
		{
			for(Location l : toFlip){
				flipDisk(l.row, l.column);
			}
		}
		toFlip.clear();
		seenSameColor = false;
		
	}
	
	public void displayBoard(){
		String toPrint = "";
		System.out.println("   1 2 3 4 5 6 7 8");
		for(int i = 0; i < boardSize; i++){
			toPrint += Integer.toString(i+1) + "| ";
			for(int j = 0; j < boardSize; j++){
				switch (board[i][j]){
					case BLACK:
						toPrint += "X ";
						break;
					case WHITE:
						toPrint += "O ";
						break;
					case EMPTY:
						toPrint += "- ";
						break;						
				}
			}
			toPrint += "|";
			System.out.println(toPrint);
			toPrint = "";
		}
		
		System.out.println();
		//repaint();

	}
	
	public Score getScoreOfBoard(){
		int whiteCount = 0;
		int blackCount = 0;
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				switch(board[i][j]){
					case BLACK:
						blackCount++;
						break;
					case WHITE:
						whiteCount++;
						break;
					default:
						break;
				}
			}
		}
		return new Score(blackCount, whiteCount);
	}
	
	public void paint(Graphics g){
		Graphics g2d = (Graphics2D) g; 
		//g2d.drawString("Hello", 50, 50);
		//g2d.fillOval(250, 250, diskSize, diskSize);
		java.awt.Color white = java.awt.Color.WHITE;
		java.awt.Color black = java.awt.Color.BLACK;
		int initial_x = 50;
		int initial_y = 50;
		g2d.setColor(black);
		for(int i = 0; i <= boardSize; i++){
			g2d.drawLine(initial_x + i*diskSize, initial_y, initial_x + i*diskSize, initial_y + 8*diskSize);
			if (i < boardSize)
				g2d.drawString(Integer.toString(i+1), initial_x + i*diskSize + diskSize/2, initial_y - diskSize/4);
		}
		for(int i = 0; i <= boardSize; i++){
			g2d.drawLine(initial_x, initial_y + i*diskSize, initial_x + 8*diskSize, initial_y + i*diskSize);
			if (i < boardSize)
				g2d.drawString(Integer.toString(i+1), initial_x-diskSize/2, initial_y + i*diskSize+diskSize*3/4);
		}

		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				switch(board[j][i]){
					case BLACK:
						g2d.setColor(black);
						g2d.fillOval(initial_x+(i*diskSize)+1, initial_y+(j*diskSize)+1, diskSize-2, diskSize-2);
						break;
					case WHITE:
						g2d.setColor(white);
						g2d.fillOval(initial_x+(i*diskSize)+1, initial_y+(j*diskSize)+1, diskSize-2, diskSize-2);
						break;
					default:
						break;
				}
			}
		}
	}
	
}
