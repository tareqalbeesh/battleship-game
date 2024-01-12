/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import java.io.Serializable;

public class SmartComputerPlayer extends ComputerPlayer implements Serializable{
    
    SmartComputerPlayer(String name)
    {
    	super(name) ; currentStrategy = new SmartComputerStrategy(player_data.get_grid_length(),player_data.get_grid_width());
    }
    SmartComputerPlayer(String name,int grid_length,int grid_width)
    {
    	super(name,grid_length,grid_width) ; currentStrategy = new SmartComputerStrategy(grid_length,grid_width);
        
    }
    SmartComputerStrategy getStrategey (){
        
        return (SmartComputerStrategy) currentStrategy;
    }
    
}
