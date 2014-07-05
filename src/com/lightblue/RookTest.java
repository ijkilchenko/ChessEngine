package com.lightblue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class RookTest {

	@Test
	public void getAllMovesTest(){
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

}
