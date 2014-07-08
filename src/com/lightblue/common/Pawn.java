package com.lightblue.common;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
	
	public final static String text = "P";

	public Pawn(Color color) {
		super(color);
	}

	public List<Move> getAllMoves(Board board) {
		return getAllMoves(board, this);
	}

	public static List<Move> getAllMoves(Board board, Piece currentPiece) {
		List<Move> moves = new ArrayList<Move>();
		if (currentPiece.mystate == Piece.State.DEAD) {
			return new ArrayList<Move>();
		}
		Position pos = board.getPositionOfPiece(currentPiece);

		// Move ahead
		if (currentPiece.mycolor == Piece.Color.WHITE) {
			Position newPos = new Position(pos.getX(), pos.getY() + 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				if (newPos.getY() == 7) {
					// Become queen
					moves.add(new Move(currentPiece, pos, new Queen(Piece.Color.WHITE), newPos));
				} else {
					moves.add(new Move(currentPiece, pos, newPos));
				}
			}
		} else {
			Position newPos = new Position(pos.getX(), pos.getY() - 1);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				if (newPos.getY() == 0) {
					// Become queen
					moves.add(new Move(currentPiece, pos, new Queen(Piece.Color.BLACK), newPos));
				} else {
					moves.add(new Move(currentPiece, pos, newPos));
				}
			}
		}

		// BigStep
		if (moves.size() != 0 && currentPiece.mycolor == Piece.Color.WHITE && pos.getY() == 1) {
			Position newPos = new Position(pos.getX(), pos.getY() + 2);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				moves.add(new Move(currentPiece, pos, newPos));
			}
		} else if (moves.size() != 0 && currentPiece.mycolor == Piece.Color.BLACK && pos.getY() == 6) {
			Position newPos = new Position(pos.getX(), pos.getY() - 2);
			if (board.isInside(newPos) && board.getPieceAtPos(newPos) == null) {
				moves.add(new Move(currentPiece, pos, newPos));
			}
		}

		// Diagonally (capture)
		if (currentPiece.mycolor == Piece.Color.WHITE) {
			Position newPos = new Position(pos.getX() + 1, pos.getY() + 1);
			if (board.isInside(newPos)) {

				Position adjacentPos = new Position(pos.getX() + 1, pos.getY());
				int currentNumOfMoves = moves.size();
				moves = doEnPassantIfPossible(board, currentPiece, moves, pos, newPos, adjacentPos);
				if (moves.size() == currentNumOfMoves && board.getPieceAtPos(newPos) != null
						&& board.getPieceAtPos(newPos).mycolor == Piece.Color.BLACK) {
					moves.add(new Move(currentPiece, pos, newPos));
				}
			}
			newPos = new Position(pos.getX() - 1, pos.getY() + 1);
			if (board.isInside(newPos)) {
				Position adjacentPos = new Position(pos.getX() - 1, pos.getY());
				int currentNumOfMoves = moves.size();
				moves = doEnPassantIfPossible(board, currentPiece, moves, pos, newPos, adjacentPos);
				if (moves.size() == currentNumOfMoves && board.getPieceAtPos(newPos) != null
						&& board.getPieceAtPos(newPos).mycolor == Piece.Color.BLACK) {
					moves.add(new Move(currentPiece, pos, newPos));
				}
			}
		} else if (currentPiece.mycolor == Piece.Color.BLACK) {
			Position newPos = new Position(pos.getX() + 1, pos.getY() - 1);
			if (board.isInside(newPos)) {
				Position adjacentPos = new Position(pos.getX() + 1, pos.getY());
				int currentNumOfMoves = moves.size();
				moves = doEnPassantIfPossible(board, currentPiece, moves, pos, newPos, adjacentPos);
				if (moves.size() == currentNumOfMoves && board.getPieceAtPos(newPos) != null
						&& board.getPieceAtPos(newPos).mycolor == Piece.Color.WHITE) {
					moves.add(new Move(currentPiece, pos, newPos));
				}
			}
			newPos = new Position(pos.getX() - 1, pos.getY() - 1);
			if (board.isInside(newPos)) {
				Position adjacentPos = new Position(pos.getX() - 1, pos.getY());
				int currentNumOfMoves = moves.size();
				moves = doEnPassantIfPossible(board, currentPiece, moves, pos, newPos, adjacentPos);
				if (moves.size() == currentNumOfMoves && board.getPieceAtPos(newPos) != null
						&& board.getPieceAtPos(newPos).mycolor == Piece.Color.WHITE) {
					moves.add(new Move(currentPiece, pos, newPos));
				}
			}
		}

		return moves;
	}

	private static List<Move> doEnPassantIfPossible(Board board, Piece currentPiece, List<Move> moves, Position pos,
			Position newPos, Position adjacentPos) {
		Color myPawnColor = currentPiece.mycolor;
		Piece adjacentPiece = board.getPieceAtPos(adjacentPos);
		if (adjacentPiece != null && adjacentPiece.mycolor != myPawnColor && adjacentPiece.getClass() == Pawn.class) {
			Pawn adjacentPawn = (Pawn) adjacentPiece;
			if (board.isLastMoveBigStep != null && board.isLastMoveBigStep == adjacentPawn) {
				moves.add(new EnPassant((Pawn) currentPiece, pos, newPos, adjacentPiece, adjacentPos));
			}
		}

		return moves;
	}

}
