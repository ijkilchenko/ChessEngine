package com.lightblue;

public class Position {

	private int X;
	private int Y;

	public static enum color {
		BLACK, WHITE
	};

	public Position(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	public static Position copyPosition(Position pos) {
		Position newPos = new Position(pos.getX(), pos.getY());
		return newPos;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position pos = (Position) obj;
			if (X == pos.getX() && Y == pos.getY()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public color getColor() {
		return (X + Y) % 2 == 0 ? color.WHITE : color.BLACK;
	}

	public String getString() {
		String numbers = "12345678";
		String letters = "abcdefgh";
		String position = "";
		position += letters.substring(X, X + 1) + numbers.substring(Y, Y + 1);
		return position;
	}

}
