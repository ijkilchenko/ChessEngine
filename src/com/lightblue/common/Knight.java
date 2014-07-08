package com.lightblue.common;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	public final static String text = "N";
	
	public Knight(Color color) {
		super(color);
	}

	public List<Move> getAllMoves(Board board) {
		return Knight.getAllMoves(board, this);
	}

	public static List<Move> getAllMoves(Board board, Piece currentPiece) {

		List<Move> moves = new ArrayList<Move>();
		if (currentPiece.mystate == Piece.State.DEAD) {
			return new ArrayList<Move>();
		}

		Position pos = board.getPositionOfPiece(currentPiece);

		for (int i = 0; i < 8; i++) {
			Position newPos;

			switch (i) {
			case 0:
				newPos = new Position(pos.getX() + 1, pos.getY() + 2);
				break;
			case 1:
				newPos = new Position(pos.getX() + 2, pos.getY() + 1);
				break;
			case 2:
				newPos = new Position(pos.getX() - 1, pos.getY() + 2);
				break;
			case 3:
				newPos = new Position(pos.getX() - 2, pos.getY() + 1);
				break;
			case 4:
				newPos = new Position(pos.getX() - 1, pos.getY() - 2);
				break;
			case 5:
				newPos = new Position(pos.getX() - 2, pos.getY() - 1);
				break;
			case 6:
				newPos = new Position(pos.getX() + 1, pos.getY() - 2);
				break;
			default:
				newPos = new Position(pos.getX() + 2, pos.getY() - 1);
				break;
			}

			if (!board.isInside(newPos)) {
				continue;
			}

			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(currentPiece, pos, newPos);
				moves.add(move);
			} else if (board.getPieceAtPos(newPos).mycolor != currentPiece.mycolor) {
				Move move = new Move(currentPiece, pos, newPos);
				moves.add(move);
			}

		}

		return moves;

	}

}
