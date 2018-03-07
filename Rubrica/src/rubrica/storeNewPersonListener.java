/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 *
 * @author Nicolas Benatti
 */
public class storeNewPersonListener implements ActionListener {

    private addPersonWindow ref;
    
    private boolean isUsedToUpdate = false;
    
    public storeNewPersonListener(addPersonWindow _ref, boolean usedToUpdate) {
        
        this.ref = _ref;
        this.isUsedToUpdate = usedToUpdate;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Persona newEntry = new Persona(
                ref.getUserInputs().get("Firstname").getText().trim(),
                ref.getUserInputs().get("Lastname").getText().trim(),
                ref.getUserInputs().get("Address").getText().trim(),
                ref.getUserInputs().get("City").getText().trim(),
                ref.getUserInputs().get("State").getText().trim(),
                ref.getUserInputs().get("Zip code").getText().trim(),
                ref.getUserInputs().get("Phone").getText().trim()
        );
        
        if(!this.isUsedToUpdate)
            mainWindow.getInstance().insertInDB(newEntry);
        else {
            int selectedIndex = mainWindow.getInstance().getList().getSelectedIndex();
            mainWindow.getInstance().updateDB((String)mainWindow.getInstance().getList().getSelectedValue(), newEntry, selectedIndex);
        }
        
        this.ref.dispatchEvent(new WindowEvent(this.ref, WindowEvent.WINDOW_CLOSING));
    }
}
