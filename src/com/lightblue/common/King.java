package com.lightblue.common;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	public final static String text = "K";
	
	public King(Color color) {
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

		for (int i = 0; i < 8; i++) {
			Position newPos;

			switch (i) {
			case 0:
				newPos = new Position(pos.getX() + 0, pos.getY() + 1);
				break;
			case 1:
				newPos = new Position(pos.getX() + 1, pos.getY() + 1);
				break;
			case 2:
				newPos = new Position(pos.getX() - 1, pos.getY() + 0);
				break;
			case 3:
				newPos = new Position(pos.getX() - 1, pos.getY() + 1);
				break;
			case 4:
				newPos = new Position(pos.getX() + 0, pos.getY() - 1);
				break;
			case 5:
				newPos = new Position(pos.getX() - 1, pos.getY() - 1);
				break;
			case 6:
				newPos = new Position(pos.getX() + 1, pos.getY() + 0);
				break;
			default:
				newPos = new Position(pos.getX() + 1, pos.getY() - 1);
				break;
			}

			if (!board.isInside(newPos)) {
				continue;
			}

			if (board.getPieceAtPos(newPos) == null) {
				Move move = new Move(currentPiece, pos, newPos);
				if (!isOtherKingNearby(board, newPos, currentPiece.mycolor)) {
					moves.add(move);
				}
			} else if (board.getPieceAtPos(newPos).mycolor != currentPiece.mycolor) {
				Move move = new Move(currentPiece, pos, newPos);
				if (!isOtherKingNearby(board, newPos, currentPiece.mycolor)) {
					moves.add(move);
				}
			}

		}

		// Castling
		Piece leftWhiteRook = board.getPieceAtPos(new Position(0, 0));
		Piece rightWhiteRook = board.getPieceAtPos(new Position(7, 0));
		Piece leftBlackRook = board.getPieceAtPos(new Position(0, 7));
		Piece rightBlackRook = board.getPieceAtPos(new Position(7, 7));

		if (currentPiece.mycolor == Piece.Color.WHITE) {
			if (currentPiece.hasMoved == false && leftWhiteRook != null && leftWhiteRook.hasMoved == false) {
				if (board.getPieceAtPos(new Position(1, 0)) == null && board.getPieceAtPos(new Position(2, 0)) == null
						&& board.getPieceAtPos(new Position(3, 0)) == null) {
					Move bigStep1 = new Move(currentPiece, pos, new Position(2, 0));
					Move bigStep2 = new Move(currentPiece, pos, new Position(3, 0));
					if (Move.isMoveValid(bigStep1, board, Piece.Color.WHITE)
							&& Move.isMoveValid(bigStep2, board, Piece.Color.WHITE)) {
						Move castleLeft = new Move(currentPiece, pos, new Position(1, 0));
						castleLeft.addMove(leftWhiteRook, board.getPositionOfPiece(leftWhiteRook), new Position(2, 0));
						moves.add(castleLeft);
					}
				}
			}
			if (currentPiece.hasMoved == false && rightWhiteRook != null && rightWhiteRook.hasMoved == false) {
				if (board.getPieceAtPos(new Position(5, 0)) == null && board.getPieceAtPos(new Position(6, 0)) == null) {
					Move bigStep1 = new Move(currentPiece, pos, new Position(5, 0));
					if (Move.isMoveValid(bigStep1, board, Piece.Color.WHITE)) {
						Move castleRight = new Move(currentPiece, pos, new Position(6, 0));
						castleRight.addMove(rightWhiteRook, board.getPositionOfPiece(rightWhiteRook),
								new Position(5, 0));
						moves.add(castleRight);
					}
				}
			}

		} else {
			if (currentPiece.hasMoved == false && leftBlackRook != null && leftBlackRook.hasMoved == false) {
				if (board.getPieceAtPos(new Position(1, 7)) == null && board.getPieceAtPos(new Position(2, 7)) == null
						&& board.getPieceAtPos(new Position(3, 0)) == null) {
					Move bigStep1 = new Move(currentPiece, pos, new Position(2, 7));
					Move bigStep2 = new Move(currentPiece, pos, new Position(3, 7));
					if (Move.isMoveValid(bigStep1, board, Piece.Color.BLACK)
							&& Move.isMoveValid(bigStep2, board, Piece.Color.BLACK)) {
						Move castleLeft = new Move(currentPiece, pos, new Position(1, 7));
						castleLeft.addMove(leftBlackRook, board.getPositionOfPiece(leftBlackRook), new Position(2, 7));
						moves.add(castleLeft);
					}
				}
			}
			if (currentPiece.hasMoved == false && rightBlackRook != null && rightBlackRook.hasMoved == false) {
				if (board.getPieceAtPos(new Position(5, 7)) == null && board.getPieceAtPos(new Position(6, 7)) == null) {
					Move bigStep1 = new Move(currentPiece, pos, new Position(5, 7));
					if (Move.isMoveValid(bigStep1, board, Piece.Color.BLACK)) {
						Move castleRight = new Move(currentPiece, pos, new Position(6, 7));
						castleRight.addMove(rightBlackRook, board.getPositionOfPiece(rightBlackRook),
								new Position(5, 7));
						moves.add(castleRight);
					}
				}
			}

		}
		return moves;
	}

	private static boolean isOtherKingNearby(Board board, Position pos, Color color) {
		List<Position> adjacentPos = new ArrayList<Position>();

		Position newPos = new Position(pos.getX() + 1, pos.getY() + 1);
		newPos = new Position(pos.getX() + 0, pos.getY() + 1);
		adjacentPos.add(newPos);
		newPos = new Position(pos.getX() + 1, pos.getY() + 1);
		adjacentPos.add(newPos);
		newPos = new Position(pos.getX() - 1, pos.getY() + 0);
		adjacentPos.add(newPos);
		newPos = new Position(pos.getX() - 1, pos.getY() + 1);
		adjacentPos.add(newPos);
		newPos = new Position(pos.getX() + 0, pos.getY() - 1);
		adjacentPos.add(newPos);
		newPos = new Position(pos.getX() - 1, pos.getY() - 1);
		adjacentPos.add(newPos);
		newPos = new Position(pos.getX() + 1, pos.getY() + 0);
		adjacentPos.add(newPos);
		newPos = new Position(pos.getX() + 1, pos.getY() - 1);
		adjacentPos.add(newPos);

		for (Position thePos : adjacentPos) {
			Piece adjacentPiece = board.getPieceAtPos(thePos);
			if (adjacentPiece != null && adjacentPiece.getClass() == King.class && adjacentPiece.mycolor != color) {
				return true;
			}
		}

		return false;

	}

}
