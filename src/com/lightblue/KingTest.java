package com.lightblue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class KingTest {

	@Test
	public void getAllMovesTest() {
		Board board = new Board();
		Position pos1 = new Position(0, 1);
		King king1 = new King(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, king1);

		List<Move> moves1 = king1.getAllMoves(board);
		Assert.assertEquals(5, moves1.size());

		Position pos2 = new Position(0, 2);
		Pawn pawn2 = new Pawn(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, pawn2);
		List<Move> moves2 = king1.getAllMoves(board);
		Assert.assertEquals(4, moves2.size());

		Position pos3 = new Position(2, 0);
		King king2 = new King(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, king2);
		List<Move> moves3 = king1.getAllMoves(board);
		Assert.assertEquals(2, moves3.size());
	}

}
