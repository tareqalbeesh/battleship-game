
package mainclass;

import java.io.Serializable;

public class BattleshipMove implements Serializable{
    int x,y;
    BattleshipMove(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    public int get_x()
    { 
       return x;
    }
    public int get_y()
    {
        return y;
    }
}
