package com.lightblue.common;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

	public final static String text = "B";

	public Bishop(Color color) {
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

		// Up-Right
		int startCount = 0;
		Position newPos = new Position(pos.getX() + 1, pos.getY() + 1);
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				Piece pieceAtFinish = board.getPieceAtPos(newPos);

				if (pieceAtFinish.mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					move.setPieceAttacked(pieceAtFinish);
					moves.add(move);
					break;
				}
				if (pieceAtFinish.mycolor == thisPiece.mycolor) {
					for (int i = startCount; i < moves.size(); i++) {
						moves.get(i).setPieceDefending(pieceAtFinish);
					}
					break;
				}
			}
			newPos = new Position(newPos.getX() + 1, newPos.getY() + 1);
		}

		// Down-Right
		startCount = moves.size();
		newPos = new Position(pos.getX() + 1, pos.getY() - 1);
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				Piece pieceAtFinish = board.getPieceAtPos(newPos);

				if (pieceAtFinish.mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					move.setPieceAttacked(pieceAtFinish);
					moves.add(move);
					break;
				}
				if (pieceAtFinish.mycolor == thisPiece.mycolor) {
					for (int i = startCount; i < moves.size(); i++) {
						moves.get(i).setPieceDefending(pieceAtFinish);
					}
					break;
				}
			}
			newPos = new Position(newPos.getX() + 1, newPos.getY() - 1);
		}

		// Left-Up
		startCount = moves.size();
		newPos = new Position(pos.getX() - 1, pos.getY() + 1);
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				Piece pieceAtFinish = board.getPieceAtPos(newPos);

				if (pieceAtFinish.mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					move.setPieceAttacked(pieceAtFinish);
					moves.add(move);
					break;
				}
				if (pieceAtFinish.mycolor == thisPiece.mycolor) {
					for (int i = startCount; i < moves.size(); i++) {
						moves.get(i).setPieceDefending(pieceAtFinish);
					}
					break;
				}
			}
			newPos = new Position(newPos.getX() - 1, newPos.getY() + 1);
		}

		// Left-Down
		startCount = moves.size();
		newPos = new Position(pos.getX() - 1, pos.getY() - 1);
		while (board.isInside(newPos)) {
			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(thisPiece, pos, newPos);
				moves.add(move);
			} else {
				Piece pieceAtFinish = board.getPieceAtPos(newPos);

				if (pieceAtFinish.mycolor != thisPiece.mycolor) {
					Move move = new Move(thisPiece, pos, newPos);
					move.setPieceAttacked(pieceAtFinish);
					moves.add(move);
					break;
				}
				if (pieceAtFinish.mycolor == thisPiece.mycolor) {
					for (int i = startCount; i < moves.size(); i++) {
						moves.get(i).setPieceDefending(pieceAtFinish);
					}
					break;
				}
			}
			newPos = new Position(newPos.getX() - 1, newPos.getY() - 1);
		}

		return moves;

	}

}
