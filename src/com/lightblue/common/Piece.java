package com.lightblue.common;

public class Piece {

	public boolean hasMoved;

	public enum Color {
		BLACK, WHITE
	};

	public enum State {
		ALIVE, DEAD
	};

	public State mystate;
	public Color mycolor;

	public Piece(Color color) {
		this.hasMoved = false;
		this.mycolor = color;
		this.mystate = Piece.State.ALIVE;
	}

	public String getTextOfColor() {
		return (mycolor.equals(Color.WHITE) ? "w" : "b");
	}

	public static Piece copyPiece(Piece piece) {

		Piece newPiece;
		if (piece.getClass().equals(Pawn.class)) {
			newPiece = new Pawn(piece.mycolor);
		} else if (piece.getClass().equals(Rook.class)) {
			newPiece = new Rook(piece.mycolor);
		} else if (piece.getClass().equals(Knight.class)) {
			newPiece = new Knight(piece.mycolor);
		} else if (piece.getClass().equals(Queen.class)) {
			newPiece = new Queen(piece.mycolor);
		} else if (piece.getClass().equals(Bishop.class)) {
			newPiece = new Bishop(piece.mycolor);
		} else {
			newPiece = new King(piece.mycolor);
		}

		newPiece.hasMoved = piece.hasMoved;
		newPiece.mystate = piece.mystate;

		return newPiece;

	}

	public void die() {
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
