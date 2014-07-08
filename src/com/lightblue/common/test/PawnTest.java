package com.lightblue.common.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lightblue.common.Board;
import com.lightblue.common.Move;
import com.lightblue.common.Pawn;
import com.lightblue.common.Piece;
import com.lightblue.common.Position;

public class PawnTest {

	@Test
	public void getAllMovesTest() {
		Board board = new Board();
		Position pos1 = new Position(0, 1);
		Pawn pawn1 = new Pawn(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, pawn1);

		List<Move> moves1 = pawn1.getAllMoves(board);
		Assert.assertEquals(2, moves1.size());

		Position pos2 = new Position(0, 3);
		Pawn pawn2 = new Pawn(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, pawn2);
		List<Move> moves2 = pawn1.getAllMoves(board);
		Assert.assertEquals(1, moves2.size());

		Position pos3 = new Position(1, 2);
		Pawn pawn3 = new Pawn(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, pawn3);
		List<Move> moves3 = pawn1.getAllMoves(board);
		Assert.assertEquals(2, moves3.size());
	}

}
