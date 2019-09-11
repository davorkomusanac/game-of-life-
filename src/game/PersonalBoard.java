/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gameoflife.*;
import java.util.Random;

/**
 *
 * @author Davor
 */
public class PersonalBoard extends GameOfLifeBoard {

    private int width;
    private int height;

    public PersonalBoard(int width, int height) {
        super(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void initiateRandomCells(double probabilityForEachCell) {
        Random random = new Random();
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                double finalProbability = random.nextDouble() + probabilityForEachCell;
                if (finalProbability >= 1) {
                    this.turnToLiving(x, y);
                }
            }
        }
    }

    @Override
    public boolean isAlive(int x, int y) {
        try {
            return super.getBoard()[x][y];
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void turnToLiving(int x, int y) {
        try {
            super.getBoard()[x][y] = true;
        } catch (Exception e) {
            System.out.println("Out of bounds.");
        }
    }

    @Override
    public void turnToDead(int x, int y) {
        try {
            super.getBoard()[x][y] = false;
        } catch (Exception e) {
            System.out.println("Out of bounds.");
        }
    }

    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
        int NumberOfLivingNeighbours = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (this.isAlive(i, j)) {
                    NumberOfLivingNeighbours++;
                }
            }
        }
        if (this.isAlive(x, y)) {
            NumberOfLivingNeighbours--;
        }
        return NumberOfLivingNeighbours;
    }

    @Override
    public void manageCell(int x, int y, int NumberOfLivingNeighbours) {
        if (NumberOfLivingNeighbours<2) {
            this.turnToDead(x, y);
        }
        if (NumberOfLivingNeighbours>3) {
            this.turnToDead(x, y);
        }
        if (NumberOfLivingNeighbours==3) {
            this.turnToLiving(x, y);
        }
    }

}
