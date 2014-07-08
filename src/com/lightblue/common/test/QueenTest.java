package com.lightblue.common.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lightblue.common.Board;
import com.lightblue.common.Move;
import com.lightblue.common.Piece;
import com.lightblue.common.Position;
import com.lightblue.common.Queen;

public class QueenTest {

	@Test
	public void getAllMovesTest() {
		Board board = new Board();
		Position pos1 = new Position(0, 1);
		Queen queen1 = new Queen(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, queen1);

		List<Move> moves1 = queen1.getAllMoves(board);
		Assert.assertEquals(21, moves1.size());

		Position pos2 = new Position(6, 7);
		Queen pawn2 = new Queen(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, pawn2);
		List<Move> moves2 = queen1.getAllMoves(board);
		Assert.assertEquals(20, moves2.size());

		Position pos3 = new Position(2, 1);
		Queen pawn3 = new Queen(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, pawn3);
		List<Move> moves3 = queen1.getAllMoves(board);
		Assert.assertEquals(15, moves3.size());
	}

}
