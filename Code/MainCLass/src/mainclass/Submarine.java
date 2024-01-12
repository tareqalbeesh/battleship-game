
package mainclass;

import java.io.Serializable;

import mainclass.Ship;


public class Submarine extends Ship implements Serializable{
    Submarine (){
        length = 3; 
        width = 1;
        size = 3;
        name = "Submarine";
        
    }
}
