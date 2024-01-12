/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreBoard implements Serializable{
    ArrayList <player_score> objects = new ArrayList ();
    
    void sort() {
        int i, j;
        player_score key ;
   for (i = 1; i < objects.size(); i++)
   {
       key = objects.get(i);
       j = i-1;
       while (j >= 0 && objects.get(j).score() < key.score())
       {
           objects.set(j+1, objects.get(j));
           j = j-1;
       }
       objects.set(j+1, key);
   }
    }
}

class player_score implements Serializable {

    String name;
    int startedGame = 0, won = 0, loose = 0;

    player_score(String name ){
        this.name = name ; 
    }
    public void inc_startedGame() {
        startedGame++;
    }

    public void inc_loose() {
        loose++;
    }

    public void inc_won() {
        won++;
    }

    public String getName() {
        return name;
    }

    public int getStartedGame() {
        return startedGame;
    }
    public int getWon() {
        return won;
    }
    public int getloose() {
        return loose;
    }
    public float score() {
        return (float) (won * (1.0) /startedGame);
    }
}
