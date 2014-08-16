package com.lightblue.common.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lightblue.common.Bishop;
import com.lightblue.common.Board;
import com.lightblue.common.Move;
import com.lightblue.common.Piece;
import com.lightblue.common.Position;

public class BishopTest {

	@Test
	public void getAllMovesTest() {
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
	
	@Test
	public void pieceDefendingTest() {
		Board board = new Board();
		Position pos1 = new Position(0, 1);
		Bishop bishop1 = new Bishop(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, bishop1);
		
		Position pos2 = new Position(3, 4);
		Bishop bishop2 = new Bishop(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, bishop2);
		
		int counter = 0;
		List<Move> moves1 = bishop1.getAllMoves(board);
		for (Move move : moves1){
			if (move.getPieceDefending() != null && move.getPieceDefending().equals(bishop2)){
				counter++;
			}
		}
		
		counter = 0;
		List<Move> moves2 = bishop2.getAllMoves(board);
		for (Move move : moves2){
			if (move.getPieceDefending() != null && move.getPieceDefending().equals(bishop1)){
				counter++;
			}
		}
		
		Assert.assertEquals(2, counter);
		
	}

}
