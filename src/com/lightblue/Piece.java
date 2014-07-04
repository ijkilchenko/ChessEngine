package com.lightblue;

public class Piece {
	
	private boolean hasMoved; 
	public enum Color {BLACK, WHITE};
	public enum State {ALIVE, DEAD};	
	public State mystate;
	public Color mycolor; 
	
	public Piece (Color color){
		this.hasMoved = false;
		this.mycolor = color;
		this.mystate = Piece.State.ALIVE;
	}
	
	public void die(){
		mystate = Piece.State.DEAD;
	}
	public boolean isHasMoved() {
		return hasMoved;
	}
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	public State getMystate() {
		return mystate;
	}
	public void setMystate(State mystate) {
		this.mystate = mystate;
	}
	public Color getMycolor() {
		return mycolor;
	}
	public void setMycolor(Color mycolor) {
		this.mycolor = mycolor;
	}	

}
