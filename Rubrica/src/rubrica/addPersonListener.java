/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Nicolas Benatti
 */
public class addPersonListener implements ActionListener{

    /*private mainWindow ref = null;
    
    public addPersonListener(mainWindow _ref) {
        this.ref = _ref;
    }*/
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        System.out.println("hai scelto \" " + ((JButton)ae.getSource()).getText() + "\" ");
        JFrame popup = new addPersonWindow();   // ereditarietà...
        popup.pack();
        popup.setLocationRelativeTo(null);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setVisible(true);
    }
}
