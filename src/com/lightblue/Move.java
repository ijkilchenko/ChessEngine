package com.lightblue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lightblue.Piece.Color;

public class Move {

	protected Map<Tuple, Tuple> map;

	public Map<Tuple, Tuple> getMap() {
		return map;
	}

	public void setMap(Map<Tuple, Tuple> map) {
		this.map = map;
	}

	public Move() {
		map = new HashMap<Tuple, Tuple>();
	}

	public static boolean isMoveValid(Move move, Board board, Color color) {
		Board boardCopy = Board.copyBoard(board);
		Position myKingPos = board.getPositionOfKing();

		boardCopy.applyMove(move);
		Color opponentColor = !color.equals(Piece.Color.WHITE) ? Piece.Color.WHITE : Piece.Color.BLACK;

		List<Move> opponentNextMoves = Board.getAllMoves(boardCopy, opponentColor);
		for (Move opponentMove : opponentNextMoves) {
			Iterator<Tuple> it = opponentMove.getMap().keySet().iterator();
			Tuple tupImage = opponentMove.getMap().get(it.next());

			if (myKingPos.equals(tupImage.getPos())) {
				return false;
			}
		}

		return true;

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
