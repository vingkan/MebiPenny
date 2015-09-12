package FantasticFour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String line;
		ArrayList<String> lines = new ArrayList<String>();

		try {
			while ((line = bi.readLine()) != null) {
				lines.add(line);
			}
		} catch(IOException e) { }

		/*lines.add("B,B,R,_,R");
		lines.add("B,_,R,_,R");
		lines.add("_,B,B,B,B");
		lines.add("B,_,R,_,_");
		lines.add("B,B,R,_,R");*/
		
		Grid grid = new Grid(lines);
		//System.out.println(grid);
		grid.checkForWin();
		
		/*for (String l : lines) {
			System.out.println(processLine(l));
		}*/
		
	}

	static String processLine(String line) {
		
		return line;
	}
	
	public static class Grid{
		
		ArrayList<Square> paper;
		int size;
		
		public Grid(int size){
			this.paper = new ArrayList<Square>();
			this.size = size;
			for(int y = 0; y < size; y++){
				for(int x = 0; x < size; x++){
					paper.add(new Square(this, x, y, '_'));
				}
			}
		}
		
		public Grid(ArrayList<String> lines){
			this.paper = new ArrayList<Square>();
			this.size = lines.size();
			for(int y = 0; y < lines.size(); y++){
				char[] row = lines.get(y).toCharArray();
				int x = 0;
				for(char c : row){
					if(c == 'B' || c == 'R'){
						paper.add(new Square(this, x, y, c));
					}
					else if(c == ','){
						x++;
					}
					else{
						paper.add(new Square(this, x, y, '_'));
					}
				}
			}
		}
		
		private Square getSquare(int x, int y){
			Square response = null;
			for(Square s : paper){
				if(s.getX() == x && s.getY() == y){
					response = s;
				}
			}
			return response;
		}
		
		private void checkForWin(){
			ArrayList<String> wins = new ArrayList<String>();
			ArrayList<String> coords = new ArrayList<String>();
			int blank = 0;
			for(Square s : paper){
				if(s.getColor() == '_'){
					blank++;
				}
				else if(checkSquare(s, false)){
					wins.add("Win_" + s.getColor());
					coords.add(s.getCoord());
				}
			}
			if(wins.size() > 1){
				System.out.println("Invalid");
				for(String coord : coords){
					System.out.println(coord);
				}
			}
			else if(wins.size() == 1){
				System.out.println(wins.get(0));
				System.out.println(coords.get(0));
			}
			else{
				boolean unsettled = false;
				for(Square s : paper){
					if(checkSquare(s, true)){
						unsettled = true;
						System.out.println("Unsettled");
						System.out.println(blank);
						break;
					}
				}
				if(!unsettled){
					System.out.println("Draw");
					System.out.println(blank);
				}
			}
			//System.out.println("Finished");
		}
		
		private boolean checkSquare(Square s, boolean winnable){
			boolean found = false;
			String[] directions = {"BOTTOM", "RIGHT",
					"BOTTOMRIGHT", "BOTTOMLEFT"};
			for(String direction : directions){
				int matches = s.check(direction, winnable);
				if(matches >= 3){
					found = true;
				}
			}
			return found;
		}

		@Override
		public String toString() {
			String grid = "";
			int counter = 0;
			for(Square s : paper){
				grid += s.getColor();
				counter++;
				if(counter == size){
					grid += "\n";
					counter = 0;
				}
				else{
					grid += ",";
				}
			}
			return grid;
		}

		public ArrayList<Square> getPaper() {
			return paper;
		}

		public int getSize() {
			return size;
		}
		
	}
	
	public static class Square{
		
		Grid grid;
		int x;
		int y;
		char color;
		
		public Square(Grid grid, int x, int y, char color){
			this.grid = grid;
			this.x = x;
			this.y = y;
			this.color = color;
		}
		
		public String getCoord(){
			return "[" + y + "," + x + "]";
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public char getColor() {
			return color;
		}

		public void setColor(char color) {
			this.color = color;
		}

		public Grid getGrid() {
			return grid;
		}
		
		private boolean inBounds(int coord){
			boolean inside = false;
			if(coord >= 0 && coord < grid.getSize()){
				inside = true;
			}
			return inside;
		}
		
		public Square getTop(){
			Square s = null;
			int newY = y - 1;
			if(inBounds(newY)){
				s = grid.getSquare(x, newY);
			}
			return s;
		}
		
		public Square getBottom(){
			Square s = null;
			int newY = y + 1;
			if(inBounds(newY)){
				s = grid.getSquare(x, newY);
			}
			return s;
		}
		
		public Square getRight(){
			Square s = null;
			int newX = x + 1;
			if(inBounds(newX)){
				s = grid.getSquare(newX, y);
			}
			return s;
		}

		public Square getLeft(){
			Square s = null;
			int newX = x - 1;
			if(inBounds(newX)){
				s = grid.getSquare(newX, y);
			}
			return s;
		}
		
		public Square getTopRight(){
			Square s = null;
			int newX = x + 1;
			int newY = y - 1;
			if(inBounds(newX) && inBounds(newY)){
				s = grid.getSquare(newX, newY);
			}
			return s;
		}
		
		public Square getTopLeft(){
			Square s = null;
			int newX = x - 1;
			int newY = y - 1;
			if(inBounds(newX) && inBounds(newY)){
				s = grid.getSquare(newX, newY);
			}
			return s;
		}
		
		public Square getBottomRight(){
			Square s = null;
			int newX = x + 1;
			int newY = y + 1;
			if(inBounds(newX) && inBounds(newY)){
				s = grid.getSquare(newX, newY);
			}
			return s;
		}
		
		public Square getBottomLeft(){
			Square s = null;
			int newX = x - 1;
			int newY = y + 1;
			if(inBounds(newX) && inBounds(newY)){
				s = grid.getSquare(newX, newY);
			}
			return s;
		}
		
		public Square get(String direction){
			Square s = null;
			switch(direction){
				case "TOP":
					s = getTop();
					break;
				case "BOTTOM":
					s = getBottom();
					break;
				case "RIGHT":
					s = getRight();
					break;
				case "LEFT":
					s = getLeft();
					break;
				case "TOPRIGHT":
					s = getTopRight();
					break;
				case "TOPLEFT":
					s = getTopLeft();
					break;
				case "BOTTOMRIGHT":
					s = getBottomRight();
					break;
				case "BOTTOMLEFT":
					s = getBottomLeft();
					break;
			}
			return s;
		}
		
		public int check(String direction, boolean winnable){
			int match = 0;
			Square p = get(direction);
			if(p != null){
				if(getColor() == p.getColor()){
					match += 1;
					match += p.check(direction, winnable);
				}
				else if(winnable && p.getColor() == '_'){
					match += 1;
					match += p.check(direction, winnable);
				}
			}
			return match;
		}
		
	}
}
