package com.richard.snakegame.logic;

import com.richard.snakegame.logic.objects.Food;
import com.richard.snakegame.logic.objects.Snake;
import com.richard.snakegame.logic.objects.SnakeTail;

import java.util.ArrayList;

public class SnakeGame {

    /**
     * Direction
     */
    public final int RIGHT = 1;
    public final int LEFT = 2;
    public final int DOWN = 3;
    public final int UP = 4;

    /**
     * To create the int[][] world
     */
    public final int NUMBER_X = 30;
    public final int NUMBER_Y = 20;

    /**
     * The default points by eaten food
     */
    private final int DEFAULT_POINTS_BY_FOOD = 50;

    /**
     * The int entity for the world
     */
    public final int EMPTY = 0;
    public final int SNAKE = 1;
    public final int FOOD = 2;

    /**
     * The list of the food
     */
    private ArrayList<Food> foods;

    /**
     * The current number of foods
     * It can be increase with the level
     */
    private int numberOfFood = 1;

    /**
     * The Snake
     */
    private Snake snake;

    /**
     * The representation of the world
     */
    private int[][] world;


    /**
     * The current direction is put on snake to move it
     * The next direction is put on current direction only if is not a back move (e.g. right => left)
     */
    int currentDirection;
    int nextDirection;

    /**
     * If is true, stop the current game
     */
    private boolean gameOver;

    /**
     * If is true, pause the game
     * If is false, resume the game
     */
    private boolean pause;

    /**
     * the score is increasing after the snake eat food
     */
    private int score;

    /**
     * the number of eaten food by the snake
     */
    private int numberOFEatenFood;


    /**
     * The constructor is private
     * To prevent the multi SnakeGame instance, it can be exist one instance by execution
     * This class can be instanciate only with the create() static method
     * Cause SnakeGame is a singleton class model
     */
    private SnakeGame() {
    }

    /**
     * The instance is true if the SnakeGame has been instanciated
     */
    public static SnakeGame instance;


    /**
     * The static constructor
     *
     * @return a instance of SnakeGame
     */
    public static SnakeGame create() {

        if (instance == null) {
            instance = new SnakeGame();
            return instance;
        }
        return null;
    }

    public int[][] getWorld() {
        return world;
    }

    public int getScore() {
        return score;
    }

    //  =   =   =   GAME TOOLS

    /**
     *  set the next direction of the snake
     */
    public void setDirection(int direction) {
        switch (direction) {
            case RIGHT:
                if (currentDirection != LEFT) {
                    nextDirection = RIGHT;
                }
                break;
            case LEFT:
                if (currentDirection != RIGHT) {
                    nextDirection = LEFT;
                }
                break;
            case DOWN:
                if (currentDirection != UP) {
                    nextDirection = DOWN;
                }
                break;
            case UP:
                if (currentDirection != DOWN) {
                    nextDirection = UP;
                }
                break;
        }
    }

    private void addGameObjects() {

        world = new int[NUMBER_X][NUMBER_Y];

        //  is snake is out of world ?
        try {
            for (SnakeTail s : snake.getBody()) {
                int x = s.getX();
                int y = s.getY();
                if (x >= NUMBER_X) x = 0;
                if (x < 0) x = NUMBER_X - 1;
                if (y >= NUMBER_Y) y = 0;
                if (y < 0) y = NUMBER_Y - 1;
                s.setPos(x, y);
                world[s.getX()][s.getY()] = SNAKE;
            }
        } catch (IndexOutOfBoundsException e) {
            putGameOver();
        }

        //  is food is out of world ?
        try {
            for (Food f : foods) {
                world[f.getX()][f.getY()] = FOOD;
            }
        } catch (IndexOutOfBoundsException e) {
            //  AIE ça va faire mal XD
            System.exit(0);
        }
    }


    public void displayWorldRepresentation() {
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[x].length; y++) {
                System.out.print(world[x][y] + " ");
            }
            System.out.println();
        }
    }

    private void increaseScore() {
        score += DEFAULT_POINTS_BY_FOOD * numberOFEatenFood;
    }

    /**
     * Generate the foods with a random coordinates
     */
    private void generateFood() {

        int randX;
        int randY;

        for (int i = foods.size(); i < numberOfFood; i++) {

            do {
                randX = (int) (Math.random() * NUMBER_X);
                randY = (int) (Math.random() * NUMBER_Y);
            } while (world[randX][randY] != EMPTY);

            foods.add(new Food(randX, randY));
        }

    }

    /**
     * stop the game
     */
    private void putGameOver() {
        System.out.println("GameOver");
        gameOver = true;
    }

    /**
     * @return the game over boolean value
     */
    public boolean isGameOver() {
        return gameOver;
    }

    //  =   =   =   GAME LYFE CYCLE

    /**
     * Init the game
     */
    public void init() {

        int midX = (int) (NUMBER_X / 2) - 1;
        int midY = (int) (NUMBER_Y / 2) - 1;

        foods = new ArrayList<>();
        snake = new Snake(midX, midY);
        world = new int[NUMBER_X][NUMBER_Y];
        gameOver = false;
        numberOFEatenFood = 0;
        numberOfFood = 1;
        score = 0;
        generateFood();
        setDirection(RIGHT);
        addGameObjects();
    }

    /**
     * Update the game state
     */
    public void update() {

        if (isGameOver()) {
            return;
        }

        currentDirection = nextDirection;
        snake.move(currentDirection);

        //  is snake eating his tail ?
        if (snake.isOnHisTail()) {
            putGameOver();
            return;
        }

        //  is snake eating food ?
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            if (food.getX() == snake.getHead().getX() && food.getY() == snake.getHead().getY()) {
                //  remove food, generate new food, increase score
                food = foods.remove(i);
                snake.eat(food.getX(), food.getY());
                generateFood();
                numberOFEatenFood++;
                if (numberOFEatenFood % 10 == 0) {
                    numberOfFood++;
                }
                increaseScore();
                System.out.println(score);
            }
        }

        addGameObjects();
    }

    /**
     * The loop game
     */
    public void play() {
        // while(true) update();
    }
}
