package com.lightblue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Board {
	
	private Map<Position, Piece> map;
	public static int min = 0;
	public static int max = 7;
	
	public Board(){
		map = new HashMap<Position, Piece>();
	}
	
	public void putPieceAtPos(Position pos, Piece piece){
		Piece oldPiece = getPieceAtPos(pos);
		if (oldPiece != null){
			oldPiece.die();
		}
		map.put(pos, piece);
	}
	
	public Piece getPieceAtPos(Position pos){
		Iterator<Position> it = map.keySet().iterator();
		while (it.hasNext()){
			Position currentPos = it.next();
			if (currentPos.equals(pos)){
				return map.get(currentPos);
			}
		}
		return null;
	
	}

	public void applyMove(Move move) {
		
		Iterator<Tuple> it = move.getMap().keySet().iterator();
		while (it.hasNext()){
			Tuple tup = it.next();
			Position pos1 = tup.getPos();
			map.put(pos1, null);
			
			Tuple tupImage = move.getMap().get(tup);
			Piece piece = tup.getPiece();
			piece = tupImage.getPiece();
			piece.setHasMoved(true);
			putPieceAtPos(tupImage.getPos(), piece);
		}
		
	}

	public Position getPositionOfPiece(Piece piece) {
		
		Iterator<Position> it = map.keySet().iterator();
		while (it.hasNext()){
			Position pos = it.next();
			if (map.get(pos).equals(piece)){
				return pos;
			}
		}
		return null;
		
	}

	public boolean isInside(Position newPos) {
		boolean correctX = (newPos.getX() <= max && newPos.getX() >= min) ? true : false;
		boolean correctY = (newPos.getY() <= max && newPos.getY() >= min) ? true : false;
		return correctX && correctY;
	}

}
