package com.richard.snakegame.logic.objects;

import java.util.ArrayList;

public class Snake {

    private ArrayList<SnakeTail> body;

    public Snake(int x, int y) {
        body = new ArrayList();
        body.add(new SnakeTail(x, y));
        body.add(new SnakeTail(x + 1, y));
    }

    public ArrayList<SnakeTail> getBody() {
        return body;
    }

    public SnakeTail getHead() {
        return body.get(0);
    }

    public void eat(int x, int y) {
        body.add(new SnakeTail(x, y));
    }

    public void move(int direction) {

        int x;
        int y;

        for (int i = body.size() - 1; i > 0; i--) {
            SnakeTail tail = body.get(i);
            SnakeTail previousTail = body.get(i - 1);
            x = previousTail.getX();
            y = previousTail.getY();
            tail.setPos(x, y);
        }

        x = getHead().getX();
        y = getHead().getY();

        switch (direction) {
            case 1:
                x++;
                break;
            case 2:
                x--;
                break;
            case 3:
                y++;
                break;
            case 4:
                y--;
                break;
        }

        getHead().setPos(x, y);
    }

    public boolean isOnHisTail() {

        for (int i = 1; i < body.size(); i++) {
            SnakeTail tail = body.get(i);
            if (getHead().getX() == tail.getX() && getHead().getY() == tail.getY()) {
                return true;
            }
        }
        return false;
    }
}
