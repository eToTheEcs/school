/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048thegame;

import javax.swing.JFrame;

/**
 *
 * @author Nicolas Benatti
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame mwin = MainWindow.getInstance();
        
        mwin.pack();
        mwin.setLocationRelativeTo(null);
        mwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mwin.setVisible(true);
    }
    
}