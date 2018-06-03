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
        
        
        /*int[] v = {1, 2, 3, 4, 5, 6};
        
        shiftBackwards(v, 0, v.length-1);
        
        System.out.println(Arrays.toString(v));*/
    }
    
    
    /*public static void shiftForward(int[] v, int start, int end) {
        
        for(int i = end; i > start; --i) {
            v[i] = v[i-1];
        }
        
        v[start] = 0;
    }
    
    public static void shiftBackwards(int[] v, int start, int end) {
        
        for(int i = start; i < end; ++i)
            v[i] = v[i+1];
        
        v[end] = 0;
    }*/
}
