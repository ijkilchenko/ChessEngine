package com.lightblue;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	public enum Status {WHITEWON, BLACKWON, DRAW, ACTIVE};
	public Status mystatus;
	Board board;
	List<Move> moves;

	public Game(){
		this.mystatus = Status.ACTIVE;
		board = new Board();
		//Initialize board.
		moves = new ArrayList<Move>();
	}
}
