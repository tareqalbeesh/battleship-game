
package mainclass;

import java.io.Serializable;

import mainclass.Ship;


public class Carrier extends Ship implements Serializable{
    Carrier (){ 
        length = 5 ; 
        width =1 ;
        size = 5;
        name = "Carrier";
    }
}
