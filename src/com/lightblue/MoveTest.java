package com.lightblue;

import org.junit.Assert;
import org.junit.Test;

public class MoveTest {

	@Test
	public void isMoveValidTest() {

		Board board = new Board();
		Position pos1 = new Position(2, 3);
		Queen queen1 = new Queen(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, queen1);

		Position pos2 = new Position(2, 1);
		King king1 = new King(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, king1);

		Position pos3 = new Position(2, 6);
		Rook rook1 = new Rook(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, rook1);

		Move move = new Move();
		Position pos4 = new Position(4, 5);
		move.addMove(queen1, pos1, pos4);
		Assert.assertFalse(Move.isMoveValid(move, board, Piece.Color.WHITE));

	}

	@Test
	public void applyQueenMoveTest() {

		Board board = new Board();
		Position pos1 = new Position(2, 3);
		Queen queen1 = new Queen(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, queen1);

		Move move = new Move();
		Position pos2 = new Position(4, 5);
		move.addMove(queen1, pos1, pos2);

		Board boardCopy = Board.copyBoard(board);

		boardCopy.applyMove(move);
		Assert.assertEquals(1, boardCopy.getMap().size());

	}

	@Test
	public void enPassantMoveTest() {

		Board board = new Board();
		
		Position kingPos = new Position(7,7);
		King king = new King(Piece.Color.WHITE);
		board.putPieceAtPos(kingPos, king);
		
		Position pos1 = new Position(5, 6);
		Pawn blackPawn = new Pawn(Piece.Color.BLACK);
		board.putPieceAtPos(pos1, blackPawn);
		Assert.assertEquals(2, Pawn.getAllMoves(board, blackPawn).size());

		Position pos2 = new Position(5, 4);
		Move blackPawnMove = new Move(blackPawn, pos1, pos2);
		Assert.assertTrue(board.isLastMoveBigStep == null);
		board.applyMove(blackPawnMove);
		Assert.assertEquals(1, Pawn.getAllMoves(board, blackPawn).size());
		Assert.assertTrue(board.isLastMoveBigStep.equals(blackPawn));

		Position pos3 = new Position(6, 4);
		Position pos4 = new Position(5, 5);
		Pawn whitePawn = new Pawn(Piece.Color.WHITE);
		board.putPieceAtPos(pos3, whitePawn);
		Assert.assertEquals(2, Pawn.getAllMoves(board, whitePawn).size());
		Move whitePawnMove = new Move(whitePawn, pos3, pos4);
		Assert.assertTrue(Move.isMoveValid(whitePawnMove, board, Piece.Color.WHITE));
		board.applyMove(whitePawnMove);
		Assert.assertEquals(1, Pawn.getAllMoves(board, whitePawn).size());
		Assert.assertTrue(board.isLastMoveBigStep == null);

	}

}
