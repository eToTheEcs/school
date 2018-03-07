/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Nicolas Benatti
 */
public class sortListener implements ActionListener {
    
    public static boolean hasBeenSorted = false;
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(!hasBeenSorted) {
            Collection prv = Collections.list( mainWindow.getInstance().getDlm().elements() );
            Collections.sort((List<Persona>)prv);
            DefaultListModel dlm = mainWindow.getInstance().getDlm();
            
            // ugly way :-(
            dlm.clear();
            
            for(Object runner : prv)
                dlm.addElement(runner);
            
            System.out.println("list sorted");
            hasBeenSorted = true;
        }
    }
}
