
package mainclass;

import java.io.Serializable;

public abstract class Player implements Serializable{
    
    protected Game currgame;    
    
    
    
    abstract public class PlayerData implements Serializable
    { 
    	protected String name;
    	public void set_name(String name) { this.name = name; }
    	public String get_name() { return name; }
    	
    }
    
    
    
    public void SubscribeTo(Game game)  { if(game.Subscribe(this))  currgame = game; }
    
    public void LeaveGame() { currgame.leave(this); }
    
}