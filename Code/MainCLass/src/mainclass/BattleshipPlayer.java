
package mainclass;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JOptionPane;
import java.util.*;

public abstract class  BattleshipPlayer extends Player implements Serializable{
    
	public class BattleshipPlayerData extends PlayerData
	{
		
        int num_of_ships; 
		private Grid grid;
		private Grid draft;
		
		private ArrayList <BattleshipMoveResult> my_hits;
                public  Ship [] shipsArray ;
		BattleshipPlayerData(String name)
                {			
			this.set_name(name); grid = new Grid();	draft = new Grid();
		}
		public Grid get_grid()
		{
	return grid;
		}
		public void add_hit(BattleshipMoveResult movve)
		{
			my_hits.add(movve);
		}
		
                BattleshipPlayerData(String name,int grid_length,int grid_width)
                {			
                this.set_name(name);
                grid = new Grid(grid_length,grid_width);
                draft = new Grid(grid_length,grid_width);
               my_hits =  new ArrayList <BattleshipMoveResult>() ;
                }
		
                public void edit(int x ,int y,int c)   { 
                    this.grid.edit(x, y, c);
                }
                
                public int get_grid_case(int x,int y) { return grid.get_case(x, y); }
               
                public int get_draft_case(int x,int y) { return draft.get_case(x, y); }
		
                public void edit_draft(int x ,int y,int c) { this.draft.edit(x, y, c);	}
		
                public int get_grid_length() {  return grid.get_length(); }
		
                public int get_grid_width() {   return grid.get_width(); }
               
                public void set_num_of_ships(int num_of_ships)  {
                    this.num_of_ships = num_of_ships;
                }
               
                public int get_num_of_ships()  {
                    return num_of_ships;
                }
                
                public void print_grid()  { 
                    grid.printt();
                }
                public void print_draft()  { 
                    draft.printt();
                }
                public String get_name () {
                    return name;
                }
                public ArrayList<BattleshipMoveResult> get_hits()
                {
                	return my_hits;
                }
                
		          
                
	}
        
        
        
        
	BattleshipPlayerData player_data;
	ArrayList <BomB> B = new ArrayList (); 
        
   
        
    BattleshipPlayer(String name)
    { 
        player_data = new BattleshipPlayerData(name);
    }
    
    BattleshipPlayer(String name,int grid_length,int grid_width) 
    {
        player_data = new BattleshipPlayerData(name,grid_length,grid_width);
    }
    
    public abstract BattleshipMove GetNextMove() ;
    
    
    public void NotifyPlayer(BattleshipMoveResult result)
    { 
    	int x = result.get_x(),y=result.get_y(),z=result.get_hit_case();
    	player_data.add_hit(result);
    	if(result.get_hit_case()==-10)
    	{
            JOptionPane.showMessageDialog(null,"hitted before","result",JOptionPane.PLAIN_MESSAGE);
              
    	}
        if (result.get_hit_case()==-1 ) 
        {
            JOptionPane.showMessageDialog(null,"Water!!","result",JOptionPane.PLAIN_MESSAGE);
       this.player_data.edit_draft(result.get_x(), result.get_y(),-3);
       //// the case of hitten water in my draft
        }
        else if(result.get_hit_case()==this.player_data.get_num_of_ships())
        {
        	JOptionPane.showMessageDialog(null,"BomB!!!","result",JOptionPane.PLAIN_MESSAGE);
        	
        	this.player_data.edit_draft(x, y, z);
        	
        }
        else 
        {
              x= this.player_data.shipsArray[result.get_hit_case()].returnSize();
             this.player_data.edit_draft(result.get_x(), result.get_y(),result.get_hit_case());
             if (x!=0)
           JOptionPane.showMessageDialog(null,"Part Of"+player_data.shipsArray[result.get_hit_case()].returnName(),"result",JOptionPane.PLAIN_MESSAGE);
           if (x==0){
               JOptionPane.showMessageDialog(null,"Part Of"+player_data.shipsArray[result.get_hit_case()].returnName(),"result",JOptionPane.PLAIN_MESSAGE);
              BattleshipGame m = (BattleshipGame)currgame;
              m.BSJ.edit(this);
           }
        }
    }
    
    
    
    
    public BattleshipMoveResult AcceptPlayerMove(BattleshipMove move)
    {
        int x = move.get_x() ,   y = move.get_y()  , z = player_data.get_grid_case(x, y) ;
        if(z==-2 || z==-3)
        {
        	return new BattleshipMoveResult(x,y,-10);
        }
        else if (z!=-1  && z!=-2 && z!=this.player_data.get_num_of_ships())
        {
         player_data.edit(x, y, -2);
         
         return new BattleshipMoveResult(x,y,z);
        }
        else if(z==this.player_data.get_num_of_ships())
        {
            for (int i=0 ; i<B.size(); i++){
                if (B.get(i).x == x && B.get(i).y == y )
                    B.get(i).stop();
            }
                player_data.edit(x, y, -2);
        	 return new BattleshipMoveResult(x,y,z);
        	
        }
        else ///z==-1 
        {
        		
        		 player_data.edit(x, y, -3);
        		
        	return new BattleshipMoveResult(x,y,-1);
        }
        		
    }
 
    
    
    abstract public void putShips();
    
}
