package mainclass;

import java.io.Serializable;
import java.util.Scanner;

public class Grid  implements Serializable
{

    private Square[][] squares;
    private int length, width;

    Grid()
    {
        squares = new Square[10][10];
        this.length = 10;
        this.width = 10;
        for (int i = 0; i < this.length; i++) {
            for (int j = 0; j < this.width; j++) {
                squares[i][j] = new Square();
            }
        }

    }

    Grid(int x, int y)
    {
        this.length = x;
        this.width = y;
        squares = new Square[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                squares[i][j] = new Square();
               
            }
        }
    }

    public void printt()
    {
        for (int i = 0; i < length; i++) 
        {
            for (int j = 0; j < width; j++) 
            {
                System.out.print(squares[i][j].get_content() + " ");
            }
            System.out.println();
        }
    }

    public void edit(int x, int y, int c) 
    {
        squares[x][y].edit(c);

    }

    public int get_case(int x, int y)
    {
        return squares[x][y].get_content();
    }

    public int get_length()
    {
        return length;
    }

    public int get_width() 
    {
        return width;
    }

}
