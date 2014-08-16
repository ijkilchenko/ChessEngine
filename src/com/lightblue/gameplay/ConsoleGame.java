package com.lightblue.gameplay;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.lightblue.common.Board;
import com.lightblue.common.EnPassant;
import com.lightblue.common.Move;
import com.lightblue.common.Position;
import com.lightblue.common.Tuple;
import com.lightblue.common.Piece.Color;
import com.lightblue.gameplay.Game.Status;

public class ConsoleGame {

	public static void main(String[] args) {

		Game game = new Game();
		Scanner in = new Scanner(System.in);

		int movesCountLimit = 600;
		int i = 0;
		while (game.isGamePlayable() && i < movesCountLimit) {
			game.getCurrentBoard().drawBoard();
			Move inputMove = null;
			while (inputMove == null) {
				System.out.println("Enter a move! ");
				String inputMoveString = in.nextLine();
				try {
					inputMove = ConsoleGame.convertStringToMove(inputMoveString, game);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("You entered: " + ConsoleGame.convertMoveToString(inputMove));
			game.applyMoveToGame(inputMove);

			if (!game.isGamePlayable()) {
				break;
			} else {
				// Engine's move
				Move engineMove = game.generateNextMove();
				System.out.println("Applying move: " + ConsoleGame.convertMoveToString(engineMove));
				game.applyMoveToGame(engineMove);
			}
			System.out.println("Move " + i);
			i++;
		}
		in.close();
		game.getCurrentBoard().drawBoard();
		if (game.getStatus().equals(Status.WHITEWON)) {
			System.out.println("White won!");
		} else if (game.getStatus().equals(Status.BLACKWON)) {
			System.out.println("Black won!");
		}

	}

	public static Move convertStringToMove(String str, Game game) throws Exception {
		List<Move> allMoves = Board.getAllValidMoves(game.getCurrentBoard(), game.getCurrentMoveColor());
		Move returningMove = null;
		try {
			for (Move move : allMoves) {
				Position inputStart = Position.convertStringToPosition((String) str.subSequence(0, 2));
				Position inputFinish = Position.convertStringToPosition((String) str.subSequence(6, 8));

				Iterator<Tuple> it = move.getMap().keySet().iterator();
				while (it.hasNext()) {
					Tuple tup = it.next();
					Tuple tupImage = move.getMap().get(tup);

					if (tup.getPos().equals(inputStart) && tupImage.getPos().equals(inputFinish)) {
						returningMove = move;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			returningMove = null;
		}

		if (returningMove == null) {
			System.out.println("You entered an illegal move!");
		}

		return returningMove;

	}

	public static String convertMoveToString(Move move) {
		String moveString = "Move: ";
		if (move == null) {
			return moveString + "\n";
		}
		if (move.getClass() == EnPassant.class) {
			Iterator<Tuple> it = move.getMap().keySet().iterator();
			while (it.hasNext()) {
				Tuple tup = it.next();
				Tuple tupImage = move.getMap().get(tup);
				if (tupImage.getPos() == null) {
					moveString += "En Passant at " + tup.getPos().getString();
				}
			}
		} else {
			Iterator<Tuple> it = move.getMap().keySet().iterator();
			while (it.hasNext()) {
				Tuple tup = it.next();
				Tuple tupImage = move.getMap().get(tup);

				moveString += tup.getPos().getString() + " -> " + tupImage.getPos().getString() + "\t";

			}
		}
		return moveString + "\n";
	}

}
