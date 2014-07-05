package com.lightblue;

import java.util.HashMap;

public class EnPassant extends Move {

	public EnPassant(Pawn pawn, Position pos, Position newPos, Piece adjacentPiece, Position adjacentPos) {
		
		map = new HashMap<Tuple, Tuple>();
		addMove(pawn, pos, newPos);
		addMove(adjacentPiece, adjacentPos, null);
		
	}
	
}
