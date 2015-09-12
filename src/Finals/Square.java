package Finals;

public class Square{
	
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
