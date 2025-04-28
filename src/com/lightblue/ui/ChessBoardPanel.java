package com.lightblue.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.lightblue.gameplay.Game;
import com.lightblue.common.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.image.BufferedImage;

public class ChessBoardPanel extends JPanel {
    private static final int SQUARE_SIZE = 60;
    private static final int BOARD_SIZE = 8 * SQUARE_SIZE;
    
    private Game game;
    private Position selectedPosition;
    private Map<String, BufferedImage> pieceImages;
    
    public ChessBoardPanel(Game game) {
        this.game = game;
        this.selectedPosition = null;
        setPreferredSize(new Dimension(BOARD_SIZE, BOARD_SIZE));
        createPieceImages();
        setupMouseListener();
    }
    
    private void createPieceImages() {
        pieceImages = new HashMap<>();
        String[] pieces = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "King"};
        
        for (String piece : pieces) {
            pieceImages.put("White" + piece, ChessPieceRenderer.createPieceImage(piece, true));
            pieceImages.put("Black" + piece, ChessPieceRenderer.createPieceImage(piece, false));
        }
    }
    
    private void setupMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int file = e.getX() / SQUARE_SIZE;
                int rank = 7 - (e.getY() / SQUARE_SIZE);
                handleSquareClick(new Position(file, rank));
            }
        });
    }
    
    private void handleSquareClick(Position clickedPos) {
        Board currentBoard = game.getCurrentBoard();
        
        if (selectedPosition == null) {
            Piece piece = currentBoard.getPieceAtPos(clickedPos);
            if (piece != null && piece.getMycolor() == game.getCurrentMoveColor()) {
                selectedPosition = clickedPos;
                repaint();
            }
        } else {
            List<Move> validMoves = Board.getAllValidMoves(currentBoard, game.getCurrentMoveColor());
            Move selectedMove = null;
            
            for (Move move : validMoves) {
                for (Map.Entry<Tuple, Tuple> entry : move.getMap().entrySet()) {
                    if (entry.getKey().getPos().equals(selectedPosition) &&
                        entry.getValue().getPos().equals(clickedPos)) {
                        selectedMove = move;
                        break;
                    }
                }
                if (selectedMove != null) break;
            }
            
            if (selectedMove != null) {
                game.applyMoveToGame(selectedMove);
                
                // If game is still playable, let engine make its move
                if (game.isGamePlayable()) {
                    Move engineMove = game.generateNextMove();
                    game.applyMoveToGame(engineMove);
                }
            }
            
            selectedPosition = null;
            repaint();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
        if (selectedPosition != null) {
            highlightSquare(g, selectedPosition);
        }
    }
    
    private void drawBoard(Graphics g) {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                boolean isLight = (file + rank) % 2 == 0;
                g.setColor(isLight ? new Color(240, 217, 181) : new Color(181, 136, 99));
                g.fillRect(file * SQUARE_SIZE, (7-rank) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
    
    private void drawPieces(Graphics g) {
        Board board = game.getCurrentBoard();
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Position pos = new Position(file, rank);
                Piece piece = board.getPieceAtPos(pos);
                if (piece != null) {
                    drawPiece(g, piece, file, rank);
                }
            }
        }
    }
    
    private void drawPiece(Graphics g, Piece piece, int file, int rank) {
        String color = piece.getMycolor() == Piece.Color.WHITE ? "White" : "Black";
        String pieceName = piece.getClass().getSimpleName();
        String key = color + pieceName;
        BufferedImage img = pieceImages.get(key);
        
        if (img != null) {
            g.drawImage(img, file * SQUARE_SIZE, (7-rank) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, this);
        }
    }
    
    private void highlightSquare(Graphics g, Position pos) {
        g.setColor(new Color(255, 255, 0, 100));
        g.fillRect(pos.getX() * SQUARE_SIZE, (7-pos.getY()) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }
} 