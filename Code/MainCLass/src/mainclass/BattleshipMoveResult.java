
package mainclass;

import java.io.Serializable;
import java.util.Calendar;

public class BattleshipMoveResult implements Serializable{

    private int x,y;
    int hit_case;
    Calendar time ;
    // -1 water 
    // n 
   
    BattleshipMoveResult(int x,int y,int hit_case)
    { 
    	this.x = x;
    	this.y = y;
    	this.hit_case = hit_case;
    	this.time.getInstance();
    }
    public Calendar get_time()
    {
    	return time;
    }
    public int get_hit_case()
    {
    	return hit_case;
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
