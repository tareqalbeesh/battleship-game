
package mainclass;

import static java.lang.Thread.sleep;

import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
public abstract class  ComputerPlayer extends BattleshipPlayer implements Runnable,Serializable{
    private App GUI;
    
    public void add_BomB(int num )
    {
    	int  x,y;
    	Random r = new Random();
    	x= r.nextInt(this.player_data.get_grid_length());
    	y =r.nextInt(this.player_data.get_grid_width());
    	while(this.player_data.get_grid_case(x, y)== this.player_data.get_num_of_ships())
    	{
    		x= r.nextInt(this.player_data.get_grid_length());
        	y =r.nextInt(this.player_data.get_grid_width());
    	}
    	this.player_data.edit(x, y, this.player_data.get_num_of_ships());
    	//this.player_data.print_grid();
    	this.B.add(new BomB(this,  x ,  y , this.GUI,num));
    }
    
    public void set_gui(App GUI)
    {
        this.GUI = GUI;
    }
    protected AbstractComputerStrategy currentStrategy;	
    BattleshipMove mm ; 
    private Random r = new Random ();
    int x , i ;
        ComputerPlayer(String name)  {   super(name);    }
        ComputerPlayer(String name,int grid_length,int grid_width)  {   super(name,grid_length,grid_width);    }
	public BattleshipMove GetNextMove() 
        { 
		 if (x==10000) return null;
		return mm;
		
        }
 
	public void putShips(){
            int length = this.player_data.get_grid_length() , width = this.player_data.get_grid_width();
            int nOfShips = player_data.get_num_of_ships() , k = 0 , x1 , y1 , x2 , y2 ,z=0;
            Random random = new Random();
            while (k!=nOfShips)
            {
                x1 = random.nextInt(length-1);
                y1 = random.nextInt(width-1);
                x2 = player_data.shipsArray[k].returnLenght();
                y2 = player_data.shipsArray[k].returnWidth();
                if (this.player_data.get_grid_case(x1, y1)== -1)
                {
                    ////1111111
                   if (x1+x2-1<length && y1+y2-1<width)
                   {   
                       label :for (int i=x1 ; i<x1+x2 ; i++)
                           for (int j=y1 ; j<y1+y2 ; j++)
                               if (player_data.get_grid_case(i, j)!=-1)
                               {  z=1;
                                    break label ;  
                               }
                       if (z==0)
                       {
                        for (int i=x1 ; i<x1+x2 ; i++)
                           for (int j=y1 ; j<y1+y2 ; j++)
                               this.player_data.edit(i, j,k);
                        k++;
                       }
                   }
                    if (x1+y2-1<length && y1+x2-1<width && z==1)
                   {
                       ////2222222
                       z=0;
                       label :for (int i=x1 ; i<x1+y2 ; i++)
                           for (int j=y1 ; j<y1+x2 ; j++)
                               if (player_data.get_grid_case(i, j)!=-1)
                               {  z=1;
                                    break label ;  
                               }
                       if (z==0)
                       {    
                       for (int i=x1 ; i<x1+y2 ; i++)
                           for (int j=y1 ; j<y1+x2 ; j++)
                               this.player_data.edit(i, j,k);
                        k++;
                       }
                       
                   }
                   
                   }
                   
                }
                
            }

    @Override
    public void run() {
        x = r.nextInt(11);
        x=x*1000;
        int i= 0 ;
        
        label: while (i*1000<=x){
           // GUI.setVisible(false);
         mm =  currentStrategy.GetNextMove();
		while( player_data.get_draft_case(mm.x,mm.y)!=-1)
		{
			 mm =  currentStrategy.GetNextMove();
		}
            try {
                sleep(0);
            } catch (InterruptedException ex) {
                Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        i++;
        
        }
        
        
    }
            
        }	
