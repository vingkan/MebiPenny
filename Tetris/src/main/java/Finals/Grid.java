package Finals;

import java.util.ArrayList;

public class Grid{
	
	ArrayList<Square> board;
	int xSize;
	int ySize;
	
	public Grid(int xSize, int ySize){
		this.board = new ArrayList<Square>();
		this.xSize = xSize;
		this.ySize = ySize;
		for(int y = 0; y < ySize; y++){
			for(int x = 0; x < xSize; x++){
				board.add(new Square(this, x, y, '_'));
			}
		}
	}
	
	public Grid(ArrayList<String> lines){
		this.board = new ArrayList<Square>();
		char[] firstLine = lines.get(0).toCharArray();
		int lineSquares = 0;
		for(char c : firstLine){
			if(c != ','){
				lineSquares++;
			}
		}
		this.xSize = lineSquares;
		this.ySize = lines.size();
		for(int y = 0; y < lines.size(); y++){
			char[] row = lines.get(y).toCharArray();
			int x = 0;
			for(char c : row){
				if(c == 'O'){
					board.add(new Square(this, x, y, c));
				}
				else if(c == ','){
					x++;
				}
				else{
					board.add(new Square(this, x, y, '_'));
				}
			}
		}
	}
	
	public Square getSquare(int x, int y){
		Square response = null;
		for(Square s : board){
			if(s.getX() == x && s.getY() == y){
				response = s;
			}
		}
		return response;
	}
	
	public void addTopRow(){
		setYSize(getYSize() + 1);
		for(int x = 0; x < getXSize(); x++){
			board.add(new Square(this, x, (getYSize() - 1), '_'));
		}
	}
	
	public void addGarbageRow(int emptyXCoord){
		setYSize(getYSize() + 1);
		for(Square s : board){
			s.setY(s.getY() + 1);
		}
		for(int x = 0; x < getXSize(); x++){
			if(x != emptyXCoord){
				board.add(new Square(this, x, 0, 'O'));
			}
			else{
				board.add(new Square(this, x, 0, '_'));
			}
		}
	}

	@Override
	public String toString() {
		String grid = "";
		int counter = 0;
		for(Square s : board){
			grid += s.getColor();
			counter++;
			if(counter == xSize){
				grid += "\n";
				counter = 0;
			}
			else{
				grid += ",";
			}
		}
		return grid;
	}

	public ArrayList<Square> getBoard() {
		return board;
	}

	public int getXSize() {
		return xSize;
	}

	public void setXSize(int xSize) {
		this.xSize = xSize;
	}

	public int getYSize() {
		return ySize;
	}

	public void setYSize(int ySize) {
		this.ySize = ySize;
	}
	
}