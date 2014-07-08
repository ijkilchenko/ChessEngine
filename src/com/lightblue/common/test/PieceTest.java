package com.lightblue.common.test;

import org.junit.Assert;
import org.junit.Test;

import com.lightblue.common.Pawn;
import com.lightblue.common.Piece;

public class PieceTest {

	@Test
	public void copyPieceTest() {
		Pawn pawn1 = new Pawn(Piece.Color.WHITE);

		Pawn pawn1Copy = (Pawn) Piece.copyPiece(pawn1);

		pawn1.setHasMoved(true);

		Assert.assertTrue(pawn1Copy.hasMoved != true);
		Assert.assertTrue(pawn1.hasMoved == true);
	}

}
