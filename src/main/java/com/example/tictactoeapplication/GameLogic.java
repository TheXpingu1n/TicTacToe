package com.example.tictactoeapplication;
public class GameLogic {
    private int[][] grid = new int[3][3];
    // 0 notplayed,1 x, 2 o

    public int getGrid(int index)
    {
        if(index <= 2)
        {
            if(index == 0)
                return grid[0][index];
            if(index == 1)
               return grid[0][index];
            if(index == 2)
              return  grid[0][index];
        }
        if(index <= 5)
        {
            if(index == 3)
               return grid[1][0];
            if(index == 4)
                return grid[1][1];
            if(index == 5)
                return grid[1][2];

        }
        if(index <= 8)
        {
            if(index == 6)
                return grid[2][0];
            if(index == 7)
               return  grid[2][1];
            if(index == 8)
                return grid[2][2];

        }
        return -1;
    }
    public void setGrid(int index, int num) {
        if(index <= 2)
        {
            if(index == 0)
                grid[0][index] = num;
            if(index == 1)
                grid[0][index] = num;
            if(index == 2)
                grid[0][index] = num;
        }
        if(index <= 5)
        {
            if(index == 3)
                grid[1][0] = num;
            if(index == 4)
                grid[1][1] = num;
            if(index == 5)
                grid[1][2] = num;

        }
        if(index <= 8)
        {
            if(index == 6)
                grid[2][0] = num;
            if(index == 7)
                grid[2][1] = num;
            if(index == 8)
                grid[2][2] = num;

        }
    }
    public int XorO(String who)
    {
        if(who.equals("X"))
            return 1;
        return 2;
    }
    // 0 no winner
    // 1 X wins
    // 2 O wins
    private int checkRow()
    {
        int rsum = 0;
        boolean draw = false;
        int winner = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(grid[i][j] == 0) {
                    draw = true;
                    break;
                }
                rsum += grid[i][j];
            }
            if(rsum == 6 && !draw)
            {
                winner = 2;
                break;
            }
            if(rsum == 3 && !draw)
            {
                winner = 1;
                break;
            }
            rsum = 0;
            draw = false;
        }
        return winner;
    }
    private int checkcol()
    {
        int csum = 0;
        int winner = 0;
        boolean draw = false;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if(grid[j][i] == 0) {
                    draw = true;
                    break;
                }
                csum += grid[j][i];
            }
            if(csum == 6 && !draw)
            {
                winner = 2;
                break;
            }
            if(csum == 3 && !draw)
            {
                winner = 1;
                break;
            }
            csum = 0;
            draw = false;

        }
        return winner;
    }
    private int checkdiagonal()
    {
        boolean draw = false;
        int sum = 0;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(i == j && grid[i][j] == 0)
                {
                    draw = true;
                    break;
                }
                if(i == j)
                {
                    sum += grid[i][j];
                }
            }
            if(draw)
                break;
        }
        if(sum == 6 && !draw)
            return 2;
        if(sum == 3 && !draw)
            return 1;

        draw = false;
        sum = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if(i+j == 2 && this.grid[i][j] == 0)
                {
                    draw = true;
                    break;
                }
                if(i + j == 2)
                {
                    sum += this.grid[i][j];
                }
            }
            if(draw)
                break;
        }
        if(sum == 6 && !draw)
                return 2;
        if(sum == 3 && !draw)
            return 1;
        return 0;
    }
    public int tracker()
    {
        int winner = checkRow();
        if(winner != 0)
            return winner;
        winner = checkcol();
        if(winner != 0)
            return winner;
        winner = checkdiagonal();
        if(winner != 0)
            return winner;

        return winner;
    }
    public boolean isDraw()
    {
        int result = tracker();
        boolean res = true;
        if(result == 0)
        {
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if(grid[i][j] == 0)
                    {
                        res = false;
                        return res;
                    }
                }
            }
        }
        return res;
    }
    public void printAtConsole()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(this.grid[i][j]);
            }
            System.out.println();
        }
    }
}
