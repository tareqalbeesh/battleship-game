package mainclass;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HumanPlayer extends BattleshipPlayer  implements Serializable{ 

    private App guiForShips , guiForShow ;
    
    
    HumanPlayer(String name) 
    { 
        super(name);
       guiForShow = new App (name);
        guiForShips = new App ("Add Ships");
       
    }
    HumanPlayer(String name,int grid_length,int grid_width) 
    { 
        super(name,grid_length,grid_width);
        guiForShow = new App (name);
        guiForShips = new App (" ");
       
    }
    
    
    
    
    
    public BattleshipMove GetNextMove() 
    {
      return null;
    }

    
    

    public void putShips() 
    {
        
        
        //JFrame f = new JFrame();
    
    
    
    player_data.shipsArray = new Ship[player_data.get_num_of_ships()];
    
       
    
    guiForShips.addships( (BattleshipGame) currgame,this,0);

    
    
    }
    

}
