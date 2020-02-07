package com.richard.snakegame.logic.objects;

public class GameObject {

    private int x;
    private int y;

    public GameObject(int x, int y) {
//        if (x < 0 || y < 0) {
//            throw new Exception("Illegale coordinates");
//        }
        this.x = x;
        this.y = y;
    }

    public void setPos(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }
}
