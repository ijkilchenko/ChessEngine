package com.lightblue.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lightblue.common.Piece.Color;

public class Move {

	protected Map<Tuple, Tuple> map;
	
	public Piece pieceAttacked = null;
	
	public Piece pieceDefending = null;

	public Map<Tuple, Tuple> getMap() {
		return map;
	}

	public void setMap(Map<Tuple, Tuple> map) {
		this.map = map;
	}

	public Piece getPieceAtFinish() {
		return pieceAttacked;
	}

	public void setPieceAttacked(Piece pieceAttacked) {
		this.pieceAttacked = pieceAttacked;
	}

	public Piece getPieceDefending() {
		return pieceDefending;
	}

	public void setPieceDefending(Piece pieceDefending) {
		this.pieceDefending = pieceDefending;
	}

	public Move() {
		map = new HashMap<Tuple, Tuple>();
	}

	public static boolean isMoveValid(Move move, Board board, Color color) {
		Board boardCopy = Board.copyBoard(board);

		Move moveCopy = Move.copyMove(boardCopy, move);

		boardCopy.applyMove(moveCopy);
		Position myKingPos = boardCopy.getPositionOfKing(color);
		Color opponentColor = !color.equals(Piece.Color.WHITE) ? Piece.Color.WHITE : Piece.Color.BLACK;

		List<Move> opponentNextMoves = Board.getAllMoves(boardCopy, opponentColor);
		for (Move opponentMove : opponentNextMoves) {
			Iterator<Tuple> it = opponentMove.getMap().keySet().iterator();
			while (it.hasNext()){
				Tuple tupImage = opponentMove.getMap().get(it.next());
	
				if (myKingPos == null || myKingPos.equals(tupImage.getPos())) {
					return false;
				}
			}
		}

		return true;

	}

	public static Move copyMove(Board boardCopy, Move move) {

		Move moveCopy;
		if (move.getClass() == EnPassant.class) {
			moveCopy = new EnPassant();
		} else {
			moveCopy = new Move();
		}
		Iterator<Tuple> it = move.getMap().keySet().iterator();
		Map<Tuple, Tuple> mapCopy = new HashMap<Tuple, Tuple>();
		while (it.hasNext()) {
			Tuple tup = it.next();
			Position posCopy = Position.copyPosition(boardCopy.getLocalPos(tup.getPos()));
			Piece pieceCopy = Piece.copyPiece(tup.getPiece());
			Tuple tupCopy = new Tuple(posCopy, pieceCopy);

			Tuple tupImage = move.getMap().get(tup);
			Piece pieceImageCopy = Piece.copyPiece(tupImage.getPiece());
			Position posImageCopy = Position.copyPosition(tupImage.getPos());
			Tuple tupImageCopy = new Tuple(posImageCopy, pieceImageCopy);

			mapCopy.put(tupCopy, tupImageCopy);
		}
		moveCopy = new Move();
		moveCopy.setMap(mapCopy);

		return moveCopy;

	}

	public Move(Piece piece, Position start, Position finish) {
		map = new HashMap<Tuple, Tuple>();
		addMove(piece, start, finish);
	}

	public Move(Piece piece, Position start, Piece newPiece, Position finish) {
		map = new HashMap<Tuple, Tuple>();
		becomeNewPieceMove(piece, start, newPiece, finish);
	}

	public void addMove(Piece piece, Position start, Position finish) {
		Tuple tup1 = new Tuple(start, piece);
		Tuple tup2 = new Tuple(finish, piece);
		map.put(tup1, tup2);
	}

	public void becomeNewPieceMove(Piece piece, Position start, Piece newPiece, Position finish) {
		Tuple tup1 = new Tuple(start, piece);
		Tuple tup2 = new Tuple(finish, newPiece);
		map.put(tup1, tup2);
	}
}
