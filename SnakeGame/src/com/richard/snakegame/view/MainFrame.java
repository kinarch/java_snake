//package com.richard.snakegame.view;
//
//import com.richard.snakegame.logic.SnakeGame;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class MainFrame {
//
//    public JPanel panel1;
//    private JPanel scorePanel;
//    private JPanel boardPanel;
//    final int rectSize = 20;
//    SnakeGame snakeGame;
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("MainFrame");
//        frame.setContentPane(new MainFrame().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    private void draw() {
//
//
//        int[][] snakeWorld = snakeGame.getWorld();
//        // snakeGame.displayWorldRepresentation();
//
//        //  remove and draw...
//        board.removeAll();
//        board.repaint(1000);
//
//        for (int x = 0; x < snakeWorld.length; x++) {
//            for (int y = 0; y < snakeWorld[x].length; y++) {
//                if (snakeWorld[x][y] != 0) {
//                    JLabel rect = new JLabel();
//                    rect.setSize(new Dimension(rectSize, rectSize));
//                    rect.setBounds(x * rectSize, y * rectSize, rectSize, rectSize);
//                    switch (snakeWorld[x][y]) {
//                        case 1:
//                            rect.setBackground(Color.RED);
//                            break;
//                        case 2:
//                            rect.setBackground(Color.CYAN);
//                            break;
//                    }
//                    rect.setOpaque(true);
//                    board.add(rect);
//                }
//            }
//        }
//
//        if (snakeGame.isGameOver()) {
//            JLabel message = new JLabel("GAME OVER", SwingConstants.CENTER);
//            message.setSize(new Dimension(100, 100));
//            message.setBounds(0, 0, 100, 100);
//            message.setForeground(Color.WHITE);
//            board.add(message);
//        }
//    }
//}
