package com.lightblue;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	public King(Color color) {
		super(color);
	}

	public List<Move> getAllMoves(Board board) {
		return getAllMoves(board, this);
	}

	public static List<Move> getAllMoves(Board board, Piece currentPiece) {

		List<Move> moves = new ArrayList<Move>();
		if (currentPiece.mystate == Piece.State.DEAD) {
			return null;
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
