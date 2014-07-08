package com.lightblue.common.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lightblue.common.Board;
import com.lightblue.common.King;
import com.lightblue.common.Knight;
import com.lightblue.common.Move;
import com.lightblue.common.Pawn;
import com.lightblue.common.Piece;
import com.lightblue.common.Position;
import com.lightblue.common.Rook;

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

	@Test
	public void generalCastlingTest1() {
		Board board = new Board();
		Position pos1 = new Position(4, 0);
		King king1 = new King(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, king1);

		List<Move> moves1 = king1.getAllMoves(board);
		Assert.assertEquals(5, moves1.size());

		Position pos2 = new Position(0, 0);
		Rook leftRook = new Rook(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, leftRook);
		List<Move> moves2 = king1.getAllMoves(board);
		Assert.assertEquals(6, moves2.size());

		Position pos3 = new Position(7, 0);
		Rook rightRook = new Rook(Piece.Color.WHITE);
		board.putPieceAtPos(pos3, rightRook);
		List<Move> moves3 = king1.getAllMoves(board);
		Assert.assertEquals(7, moves3.size());

		Position pos4 = new Position(1, 2);
		Knight blackKnight = new Knight(Piece.Color.BLACK);
		board.putPieceAtPos(pos4, blackKnight);
		List<Move> moves4 = king1.getAllMoves(board);
		Assert.assertEquals(6, moves4.size());

		board.drawBoard();

		Position rightRookFinal = new Position(7, 7);
		board.applyMove(new Move(rightRook, pos3, rightRookFinal));
		board.drawBoard();
		List<Move> moves5 = king1.getAllMoves(board);
		Assert.assertEquals(5, moves5.size());

		board.applyMove(new Move(rightRook, rightRookFinal, pos3));
		List<Move> moves6 = king1.getAllMoves(board);
		Assert.assertEquals(5, moves6.size());

	}

	@Test
	public void generalCastlingTest2() {
		Board board = new Board();
		Position pos1 = new Position(4, 0);
		King king1 = new King(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, king1);

		Position pos2 = new Position(4, 7);
		King blackKing = new King(Piece.Color.BLACK);
		board.putPieceAtPos(pos2, blackKing);

		Position pos3 = new Position(7, 7);
		Rook blackRook = new Rook(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, blackRook);

		board.drawBoard();
		Assert.assertEquals(15, Board.getAllValidMoves(board, Piece.Color.BLACK).size());

		Assert.assertEquals(5, Board.getAllValidMoves(board, Piece.Color.WHITE).size());
	}

}
