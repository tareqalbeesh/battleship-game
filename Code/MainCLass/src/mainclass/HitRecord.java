package mainclass;

import java.io.Serializable;
import java.util.Calendar;

public class HitRecord implements Serializable{

	private	 Calendar time;
	private int x,y,result,score;
    HitRecord(int x ,int y,int result,int score)
	{
    	time.getInstance();
		this.time = time;
		this.x =x;
		this.y = y;
		this.result = result;
		this.score = score;
	}
    public int get_x()
    {
    	return x;
    }
    public int get_y()
    {
        return y;
    }
    public int get_result()
    {
    	return result;
    }
    public int get_score()
    {
    	return score;
    }

	
}
