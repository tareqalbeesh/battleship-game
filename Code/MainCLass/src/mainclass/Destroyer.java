
package mainclass;

import java.io.Serializable;

import mainclass.Ship;


public class Destroyer extends Ship implements Serializable{
    Destroyer (){
        length = 2; 
        width = 1;
        size = 2;
        name = "Destroyer";
    }
}
