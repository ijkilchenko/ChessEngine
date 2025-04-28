package com.lightblue.ui;

import javax.swing.*;
import java.awt.*;
import com.lightblue.gameplay.Game;
import com.lightblue.common.Position;
import com.lightblue.common.Move;
import com.lightblue.common.Board;
import com.lightblue.common.Piece;
import com.lightblue.common.Tuple;
import java.util.HashMap;
import java.util.List;

public class ChessGUI extends JFrame {
    private ChessBoardPanel boardPanel;
    private Game game;
    private JLabel statusLabel;

    public ChessGUI() {
        game = new Game();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new ChessBoardPanel(game);
        add(boardPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("White's turn");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGUI gui = new ChessGUI();
            gui.setVisible(true);
        });
    }
} 