/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Nicolas Benatti
 */
public class addPersonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent ae) {
                
        JFrame popup = new addPersonWindow(true);   // ereditarietà...
        
        popup.pack();
        popup.setLocationRelativeTo(null);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setVisible(true);
    }
}
