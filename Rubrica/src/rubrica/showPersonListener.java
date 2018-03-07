/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

//TODO: mostrare descrizione persona compilata

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Nicolas Benatti
 */
public class showPersonListener extends MouseAdapter {
    
    private mainWindow ref = null;
    
    public showPersonListener (mainWindow _ref) {
        
        this.ref = _ref;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
        if(me.getClickCount() == 2) {
            
            addPersonWindow tmp = new addPersonWindow(false);
            tmp.showcaseMode(true);

            JFrame popup = tmp;
            popup.pack();
            popup.setLocationRelativeTo(null);
            popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            popup.setVisible(true);
        }
    }
}
