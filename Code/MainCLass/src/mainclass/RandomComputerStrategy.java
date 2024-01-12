
package mainclass;
import java.io.Serializable;
import java.util.Random;

public class RandomComputerStrategy extends AbstractComputerStrategy implements Serializable{   
    int length,width;
	RandomComputerStrategy(int length,int width)
	{   this.length = length;	this.width = width;	}
		
    private Random random = new Random();
    public BattleshipMove GetNextMove()
    {
       int x = random.nextInt(length);
       int y = random.nextInt(width);
       return new BattleshipMove(x,y);
    }

    
}
