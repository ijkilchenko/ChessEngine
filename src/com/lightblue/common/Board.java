package com.lightblue.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lightblue.common.Piece.Color;

public class Board {

	private Map<Position, Piece> map;
	public List<Piece> graveyard;
	public static int min = 0;
	public static int max = 7;

	public Piece isLastMoveBigStep = null;

	public Board() {
		map = new HashMap<Position, Piece>();
		graveyard = new ArrayList<Piece>();
	}

	public void drawBoard() {
		System.out.println("--------------------------------");
		for (int j = max; j >= min - 1; j--) {
			if (j != -1) {
				System.out.print(j + 1 + " |");
				for (int i = min; i <= max; i++) {
					Piece piece = getPieceAtPos(new Position(i, j));
					if (piece != null) {
						if (piece.getClass().equals(Pawn.class)) {
							System.out.print(Pawn.text);
						} else if (piece.getClass().equals(Rook.class)) {
							System.out.print(Rook.text);
						} else if (piece.getClass().equals(Knight.class)) {
							System.out.print(Knight.text);
						} else if (piece.getClass().equals(Queen.class)) {
							System.out.print(Queen.text);
						} else if (piece.getClass().equals(Bishop.class)) {
							System.out.print(Bishop.text);
						} else {
							System.out.print(King.text);
						}
						System.out.print("" + piece.getTextOfColor() + "|");
					} else {
						System.out.print("" + "  |");
					}
				}
				System.out.println();
			} else {
				System.out.print("   ");
				for (int i = min; i <= max; i++) {
					String letter;
					switch (i) {
					case 0:
						letter = "a";
						break;
					case 1:
						letter = "b";
						break;
					case 2:
						letter = "c";
						break;
					case 3:
						letter = "d";
						break;
					case 4:
						letter = "e";
						break;
					case 5:
						letter = "f";
						break;
					case 6:
						letter = "g";
						break;
					default:
						letter = "h";
						break;
					}
					System.out.print(letter + "  ");
				}
				System.out.println();
			}
		}
		System.out.println();
		List<Piece> blackDeadPieces = new ArrayList<Piece>();
		List<Piece> whiteDeadPieces = new ArrayList<Piece>();

		for (int i = 0; i < graveyard.size(); i++) {
			if (graveyard.get(i).mycolor.equals(Color.WHITE)) {
				whiteDeadPieces.add(graveyard.get(i));
			} else {
				blackDeadPieces.add(graveyard.get(i));
			}
		}
		System.out.print("Black's captures: ");
		for (int i = 0; i < whiteDeadPieces.size(); i++) {
			Piece piece = whiteDeadPieces.get(i);
			if (piece.getClass().equals(Pawn.class)) {
				System.out.print(Pawn.text);
			} else if (piece.getClass().equals(Rook.class)) {
				System.out.print(Rook.text);
			} else if (piece.getClass().equals(Knight.class)) {
				System.out.print(Knight.text);
			} else if (piece.getClass().equals(Queen.class)) {
				System.out.print(Queen.text);
			} else if (piece.getClass().equals(Bishop.class)) {
				System.out.print(Bishop.text);
			} else {
				System.out.print(King.text);
			}
			System.out.print(", ");
		}
		System.out.print(" \n");

		System.out.print("White's captures: ");
		for (int i = 0; i < blackDeadPieces.size(); i++) {
			Piece piece = blackDeadPieces.get(i);
			if (piece.getClass().equals(Pawn.class)) {
				System.out.print(Pawn.text);
			} else if (piece.getClass().equals(Rook.class)) {
				System.out.print(Rook.text);
			} else if (piece.getClass().equals(Knight.class)) {
				System.out.print(Knight.text);
			} else if (piece.getClass().equals(Queen.class)) {
				System.out.print(Queen.text);
			} else if (piece.getClass().equals(Bishop.class)) {
				System.out.print(Bishop.text);
			} else {
				System.out.print(King.text);
			}
			System.out.print(", ");
		}
		System.out.print(" \n");
		System.out.println("--------------------------------");
	}

	public static List<Piece> copyGraveyard(List<Piece> graveyard) {
		List<Piece> newGraveyard = new ArrayList<Piece>();

		for (Piece oldPiece : graveyard) {
			newGraveyard.add(Piece.copyPiece(oldPiece));
		}

		return newGraveyard;
	}

	public static Board getStartingBoard(Board board) {

		for (int i = min; i <= max; i++) {
			board.putPieceAtPos(new Position(i, 1), new Pawn(Piece.Color.WHITE));
		}
		for (int i = min; i <= max; i++) {
			board.putPieceAtPos(new Position(i, 6), new Pawn(Piece.Color.BLACK));
		}

		board.putPieceAtPos(new Position(0, 0), new Rook(Piece.Color.WHITE));
		board.putPieceAtPos(new Position(7, 0), new Rook(Piece.Color.WHITE));

		board.putPieceAtPos(new Position(0, 7), new Rook(Piece.Color.BLACK));
		board.putPieceAtPos(new Position(7, 7), new Rook(Piece.Color.BLACK));

		board.putPieceAtPos(new Position(1, 0), new Knight(Piece.Color.WHITE));
		board.putPieceAtPos(new Position(6, 0), new Knight(Piece.Color.WHITE));

		board.putPieceAtPos(new Position(1, 7), new Knight(Piece.Color.BLACK));
		board.putPieceAtPos(new Position(6, 7), new Knight(Piece.Color.BLACK));

		board.putPieceAtPos(new Position(2, 0), new Bishop(Piece.Color.WHITE));
		board.putPieceAtPos(new Position(5, 0), new Bishop(Piece.Color.WHITE));

		board.putPieceAtPos(new Position(2, 7), new Bishop(Piece.Color.BLACK));
		board.putPieceAtPos(new Position(5, 7), new Bishop(Piece.Color.BLACK));

		board.putPieceAtPos(new Position(3, 0), new Queen(Piece.Color.WHITE));
		board.putPieceAtPos(new Position(4, 0), new King(Piece.Color.WHITE));

		board.putPieceAtPos(new Position(3, 7), new Queen(Piece.Color.BLACK));
		board.putPieceAtPos(new Position(4, 7), new King(Piece.Color.BLACK));

		return board;

	}

	public List<Piece> getGraveyard() {
		return graveyard;
	}

	public int getTotalNumOfDead() {
		return graveyard.size();
	}

	public int getNumOfDead(Color color) {
		int totalNum = 0;
		for (Piece piece : graveyard) {
			if (piece.mycolor.equals(color)) {
				totalNum++;
			}
		}
		return totalNum;
	}

	public void setGraveyard(List<Piece> graveyard) {
		this.graveyard = graveyard;
	}

	public Position getPositionOfKing(Color color) {
		Iterator<Position> it = map.keySet().iterator();
		while (it.hasNext()) {
			Position pos = it.next();
			if (map.get(pos).getClass() == King.class && map.get(pos).mycolor.equals(color)) {
				return pos;
			}
		}
		return null;
	}

	public int getNumOfPieces() {
		return getMap().size();
	}

	public int getNumOfPieces(Color color) {
		Iterator<Position> it = this.map.keySet().iterator();
		int totalNum = 0;
		while (it.hasNext()) {
			Piece piece = this.map.get(it.next());
			if (piece.mycolor.equals(color)) {
				totalNum++;
			}
		}
		return totalNum;
	}

	public static Board copyBoard(Board board) {
		Map<Position, Piece> map = new HashMap<Position, Piece>();

		Iterator<Position> it = board.map.keySet().iterator();
		while (it.hasNext()) {
			Position pos = it.next();
			Position posCopy = Position.copyPosition(pos);
			Piece piece = board.map.get(pos);
			Piece pieceCopy = Piece.copyPiece(piece);
			map.put(posCopy, pieceCopy);
		}
		Board boardCopy = new Board();
		boardCopy.setMap(map);

		boardCopy.setGraveyard(Board.copyGraveyard(board.graveyard));
		return boardCopy;
	}

	public static List<Move> getAllValidMoves(Board board, Color color) {
		List<Move> allMoves = getAllMoves(board, color);
		List<Move> allValidMoves = new ArrayList<Move>();
		for (Move move : allMoves) {
			if (Move.isMoveValid(move, board, color)) {
				allValidMoves.add(move);
			}
		}
		return allValidMoves;
	}

	public static List<Move> getAllMoves(Board board, Color color) {
		List<Move> allMoves = new ArrayList<Move>();

		Iterator<Position> it = board.map.keySet().iterator();
		while (it.hasNext()) {
			Piece piece = board.map.get(it.next());
			if (piece.mycolor == color) {
				if (piece.getClass().equals(Pawn.class)) {
					piece = (Pawn) piece;
					allMoves.addAll(Pawn.getAllMoves(board, piece));
				} else if (piece.getClass().equals(Rook.class)) {
					piece = (Rook) piece;
					allMoves.addAll(Rook.getAllMoves(board, piece));
				} else if (piece.getClass().equals(Knight.class)) {
					piece = (Knight) piece;
					allMoves.addAll(Knight.getAllMoves(board, piece));
				} else if (piece.getClass().equals(Queen.class)) {
					piece = (Queen) piece;
					allMoves.addAll(Queen.getAllMoves(board, piece));
				} else if (piece.getClass().equals(Bishop.class)) {
					piece = (Bishop) piece;
					allMoves.addAll(Bishop.getAllMoves(board, piece));
				} else {
					piece = (King) piece;
					allMoves.addAll(King.getAllMoves(board, piece));
				}
			}
		}
		return allMoves;

	}

	public Map<Position, Piece> getMap() {
		return map;
	}

	public void setMap(Map<Position, Piece> map) {
		this.map = map;
	}

	public void putPieceAtPos(Position pos, Piece piece) {
		Piece oldPiece = getPieceAtPos(pos);
		if (oldPiece != null) {
			pos = getPositionOfPiece(oldPiece);
		}
		if (oldPiece != null) {
			oldPiece.die();
			graveyard.add(oldPiece);
		}
		map.put(pos, piece);
	}

	public Piece getPieceAtPos(Position pos) {
		Iterator<Position> it = map.keySet().iterator();
		while (it.hasNext()) {
			Position currentPos = it.next();
			if (currentPos.equals(pos)) {
				return map.get(currentPos);
			}
		}
		return null;

	}

	public void applyMove(Move move) {
		Move localMove = new Move();
		localMove = move;

		isLastMoveBigStep = null;
		if (localMove.getClass() == EnPassant.class) {
			applyEnPassant(localMove);
		} else {
			Iterator<Tuple> it = localMove.getMap().keySet().iterator();
			while (it.hasNext()) {
				Tuple tup = it.next();
				Position pos1 = tup.getPos();

				Position localPos = getLocalPos(pos1);
				map.remove(localPos);

				Tuple tupImage = localMove.getMap().get(tup);
				Piece piece = tup.getPiece();
				piece = tupImage.getPiece();
				piece.setHasMoved(true);

				if (piece.getClass() == Pawn.class && Math.abs(pos1.getY() - tupImage.getPos().getY()) == 2) {
					Pawn thisPawn = (Pawn) piece;
					isLastMoveBigStep = thisPawn;
				}

				putPieceAtPos(tupImage.getPos(), piece);
			}
		}

	}

	public Position getLocalPos(Position pos1) {
		Position localPos = null;
		Iterator<Position> it2 = map.keySet().iterator();
		while (it2.hasNext()) {
			Position thisPos = it2.next();
			if (thisPos.equals(pos1)) {
				localPos = thisPos;
			}
		}
		return localPos;
	}

	private void applyEnPassant(Move move) {
		Iterator<Tuple> it = move.getMap().keySet().iterator();
		while (it.hasNext()) {
			Tuple tup = it.next();
			Position pos1 = tup.getPos();
			if (move.getMap().get(tup).getPos() == null) {
				// Kill pawn
				Piece thisPiece = getPieceAtPos(pos1);
				thisPiece.die();
				graveyard.add(thisPiece);

				Position localPos = getLocalPos(pos1);

				map.remove(localPos);
			} else {
				Tuple tupImage = move.getMap().get(tup);
				Map<Tuple, Tuple> diagonalMove = new HashMap<Tuple, Tuple>();
				diagonalMove.put(tup, tupImage);
				Move diagonalMoveMap = new Move();
				diagonalMoveMap.setMap(diagonalMove);
				applyMove(diagonalMoveMap);
			}
		}

	}

	public Position getPositionOfPiece(Piece piece) {

		Iterator<Position> it = map.keySet().iterator();
		while (it.hasNext()) {
			Position pos = it.next();
			if (map.get(pos).equals(piece)) {
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
