package com.lightblue.common.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lightblue.common.Bishop;
import com.lightblue.common.Board;
import com.lightblue.common.King;
import com.lightblue.common.Knight;
import com.lightblue.common.Move;
import com.lightblue.common.Piece;
import com.lightblue.common.Position;
import com.lightblue.common.Queen;
import com.lightblue.common.Rook;

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

	@Test
	public void generalTest1() {

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

		Assert.assertEquals(12, Board.getAllValidMoves(board, Piece.Color.WHITE).size());

	}

	@Test
	public void generalTest2() {
		Board board = new Board();
		Position pos1 = new Position(2, 3);
		Knight knight1 = new Knight(Piece.Color.WHITE);
		board.putPieceAtPos(pos1, knight1);

		Position pos2 = new Position(2, 1);
		King king1 = new King(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, king1);

		Position pos3 = new Position(2, 6);
		Rook rook1 = new Rook(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, rook1);

		Position pos4 = new Position(6, 1);
		Rook rook2 = new Rook(Piece.Color.BLACK);
		board.putPieceAtPos(pos4, rook2);

		Assert.assertEquals(6, Board.getAllValidMoves(board, Piece.Color.WHITE).size());

	}

	@Test
	public void generalTest3() {
		Board board = new Board();

		Position pos2 = new Position(2, 1);
		King king1 = new King(Piece.Color.WHITE);
		board.putPieceAtPos(pos2, king1);

		Position pos3 = new Position(2, 6);
		Rook rook1 = new Rook(Piece.Color.BLACK);
		board.putPieceAtPos(pos3, rook1);

		Assert.assertEquals(6, Board.getAllValidMoves(board, Piece.Color.WHITE).size());
	}
	
	@Test
	public void generalTest4() {
		Board board = new Board();
		
		Position whiteKingStartingPos = new Position(4, 0);
		King whiteKing = new King(Piece.Color.WHITE);
		board.putPieceAtPos(whiteKingStartingPos, whiteKing);
		
		Position blackKingStartingPos = new Position(4, 7);
		King blackKing = new King(Piece.Color.BLACK);
		board.putPieceAtPos(blackKingStartingPos, blackKing);
		
		Position whiteKnightStartingPos = new Position(1, 0);
		Knight whiteKnight = new Knight(Piece.Color.WHITE);
		board.putPieceAtPos(whiteKnightStartingPos, whiteKnight);
		
		Position blackBishopStartingPos = new Position(2, 2);
		Bishop blackBishop = new Bishop(Piece.Color.BLACK);
		board.putPieceAtPos(blackBishopStartingPos, blackBishop);
		board.drawBoard();
		
		List<Move> whiteMoves1 = Board.getAllValidMoves(board, Piece.Color.WHITE);
		List<Move> blackMoves1 = Board.getAllValidMoves(board, Piece.Color.BLACK);
		
		Assert.assertEquals(6, whiteMoves1.size());
		Assert.assertEquals(16, blackMoves1.size());
		
		Assert.assertEquals(2, board.getNumOfPieces(Piece.Color.WHITE));
		Assert.assertEquals(2, board.getNumOfPieces(Piece.Color.BLACK));
		
		Move knightTakesBishop = new Move(whiteKnight, whiteKnightStartingPos, blackBishopStartingPos);
		Assert.assertEquals(0, board.getTotalNumOfDead());
		board.applyMove(knightTakesBishop);
		Assert.assertEquals(2, board.getNumOfPieces(Piece.Color.WHITE));
		Assert.assertEquals(1, board.getNumOfPieces(Piece.Color.BLACK));
		Assert.assertEquals(1, board.getTotalNumOfDead());
		Assert.assertEquals(0, board.getNumOfDead(Piece.Color.WHITE));
		Assert.assertEquals(1, board.getNumOfDead(Piece.Color.BLACK));
		board.drawBoard();
	}
}
