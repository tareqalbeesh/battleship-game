package mainclass;

import java.io.Serializable;

public class CustomShip extends Ship implements Serializable{

	CustomShip (){
        length = 1; 
        width = 1;
        size = 1;
        name = "name";
	}
	CustomShip(String name,int length)
{
	this.length = length;
	this.name = name;
}
	
	
	
	
}