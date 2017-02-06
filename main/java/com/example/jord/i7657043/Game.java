package com.example.jord.i7657043;

/**
 * Created by Jordan Griffiths on 09/01/2017.
 */

public class Game {
    private NetwalkGrid grid;
    private int turns = 0;
    private int difficulty;

    //Player can eventually be a class, if we need to store more than playerName
    private String playerName;


    public Game(int diff, String playername)
    {
        this.turns = 0;
        this.difficulty = diff;
        this.playerName = playername;

        switch (diff)
        {
            case 1:
                grid = new NetwalkGrid(3,3);
                break;
            case 2:
                grid = new NetwalkGrid(5,5);
                break;
            case 3:
                grid = new NetwalkGrid(7,7);
                break;
        }
    }

    //Need method to check win

    public Integer nextTile(int x, int y)
    {
        return grid.tileToDraw(x,y);
    }

    public void takeTurn(int col, int row)
    {
        grid.rotateRight(col,row);
        turnIncrement();
    }

    public int getGridColumns()
    {
        return grid.getColumns();
    }

    public int getGridRows()
    {
        return grid.getRows();
    }

    public int getTurn()
    {
        return this.turns;
    }
    private void turnIncrement()
    {
        this.turns++;
    }


}
