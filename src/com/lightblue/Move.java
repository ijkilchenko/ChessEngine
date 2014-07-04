package com.lightblue;

import java.util.HashMap;
import java.util.Map;

public class Move {

	private Map<Tuple, Tuple> map;
	
	public Map<Tuple, Tuple> getMap() {
		return map;
	}

	public void setMap(Map<Tuple, Tuple> map) {
		this.map = map;
	}

	public Move(){
		map = new HashMap<Tuple, Tuple>();
	}
	
	public Move(Piece piece, Position start, Position finish){
		map = new HashMap<Tuple, Tuple>();
		addMove(piece, start, finish);
	}
	
	public void addMove(Piece piece, Position start, Position finish){
		Tuple tup1 = new Tuple(start, piece);
		Tuple tup2 = new Tuple(finish, piece);
		map.put(tup1, tup2);
	}
	
	public void makeMove(Piece piece, Position start, Piece newPiece, Position finish){
		Tuple tup1 = new Tuple(start, piece);
		Tuple tup2 = new Tuple(finish, newPiece);
		map.put(tup1, tup2);
	}	
}
