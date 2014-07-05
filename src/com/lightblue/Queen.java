package com.lightblue;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
	}

	public List<Move> getAllMoves(Board board) {
		return Queen.getAllMoves(board, this);
	}

	public static List<Move> getAllMoves(Board board, Piece currentPiece) {

		List<Move> moves = new ArrayList<Move>();
		if (currentPiece.mystate == Piece.State.DEAD) {
			return null;
		}

		moves.addAll(Rook.getAllMoves(board, currentPiece));
		moves.addAll(Bishop.getAllMoves(board, currentPiece));

		return moves;
	}

}
