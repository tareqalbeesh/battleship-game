/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import java.io.Serializable;

import mainclass.Ship;


public class Battleship extends Ship implements Serializable {
    Battleship (){
        length = 4; 
        width = 1;
        size = 4;
        name = "Battleship";
    }
}
