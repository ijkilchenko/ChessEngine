package com.lightblue.gameplay;

import java.util.ArrayList;
import java.util.List;

import com.lightblue.common.Board;
import com.lightblue.common.Move;
import com.lightblue.common.Piece.Color;
import com.lightblue.engine.Decisor;

public class Game {

	public enum Status {
		WHITEWON, BLACKWON, DRAW, ACTIVE
	};

	public Status status;
	List<Board> boards;
	int currentIndex;
	List<Move> moves;

	public Game() {
		this.status = Status.ACTIVE;
		Board board = new Board();
		board = Board.getStartingBoard(board);
		boards = new ArrayList<Board>();
		boards.add(board);
		currentIndex = 0;
		moves = new ArrayList<Move>();
	}

	public Move generateNextMove() {
		if (isGamePlayable()) {
			List<Move> moves = Board.getAllValidMoves(getCurrentBoard(), getCurrentMoveColor());

			return Decisor.getBestMove(getCurrentBoard(), moves);
		} else {
			return null;
		}
	}

	public void goBack() {
		currentIndex--;
	}

	public void goForward() {
		if (boards.size() > currentIndex) {
			currentIndex++;
		}
	}

	public void fastForward() {
		currentIndex = boards.size();
	}

	public void fastBackwards() {
		currentIndex = -1;
	}

	public void applyMoveToGame(Move move) {
		Board newBoard = Board.copyBoard(getCurrentBoard());
		newBoard.applyMove(move);

		if (boards.size() == currentIndex) {
			boards.add(newBoard);
			moves.add(move);
			currentIndex++;
		} else {
			purgeAfterLastIndex();
			boards.add(newBoard);
			moves.add(move);
			currentIndex++;
		}

		moves = Board.getAllValidMoves(getCurrentBoard(), getCurrentMoveColor());
		if (moves.size() == 0) {
			if (getCurrentMoveColor().equals(Color.WHITE)) {
				setStatus(Status.BLACKWON);
			} else {
				setStatus(Status.WHITEWON);
			}
		}
	}

	public Color getCurrentMoveColor() {
		return currentIndex % 2 != 0 ? Color.BLACK : Color.WHITE;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void purgeAfterLastIndex() {
		purgeAfterIndex(this, currentIndex);
	}

	public static void purgeAfterIndex(Game game, int index) {

		for (int i = index + 1; i < game.boards.size(); i++) {
			game.boards.remove(i);
			game.moves.remove(i);
		}

	}

	public Board getCurrentBoard() {
		return boards.get(currentIndex);
	}

	public boolean isGamePlayable() {
		if (status.equals(Status.ACTIVE) && getCurrentBoard().getMovesCount() <= 500) {
			return true;
		} else {
			if (getCurrentBoard().getMovesCount() > 50){
				System.out.println("500 moves without capture reached! -> Draw");
			}
			return false;
		}
	}

}
