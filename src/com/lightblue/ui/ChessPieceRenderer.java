package com.lightblue.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.lightblue.common.Piece;

public class ChessPieceRenderer {
    private static final Font CHESS_FONT = new Font("Arial Unicode MS", Font.PLAIN, 40);
    
    public static BufferedImage createPieceImage(String pieceName, boolean isWhite) {
        BufferedImage img = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2d.setFont(CHESS_FONT);
        g2d.setColor(isWhite ? Color.WHITE : Color.BLACK);
        
        String symbol = getUnicodeSymbol(pieceName);
        FontMetrics fm = g2d.getFontMetrics();
        int x = (60 - fm.stringWidth(symbol)) / 2;
        int y = ((60 - fm.getHeight()) / 2) + fm.getAscent();
        
        if (!isWhite) {
            g2d.setColor(Color.BLACK);
        }
        g2d.drawString(symbol, x, y);
        
        g2d.dispose();
        return img;
    }
    
    private static String getUnicodeSymbol(String pieceName) {
        switch (pieceName) {
            case "King": return "♔";
            case "Queen": return "♕";
            case "Rook": return "♖";
            case "Bishop": return "♗";
            case "Knight": return "♘";
            case "Pawn": return "♙";
            default: return "?";
        }
    }
} 