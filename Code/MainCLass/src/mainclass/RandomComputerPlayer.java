
package mainclass;

import java.io.Serializable;

public class RandomComputerPlayer extends ComputerPlayer  implements Serializable{
 
    RandomComputerPlayer(String name)
    {
    	super(name) ; currentStrategy = new RandomComputerStrategy(player_data.get_grid_length(),player_data.get_grid_width());
    }
    RandomComputerPlayer(String name,int grid_length,int grid_width)
    {
    	super(name,grid_length,grid_width) ; currentStrategy = new RandomComputerStrategy(grid_length,grid_width);
        
    }
    
    
        
}
