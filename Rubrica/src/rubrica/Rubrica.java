/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Nicolas Benatti
 */
public class Rubrica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {}
        
        mainWindow win = mainWindow.getInstance();
        
        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }
    
}
