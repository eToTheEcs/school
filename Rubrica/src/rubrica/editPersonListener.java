/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas Benatti
 */
public class editPersonListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(mainWindow.getInstance().getSelectedRecord() == null) {
            JOptionPane.showMessageDialog(mainWindow.getInstance(), "you haven't selected a person to edit", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        editPersonWindow popup = new editPersonWindow();
        
        popup.pack();
        popup.setLocationRelativeTo(null);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setVisible(true);
    }
}
