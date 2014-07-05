package com.lightblue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BishopTest {

	@Test
	public void getAllMovesTest(){
		Board board = new Board();
		Position pos1 = new Position(0, 1);
		Bishop bishop1 = new Bishop(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, bishop1);
		
		List<Move> moves1 = bishop1.getAllMoves(board);
		Assert.assertEquals(7, moves1.size());
		
		Position pos2 = new Position(6, 7);
		Bishop pawn2 = new Bishop(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, pawn2);
		List<Move> moves2 = bishop1.getAllMoves(board);
		Assert.assertEquals(6, moves2.size());
		
		Position pos3 = new Position(2, 3);
		Bishop pawn3 = new Bishop(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, pawn3);
		List<Move> moves3 = bishop1.getAllMoves(board);
		Assert.assertEquals(3, moves3.size());
	}

}
