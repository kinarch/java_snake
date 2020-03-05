package com.richard.snakegame.view;

import com.richard.snakegame.logic.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeFrame extends JFrame {

    final int rectSize = 20;
    SnakeGame snakeGame;
    JPanel board;

    public SnakeFrame() {

        super("Snake Game");
        snakeGame = SnakeGame.create();
        final int width = snakeGame.NUMBER_X * rectSize;
        final int heigth = snakeGame.NUMBER_Y * rectSize;

        //  create the window
        setMinimumSize(new Dimension(width, heigth));
        setMaximumSize(new Dimension(width, heigth));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setFocusable(true);

        //  #   PANE
        board = new JPanel();
        board.setPreferredSize(new Dimension(width, heigth));
        board.setLayout(null);
        board.setBackground(Color.BLACK);
        setContentPane(board);

        //  #   MENU BAR
        addMenuBar();

        //  #   GAME
        newGame();

        //  #   KEYBOARD
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                final int keyCode = e.getKeyCode();
                int direction = 0;
                switch (keyCode) {
                    case KeyEvent.VK_RIGHT:
                        direction = snakeGame.RIGHT;
                        break;
                    case KeyEvent.VK_LEFT:
                        direction = snakeGame.LEFT;
                        break;
                    case KeyEvent.VK_DOWN:
                        direction = snakeGame.DOWN;
                        break;
                    case KeyEvent.VK_UP:
                        direction = snakeGame.UP;
                        break;
                    case KeyEvent.VK_SPACE:
                        snakeGame.pause();
                        break;
                }

                if (direction != 0) {
                    snakeGame.setDirection(direction);
                }
            }
        });
    }

    private void draw() {


        int[][] snakeWorld = snakeGame.getWorld();
//        snakeGame.displayWorldRepresentation();
//        System.out.println("------------------------------------------------------------------------------------------------------------");

        //  remove and draw...
        board.removeAll();
        board.updateUI();

        int value = 0;

        for (int x = 0; x < snakeWorld.length; x++) {
            for (int y = 0; y < snakeWorld[x].length; y++) {
                if (snakeWorld[x][y] != 0) {

                    value++;
                    JLabel rect = new JLabel();
                    rect.setSize(new Dimension(rectSize, rectSize));
                    rect.setBounds(x * rectSize, y * rectSize, rectSize, rectSize);
                    rect.setOpaque(true);
                    switch (snakeWorld[x][y]) {
                        case 1:
                            rect.setBackground(Color.RED);
                            break;
                        case 2:
                            rect.setBackground(Color.CYAN);
                            break;
                    }
                    board.add(rect);
                }
            }
        }
        board.updateUI();

        if (snakeGame.isGameOver()) {
            JLabel message = new JLabel("GAME OVER", SwingConstants.CENTER);
            message.setSize(new Dimension(100, 100));
            message.setBounds(0, 0, 100, 100);
            message.setForeground(Color.WHITE);
            board.add(message);
        }
    }

    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        {
            //  --  file
            JMenu menuFile = new JMenu("File");
            menuFile.setMnemonic(KeyEvent.VK_F);
            {
                //  --  --  new game
                JMenuItem menuItemNewGame = new JMenuItem("New Game (ALT+G)");
                menuItemNewGame.setMnemonic(KeyEvent.VK_G);
                menuItemNewGame.addActionListener(e -> newGame());
                menuFile.add(menuItemNewGame);
                menuBar.add(menuFile);
                setJMenuBar(menuBar);
                //  --  --  exit
                JMenuItem menuItemExit = new JMenuItem("Exit (ALT+Q)");
                menuItemExit.setMnemonic(KeyEvent.VK_Q);
                menuItemExit.addActionListener(e -> System.exit(0));
                menuFile.add(menuItemExit);
                menuBar.add(menuFile);
            }
        }
        setJMenuBar(menuBar);
    }


    public void newGame() {
        snakeGame.init();
        tic();
    }

    public void tic() {
        Thread thread = new Thread(() -> {
            while (!snakeGame.isGameOver()) {
                try {
                    Thread.sleep(200);
                    snakeGame.update();
                    draw();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
        thread.start();
    }
}
