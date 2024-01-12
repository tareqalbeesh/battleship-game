package mainclass;

import java.io.*;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainCLass implements Serializable{

    public static void main(String[] args) throws IOException 
    {

    	File file = new File("GamesNum.txt");
    	
    	FileReader fr = new FileReader(file);
    	int i=0;
    	int xx=0;
    	while((i = fr.read() )!= -1) 
    	{
    		i-='0';
    		xx=xx*10 + (int)i;
    	}
    	fr.close();
    	System.out.println(xx);
    	
    	 Calendar cal = Calendar.getInstance();
        int x =cal.getTime().getMinutes(); 
        System.out.print(x);
         /// Creating An APP1 object and calling start
     
    	//new BattleshipGame().end();
        new App("BattleShipGame").start();

    }

}
