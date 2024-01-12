
package mainclass;

import java.io.Serializable;
import java.util.Scanner;


abstract public class Ship implements Serializable{
       protected int length , width ,size ;
       protected String name ;
      
       
       public void changeSize (int length,int width)
       {
         this.length = length; 
         this.width = width ;
       }
       public int returnLenght () { return length ; }
       public int returnWidth () { return width ; }
       public int returnSize () { return (--size) ; }
       public String returnName() { return name ; }
}
