
package mainclass;

import java.io.Serializable;

public abstract class AbstractComputerStrategy implements Serializable {


    public BattleshipMove move;
public abstract BattleshipMove GetNextMove();

    
}
