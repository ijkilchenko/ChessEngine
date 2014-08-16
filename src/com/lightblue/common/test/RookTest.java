package com.lightblue.common.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lightblue.common.Rook;
import com.lightblue.common.Board;
import com.lightblue.common.Move;
import com.lightblue.common.Piece;
import com.lightblue.common.Position;
import com.lightblue.common.Rook;

public class RookTest {

	@Test
	public void getAllMovesTest() {
		Board board = new Board();
		Position pos1 = new Position(0, 1);
		Rook rook1 = new Rook(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, rook1);

		List<Move> moves1 = rook1.getAllMoves(board);
		Assert.assertEquals(14, moves1.size());

		Position pos2 = new Position(0, 3);
		Rook pawn2 = new Rook(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, pawn2);
		List<Move> moves2 = rook1.getAllMoves(board);
		Assert.assertEquals(9, moves2.size());

		Position pos3 = new Position(5, 1);
		Rook pawn3 = new Rook(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, pawn3);
		List<Move> moves3 = rook1.getAllMoves(board);
		Assert.assertEquals(7, moves3.size());
	}

	@Test
	public void pieceDefendingTest() {
		Board board = new Board();
		Position pos1 = new Position(3, 1);
		Rook rook1 = new Rook(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, rook1);

		Position pos2 = new Position(3, 4);
		Rook rook2 = new Rook(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, rook2);

		Position pos3 = new Position(5, 4);
		Rook rook3 = new Rook(Piece.Color.WHITE);
		board.putPieceAtPos(pos3, rook3);

		int counter = 0;
		List<Move> moves1 = rook1.getAllMoves(board);
		for (Move move : moves1) {
			if (move.getPieceDefending() != null && move.getPieceDefending().equals(rook2)) {
				counter++;
			}
		}

		counter = 0;
		List<Move> moves2 = rook2.getAllMoves(board);
		for (Move move : moves2) {
			if (move.getPieceDefending() != null
					&& (move.getPieceDefending().equals(rook1) || move.getPieceDefending().equals(rook3))) {
				counter++;
			}
		}

		Assert.assertEquals(3, counter);

	}

}
