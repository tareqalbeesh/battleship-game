/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import java.io.Serializable;

import mainclass.Ship;


public class Cruiser extends Ship implements Serializable {
    Cruiser (){
        length = 3;
        width = 1;
        size = 3;
        name = "Cruiser";
    }
}
