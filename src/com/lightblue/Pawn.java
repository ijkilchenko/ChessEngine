package com.lightblue;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	public boolean isLastMoveBigStep = false;

	public Pawn(Color color) {
		super(color);
	}

	public List<Move> getAllMoves(Board board) {
		List<Move> moves = new ArrayList<Move>();
		if (this.mystate == Piece.State.DEAD) {
			return null;
		}
		Position pos = board.getPositionOfPiece(this);

		// Move ahead
		if (this.mycolor == Piece.Color.WHITE) {
			Position newPos = new Position(pos.getX(), pos.getY() + 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				moves.add(new Move(this, pos, newPos));
			}
		} else {
			Position newPos = new Position(pos.getX(), pos.getY() - 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				moves.add(new Move(this, pos, newPos));
			}
		}

		// BigStep
		if (moves.size() != 0 && this.mycolor == Piece.Color.WHITE
				&& pos.getY() == 1) {
			Position newPos = new Position(pos.getX(), pos.getY() + 2);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				moves.add(new Move(this, pos, newPos));
			}
		} else if (moves.size() != 0 && this.mycolor == Piece.Color.BLACK
				&& pos.getY() == 6) {
			Position newPos = new Position(pos.getX(), pos.getY() - 2);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				moves.add(new Move(this, pos, newPos));
			}
		}

		// Diagonally (capture)
		if (this.mycolor == Piece.Color.WHITE) {
			Position newPos = new Position(pos.getX() + 1, pos.getY() + 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) != null
					&& board.getPieceAtPos(newPos).mycolor == Piece.Color.BLACK) {
				moves.add(new Move(this, pos, newPos));
			}
			newPos = new Position(pos.getX() - 1, pos.getY() + 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) != null
					&& board.getPieceAtPos(newPos).mycolor == Piece.Color.BLACK) {
				moves.add(new Move(this, pos, newPos));
			}
		} else if (this.mycolor == Piece.Color.BLACK) {
			Position newPos = new Position(pos.getX() + 1, pos.getY() - 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) != null
					&& board.getPieceAtPos(newPos).mycolor == Piece.Color.WHITE) {
				moves.add(new Move(this, pos, newPos));
			}
			newPos = new Position(pos.getX() - 1, pos.getY() - 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) != null
					&& board.getPieceAtPos(newPos).mycolor == Piece.Color.WHITE) {
				moves.add(new Move(this, pos, newPos));
			}
		}

		return moves;
	}

}
