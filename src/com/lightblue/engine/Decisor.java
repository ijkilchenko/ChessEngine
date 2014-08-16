package com.lightblue.engine;

import java.util.List;
import java.util.Random;

import com.lightblue.common.Board;
import com.lightblue.common.Move;
import com.lightblue.common.Piece.Color;

public class Decisor {

	public static Move getBestMove(Board currentBoard, List<Move> moves, Color myColor) {
		//Move move = pickRandomMove(moves);
		Move move = pickBestMove(currentBoard, moves, myColor);
		return move;
	}

	public static Move pickRandomMove(List<Move> moves) {
		Random random = new Random();
		int randomInt = random.nextInt(moves.size());
		return moves.get(randomInt);
	}

	public static Move pickBestMove(Board currentBoard, List<Move> moves, Color myColor) {
		Color opponentColor = myColor.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
		Move bestAttackMove = moves.get(0);

		int min = getNumOfOpponentMoves(currentBoard, bestAttackMove, opponentColor);
		Move bestDefenseMove = moves.get(0);

		Board boardCopy = Board.copyBoard(currentBoard);
		boardCopy.applyMove(bestDefenseMove);

		Move bestMove = moves.get(0);

		int counter = getNumOfMutualDefenses(boardCopy, myColor);
		double bestNum = 1 / (counter + 1) + min;
		for (int i = 1; i < moves.size(); i++) {
			int currentMin = getNumOfOpponentMoves(currentBoard, moves.get(i), opponentColor);

			boardCopy = Board.copyBoard(currentBoard);
			boardCopy.applyMove(moves.get(i));

			double currentCounter = getNumOfMutualDefenses(boardCopy, myColor);

			if (1 / (currentCounter + 1) + currentMin < bestNum) {
				bestMove = moves.get(i);
			}
		}

		return bestMove;
	}

	public static int getNumOfOpponentMoves(Board currentBoard, Move move, Color opponentColor) {

		Board boardCopy = Board.copyBoard(currentBoard);
		Move moveCopy = Move.copyMove(boardCopy, move);
		boardCopy.applyMove(moveCopy);

		List<Move> moves = Board.getAllValidMoves(boardCopy, opponentColor);
		return moves.size();
	}

	public static int getNumOfMutualDefenses(Board currentBoard, Color myColor) {

		int counter = 0;
		List<Move> allMoves = Board.getAllValidMoves(currentBoard, myColor);

		for (Move move : allMoves) {
			if (move.getPieceDefending() != null) {
				counter++;
			}
		}

		return counter;

	}

}
