package com.lightblue;

public class Tuple {
	
	public final Position pos;
	public final Piece piece;

	public Tuple(Position pos, Piece piece) {
		this.pos = pos;
		this.piece = piece;
	}

	public Position getPos() {
		return pos;
	}

	public Piece getPiece() {
		return piece;
	}
	
}