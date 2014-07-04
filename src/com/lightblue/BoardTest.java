package com.lightblue;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
	
	@Test
	public void putPieceAtPosTest(){
		
		Board board = new Board();
		Position pos1 = new Position(3, 3);
		Piece piece1 = new Piece(Piece.Color.WHITE);
		
		Assert.assertEquals(null, board.getPieceAtPos(pos1));
		board.putPieceAtPos(pos1, piece1);		
		Assert.assertEquals(piece1, board.getPieceAtPos(pos1));
		Assert.assertEquals(null, board.getPieceAtPos(new Position(3,4)));
		
		Piece piece2 = new Piece(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, piece2);
		Assert.assertEquals(piece2, board.getPieceAtPos(pos1));
		Assert.assertNotEquals(piece1, board.getPieceAtPos(pos1));
		Assert.assertEquals(Piece.State.DEAD, piece1.getMystate());
		
	}
	
	@Test
	public void applyMoveTest(){
		
		Board board = new Board();
		Position pos1 = new Position(3, 3);
		Piece piece1 = new Piece(Piece.Color.WHITE);
		Position pos2 = new Position(3, 4);
		Move move1 = new Move();
		move1.addMove(piece1, pos1, pos2);
		board.putPieceAtPos(pos1, piece1);
		Assert.assertEquals(piece1, board.getPieceAtPos(pos1));
		Assert.assertEquals(null, board.getPieceAtPos(pos2));
		board.applyMove(move1);
		Assert.assertEquals(null, board.getPieceAtPos(pos1));
		Assert.assertEquals(piece1, board.getPieceAtPos(pos2));
		
		Position pos3 = new Position(3,5);
		Piece piece2 = new Piece(Piece.Color.WHITE);
		board.putPieceAtPos(pos3, piece2);
		Move move2 = new Move();
		move2.addMove(piece1, pos2, pos3);
		
		board.applyMove(move2);
		Assert.assertEquals(piece1, board.getPieceAtPos(pos3));
		Assert.assertEquals(Piece.State.DEAD, piece2.getMystate());
		
	}

}
