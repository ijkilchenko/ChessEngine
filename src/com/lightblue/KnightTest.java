package com.lightblue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class KnightTest {

	@Test
	public void getAllMovesTest(){
		Board board = new Board();
		Position pos1 = new Position(0, 1);
		Knight knight1 = new Knight(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, knight1);
		
		List<Move> moves1 = knight1.getAllMoves(board);
		Assert.assertEquals(3, moves1.size());
		
		Position pos2 = new Position(2, 2);
		Knight pawn2 = new Knight(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, pawn2);
		List<Move> moves2 = knight1.getAllMoves(board);
		Assert.assertEquals(2, moves2.size());
		
		Position pos3 = new Position(2, 0);
		Knight pawn3 = new Knight(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, pawn3);
		List<Move> moves3 = knight1.getAllMoves(board);
		Assert.assertEquals(2, moves3.size());
	}

}
