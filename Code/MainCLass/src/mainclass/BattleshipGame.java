
package mainclass;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Iterator;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
public class BattleshipGame extends Game implements Serializable
    {
    public GameRecord game_record;
    BattleshipPlayer curr_player ;
    private App the_GUI;
    private Thread t  ;
      
    public Player get_cp()
    {
        return players.get(0);
    }
    
    ///judjment 
    public class BattleShipJudjment extends Judjment
     {
       private int numfor1 , numfor2 ; 
       public void set (int i)
       {
           numfor1 = numfor2 = i ;
       }
       public void edit (BattleshipPlayer p){
           if (p == (BattleshipPlayer)players.get(0)) numfor1--;
           else numfor2--;
       }
       public int res (){
           if (numfor1==0) return -1;
           if (numfor2==0) return 1;
           return 0;
       }
       
     }

    public BattleShipJudjment BSJ = new BattleShipJudjment();
  
    public void start() 
   {	
    	//call the run game -1 witch input the ships of the user 
        if(players.size()==2) { curr_player = (BattleshipPlayer) players.get(1);RunGame(-1,null,null);} 
    }
    
    public void end()
    {
    	//this.the_GUI.setVisible(false);
    
    	
    	 
       ComputerPlayer cp = (ComputerPlayer) this.players.get(0);
       the_GUI.setVisible(false);
       
        //////edit score board
       
        ScoreBoard s = null;
                try {
                    FileInputStream fos = new FileInputStream("RecordFiles\\ScoreBoard");
                    ObjectInputStream obj = new ObjectInputStream(fos);
                    try {
                        s = (ScoreBoard) obj.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    obj.close();
                    fos.close();

                } catch (IOException ex) {

                }
                for (int i=0 ; i<s.objects.size();i++)
                {
                    if (curr_player.player_data.name.equals(s.objects.get(i).getName()))
                    {
                        if (BSJ.res()==-1)
                            s.objects.get(i).inc_loose();
                        else s.objects.get(i).inc_won();
                        break;
                    }
                }
                s.sort();
                try {
                    FileOutputStream fos = new FileOutputStream("RecordFiles\\ScoreBoard");
                    ObjectOutputStream obj = new ObjectOutputStream(fos);
                    obj.writeObject(s);
                    obj.close();
                    fos.close();
                } catch (IOException ex) {
                    System.out.println("nooooo");
                }
                
                
           /////// end 
     if(BSJ.res()==-1)
    	 new App("BattleShipGame").END(false,cp.player_data.get_hits(),curr_player.player_data.get_hits(),curr_player.player_data.get_grid_length()*curr_player.player_data.get_grid_width());
     else 
    	 new App("BattleShipGame").END(true,cp.player_data.get_hits(),curr_player.player_data.get_hits(),curr_player.player_data.get_grid_length()*curr_player.player_data.get_grid_width());
     //the_GUI.close();
     String name  = JOptionPane.showInputDialog("Enter the Save name");
     File file= new File("RecordFiles\\" + name);
     
 	try {
 		
        FileOutputStream fos = new FileOutputStream (file);
        ObjectOutputStream obj = new ObjectOutputStream(fos);
        game_record.set_end();
         if(BSJ.res()==-1)
        game_record.set_winner(false);
         else
        	 game_record.set_winner(true);
        game_record.set_cp_record(cp.player_data.get_hits());
        game_record.set_hp_record(curr_player.player_data.get_hits());
        obj.writeObject(game_record);
        obj.close();
        fos.close();
        System.out.println("Save");
        
        
        file  = new File("RecordFiles\\FilesNames");
        
        FilesName fn=null;
        if(file.exists()) {
        	
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
         fn = (FilesName) ois.readObject();
         
        fn.names.add(name);
        ois.close();
        fis.close();
        //file.delete();
        
        }
        else 
        {
        	
        	fn = new FilesName();
        	String h="file" + Integer.toString(game_record.get_id());
        	fn.names.add(h);
        }
        
         fos = new FileOutputStream ("RecordFiles\\FilesNames");
         obj = new ObjectOutputStream(fos);
        obj.writeObject(fn);
        obj.close();
        fos.close();
        
        
        
      } catch (IOException ex) {
           System.out.println("IO Exception");
        }catch(ClassNotFoundException e )
 	{
        	System.out.print("Class Not Found Exception");
 	}
 	
     
    	
    }
    
    public void RunGame(int round , App the_GUI,BattleshipMove player_move)
    {
    	if(round == -2)// in this round we creat the gui buttons and give them listeners imp -2
    	{
    		
    	the_GUI = new App("The Game",curr_player.player_data.get_grid_length(),curr_player.player_data.get_grid_width());
    	this.the_GUI = the_GUI; 
        the_GUI.Implementation(this, -2,null,null);
        ComputerPlayer cp = (ComputerPlayer) players.get(0);
        cp.set_gui(the_GUI);
        HumanPlayer hp = (HumanPlayer) players.get(1);
        
        //to know the id
        File file = new File("GamesNum.txt");

    	FileReader fr;
    	
    	int xx=0;
		try {
			fr = new FileReader(file);
			int i=0;
    	while((i = fr.read() )!= -1) 
    	{
    		i-='0';
    		xx=xx*10 + (int)i;
    	}
    	fr.close();
		} catch (Exception e) {
			System.out.print("Didn't Read the file");
		}
		
	    try
	    {
	    	xx++;
	    	String ss= Integer.toString(xx);
	    	FileWriter fw = new FileWriter(file);
			file.delete();
			file = new File("GamesNum.txt");
			fw.write(ss);
			fw.flush();
			fw.close();

	    }catch(Exception e)
	    {
	    	System.out.print("Couldn't Write in file");
	    }
	    ///set the GameRecord
	    Grid g1 = new Grid();
	    Grid g2 = new Grid();
	    g1 = hp.player_data.get_grid();
	    g2 = cp.player_data.get_grid();
	    
	    game_record = new GameRecord(xx,hp.player_data.get_name(),g1,g2,hp.player_data.get_num_of_ships());
	    
	    
    	
        
        
        
	/// implementation -2  /// implementation -2 call rungame (1)
    		
    	}
        if(round==-1)
        {
        
        curr_player.putShips();

        }
        else if(round ==0)//computer turn
        {
        	the_GUI.t.Update();
                ComputerPlayer cp = (ComputerPlayer) players.get(0);
        	curr_player = (HumanPlayer) players.get(1);
                BattleshipMoveResult player_result=null;
                if (player_move!=null)
                {
        	
        	 player_result  = cp.AcceptPlayerMove(player_move);
        	curr_player.NotifyPlayer(player_result);
        	

        	if(player_result.get_hit_case()==cp.player_data.get_num_of_ships())
        	{
        	
            	int x = player_result.get_x(),y=player_result.get_y(),z=player_result.get_hit_case();
        		for(int i=x-1;i<x+2;i++)
            	{
            		for(int j=y-1;j<y+2;j++)
            		{
            		//	System.out.println("Dsadasdas");
            	
            			if(i>=0 && j>=0 &&
            					i<cp.player_data.get_grid_length() && 
            					j<cp.player_data.get_grid_width() &&
            					curr_player.player_data.get_draft_case(i, j)==-1  
            					)
            			{
            				//System.out.println(i  + "    " + j);
            				player_move = new BattleshipMove(i,j);
            				 BattleshipMoveResult player_result2  = (cp).AcceptPlayerMove(player_move);
            				 the_GUI.edit_for_me(player_result2);
                        	curr_player.NotifyPlayer(player_result2);
            			}
            		}
            	}
        		
        		curr_player.player_data.print_draft();	
        	}
        	
                }
                if (BSJ.res()==-1) this.end();
                
                t = new Thread(cp); 
                t.start();
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(BattleshipGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                BattleshipMoveResult computer_result = null;
                if (cp.GetNextMove()==null)
                {
                    
                }else {
                   the_GUI.t.Update(); 
                 BattleshipMove computer_move = cp.GetNextMove();
                 computer_result = curr_player.AcceptPlayerMove(computer_move);
        	
                 
                 cp.NotifyPlayer(computer_result);
                 if(computer_result.get_hit_case()==curr_player.player_data.get_num_of_ships())
             	{
             	
                 	int x = computer_result.get_x(),y=computer_result.get_y(),z=computer_result.get_hit_case();
             		for(int i=x-1;i<x+2;i++)
                 	{
                 		for(int j=y-1;j<y+2;j++)
                 		{
                 		//	System.out.println("Dsadasdas");
                 	
                 			if(i>=0 && j>=0 &&
                 					i<curr_player.player_data.get_grid_length() && 
                 					j<curr_player.player_data.get_grid_width() &&
                 					cp.player_data.get_draft_case(i, j)==-1  
                 					)
                 			{
                 				//System.out.println(i  + "    " + j);
                 				computer_move = new BattleshipMove(i,j);
                 				 BattleshipMoveResult computer_result2  = (curr_player).AcceptPlayerMove(computer_move);
                 				 the_GUI.edit_for_cp(computer_result2);
                             	cp.NotifyPlayer(computer_result2);
                 			}
                 		}
                 	}
             		
             		//curr_player.player_data.print_draft();
             		
             	}
             	
                }
                
        	if (BSJ.res()==1) this.end();
                t=null;
                
                the_GUI.Implementation(this, 0, player_result, computer_result);

                
        }
        /*else if(round == 1)// call implementation 1 to get the hit 
        {
        	
        	the_GUI.Implementation(this,1,null,null);
        	
        }*/
        
        
        //System.out.println (curr_player.GetNextMove().get_x()+ " "+curr_player.GetNextMove().get_y());
        
    }
    
    public void load()
    {
    	the_GUI.setVisible(true);
        the_GUI.t.start();
        int q=0 ; 
        ComputerPlayer cp = (ComputerPlayer) players.get(0);
            while(q<curr_player.B.size())
            {
               curr_player.B.get(q).start(); 
               cp.B.get(q).start(); 
               q++;
            }
    }
    
    }
                                                                 
