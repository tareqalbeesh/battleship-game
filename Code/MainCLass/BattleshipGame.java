
package mainclass;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.Scanner;

public class BattleshipGame extends Game{
    
    
    
    private BattleshipPlayer currplayer;
 
    
      BattleshipGame()
    {
       new RandomComputerPlayer().SubscribeTo(this);
    
    
    }
    
    public void start()
    {	
        if(players.size()==2)
            RunGame();
        else 
        {
        	private Scanner s = new Scanner(System.in);
        	String ss;
        	System.out.println("the number of player should be 2 or more!");
        	System.out.println("would you like to add player?");
        	ss= s.nextLine();
        	if(ss=="Yes")
        	{
                int age;
                System.out.println("Enter the player name: ");
                ss = s.nextLine();
                System.out.println("Enter the Player age: ");
                age = s.nextInt();
        		BattleshipPlayer p = new HumanPlayer(ss,age);
        	    p.SubscribeTo(this);
        	}
        	start();
        }
    }
    public void end()
    {
        
    }
    public void RunGame()
    {
    	
    	
        while(true)
        {
        	
        	
        }
        
        
    }
    
    
    
}
