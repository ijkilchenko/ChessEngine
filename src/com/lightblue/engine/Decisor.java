package com.lightblue.engine;

import java.util.List;
import java.util.Random;

import com.lightblue.common.Board;
import com.lightblue.common.Move;

public class Decisor {

	public static Move getBestMove(Board currentBoard, List<Move> moves) {
		Move move = pickRandomMove(moves);
		return move;
	}

	public static Move pickRandomMove(List<Move> moves) {
		Random random = new Random();
		int randomInt = random.nextInt(moves.size());
		return moves.get(randomInt);		
	}

}
