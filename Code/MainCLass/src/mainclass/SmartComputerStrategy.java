package mainclass;

import java.io.Serializable;
import java.util.Random;

public class SmartComputerStrategy extends AbstractComputerStrategy implements Serializable{

    private int length, width , z=0 , x , y , k=0; 

    SmartComputerStrategy(int length, int width) 
    {
        this.length = length;
        this.width = width;
    }
    public void editZ(BattleshipMoveResult res)
    {
        if (res.get_hit_case()!=0 && z==0)z=1;
        else if (res.get_hit_case()==0 && z==1)z=2;
        else if (res.get_hit_case()==0 && z==2){z=3 ;k=1;}
        else if (res.get_hit_case()==0 && z==3)z=4;
        
    }   
    @Override
    public BattleshipMove GetNextMove()
    {
    	System.out.println("Smart move");
        if (z==0)
        {
            k=1;
            x = new Random().nextInt(length-1);
            y = new Random().nextInt(width-1);
            return new BattleshipMove (x,y);
        }
        if (k==5)k=1;
        if (z==1)
        {
            if (x+k>=length)z=2;
            else return new BattleshipMove (x+(k++),y);
        }
        if (z==2)
        {
            if (x-k<0)z=3;
            else return new BattleshipMove (x-(k++),y);
        }
        if (z==3)
        {
            if (y+k>=width)z=4;
            else return new BattleshipMove (x,y+(k++));
        }
        if (z==4)
        {
            if (y-k<0)z=0;
            else return new BattleshipMove (x,y-(k++));
        }

       return null ; 
    }

}
