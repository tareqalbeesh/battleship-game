
package mainclass;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;


public abstract class Game implements Playable , Serializable
{
	
    protected ArrayList <Player> players = new ArrayList ();
  
    abstract  public class Judjment implements Serializable{};
 
    public boolean Subscribe(Player player)
    {
    	Iterator<Player> I = players.listIterator();
    	while(I.hasNext())
    	{
    		if(I.next()==player)
    		{
    			///throw exception
    			return false;
    		}
    	}	
        
        players.add(player);
        
        System.out.println("successfully subscribed");
        
        return true;
    }
 
    public void leave(Player player)
    {
        int i=0;
    	Iterator<Player> I = players.listIterator();
        while(I.hasNext())
        {
            if(I.next()==player)
               {players.remove(i);break;}
            i++;
        }
    }

    public abstract void start ();
    
    public abstract void end();
}
