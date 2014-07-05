package com.lightblue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

	@Test
	public void putPieceAtPosTest() {

		Board board = new Board();
		Position pos1 = new Position(3, 3);
		Piece piece1 = new Piece(Piece.Color.WHITE);

		Assert.assertEquals(null, board.getPieceAtPos(pos1));
		board.putPieceAtPos(pos1, piece1);
		Assert.assertEquals(piece1, board.getPieceAtPos(pos1));
		Assert.assertEquals(null, board.getPieceAtPos(new Position(3, 4)));

		Piece piece2 = new Piece(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, piece2);
		Assert.assertEquals(piece2, board.getPieceAtPos(pos1));
		Assert.assertNotEquals(piece1, board.getPieceAtPos(pos1));
		Assert.assertEquals(Piece.State.DEAD, piece1.getMystate());

	}

	@Test
	public void applyMoveTest() {

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

		Assert.assertEquals(1, board.getMap().size());

		Position pos3 = new Position(4, 5);
		Piece piece2 = new Piece(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, piece2);
		Move move2 = new Move();
		move2.addMove(piece1, pos2, pos3);

		board.applyMove(move2);
		Assert.assertEquals(piece1, board.getPieceAtPos(pos3));
		Assert.assertEquals(Piece.State.DEAD, piece2.getMystate());

		Assert.assertEquals(1, board.getMap().size());

	}

	@Test
	public void applyQueenMoveTest() {
		Board board = new Board();
		Position pos1 = new Position(3, 3);
		Queen queen1 = new Queen(Queen.Color.WHITE);
		Position pos2 = new Position(3, 4);
		Move move1 = new Move();
		move1.addMove(queen1, pos1, pos2);
		board.putPieceAtPos(pos1, queen1);
		Assert.assertEquals(queen1, board.getPieceAtPos(pos1));
		Assert.assertEquals(null, board.getPieceAtPos(pos2));
		board.applyMove(move1);
		Assert.assertEquals(null, board.getPieceAtPos(pos1));
		Assert.assertEquals(queen1, board.getPieceAtPos(pos2));

		Assert.assertEquals(1, board.getMap().size());

		Position pos3 = new Position(4, 5);
		Queen queen2 = new Queen(Queen.Color.BLACK);
		board.putPieceAtPos(pos3, queen2);
		Move move2 = new Move();
		move2.addMove(queen1, pos2, pos3);

		board.applyMove(move2);
		Assert.assertEquals(queen1, board.getPieceAtPos(pos3));
		Assert.assertEquals(Queen.State.DEAD, queen2.getMystate());

		Assert.assertEquals(1, board.getMap().size());
	}

	@Test
	public void getStartingBoardTest() {

		Board board = new Board();
		board = Board.getStartingBoard(board);
		Assert.assertEquals(32, board.getMap().keySet().size());

		List<Move> allWhiteMoves = Board.getAllMoves(board, Piece.Color.WHITE);
		Assert.assertEquals(20, allWhiteMoves.size());

		List<Move> allValidWhiteMoves = Board.getAllValidMoves(board, Piece.Color.WHITE);
		Assert.assertEquals(20, allValidWhiteMoves.size());

	}

}
