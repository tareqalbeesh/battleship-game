/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainclass.App;
import mainclass.BattleshipPlayer;
import mainclass.BattleshipPlayer;


public class BomB extends Thread implements Serializable {
    
    private BattleshipPlayer mine ;
    private Random r = new Random ();
    int x , y ;
    App Gui;
    int num ;
    
    BomB (BattleshipPlayer P , int x , int y , App G,int num){
        Gui = G ; 
        mine = P;
        this.num=num;
        P.player_data.edit(x, y, P.player_data.get_num_of_ships());
        this.x=x ; this.y=y ;
    }
    public int get_num()
    {
    	return num;
    }
    
    
    public void run (){
        
        int z  ;
        
        while (this!=null){
            Gui.Upgate (mine);
            z = r.nextInt(4);
                if(z==0){
                    if (x+1<mine.player_data.get_grid_length())
                    {
                        if (mine.player_data.get_grid_case(x+1, y)==-1)
                        {
                            mine.player_data.edit(x, y, -1);
                            x=x+1;
                            mine.player_data.edit(x, y, mine.player_data.get_num_of_ships());
                        }
                    }
                }
                else if(z==2){
                    if (x-1>-1)
                    {
                        if (mine.player_data.get_grid_case(x-1, y)==-1)
                        {
                            mine.player_data.edit(x, y, -1);
                            x=x-1;
                            mine.player_data.edit(x, y, mine.player_data.get_num_of_ships());
                        }
                    }
                }
                else if(z==1){
                    if (y+1<mine.player_data.get_grid_width())
                    {
                        if (mine.player_data.get_grid_case(x, y+1)==-1)
                        {
                            mine.player_data.edit(x, y, -1);
                            y=y+1;
                            mine.player_data.edit(x, y, mine.player_data.get_num_of_ships());
                        }
                    }
                }
                else if(z==3){
                    if (y-1>-1)
                    {
                        if (mine.player_data.get_grid_case(x, y-1)==-1)
                        {
                            mine.player_data.edit(x, y, -1);
                            y=y-1;
                            mine.player_data.edit(x, y, mine.player_data.get_num_of_ships());
                        }
                    }
                } 
               try { 
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BomB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
