/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainclass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import mainclass.App;

public class Timer_ extends Thread implements Serializable {

    JLabel label;
    int timer = 0;
    App gui;

    Timer_(App a) {
        label = new JLabel();
        gui = a;
    }

    void logic() {
        timer++;
        if (timer == 11) {
            timer = 0;
        }
        label.setText("Counter : " + timer);
    }

    public void run() {
        int i = 0;
        while (true) {
            while (i <= 10) {
                logic();
                edit();
                try {
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Timer_.class.getName()).log(Level.SEVERE, null, ex);
                }
                i++;
            }
            i=0;
        }
    }

    void edit() {
        this.gui.Make(label);
    }

    void Update() {
        timer = -1; 
    }

}
