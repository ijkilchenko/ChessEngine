package com.lightblue.common;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

	public final static String text = "R";
	
	public Rook(Color color) {
		super(color);
	}

	public List<Move> getAllMoves(Board board) {
		return getAllMoves(board, this);
	}

	public static List<Move> getAllMoves(Board board, Piece thisPiece) {

		Position pos = board.getPositionOfPiece(thisPiece);

		List<Move> moves = new ArrayList<Move>();
		if (thisPiece.mystate == Piece.State.DEAD) {
			return new ArrayList<Move>();
		}

		// Up
		Position newPos = new Position(pos.getX(), pos.getY() + 1);
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				if (board.getPieceAtPos(newPos).mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					moves.add(move);
					break;
				}
				if (board.getPieceAtPos(newPos).mycolor == thisPiece.mycolor) {
					break;
				}
			}
			newPos = new Position(newPos.getX(), newPos.getY() + 1);
		}

		// Down
		newPos = new Position(pos.getX(), pos.getY() - 1);
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				if (board.getPieceAtPos(newPos).mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					moves.add(move);
					break;
				}
				if (board.getPieceAtPos(newPos).mycolor == thisPiece.mycolor) {
					break;
				}
			}
			newPos = new Position(newPos.getX(), newPos.getY() - 1);
		}

		// Left
		newPos = new Position(pos.getX() - 1, pos.getY());
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				if (board.getPieceAtPos(newPos).mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					moves.add(move);
					break;
				}
				if (board.getPieceAtPos(newPos).mycolor == thisPiece.mycolor) {
					break;
				}
			}
			newPos = new Position(newPos.getX() - 1, newPos.getY());
		}

		// Right
		newPos = new Position(pos.getX() + 1, pos.getY());
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				if (board.getPieceAtPos(newPos).mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					moves.add(move);
					break;
				}
				if (board.getPieceAtPos(newPos).mycolor == thisPiece.mycolor) {
					break;
				}
			}
			newPos = new Position(newPos.getX() + 1, newPos.getY());
		}

		return moves;

	}

}
