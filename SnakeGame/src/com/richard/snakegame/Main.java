package com.richard.snakegame;

import com.richard.snakegame.logic.SnakeGame;
import com.richard.snakegame.view.SnakeFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeFrame snakeFrame = new SnakeFrame();
            snakeFrame.pack();
            snakeFrame.setVisible(true);
            snakeFrame.tic();
        });
    }
}
