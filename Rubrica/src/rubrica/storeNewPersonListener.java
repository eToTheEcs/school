/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nicolas Benatti
 */
public class storeNewPersonListener implements ActionListener{

    private addPersonWindow ref;
    
    public storeNewPersonListener(addPersonWindow _ref) {
        
        this.ref = _ref;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        Persona newEntry = new Persona(
                ref.getUserInputs().get("Firstname").getText(),
                ref.getUserInputs().get("Lastname").getText(),
                ref.getUserInputs().get("Address").getText(),
                ref.getUserInputs().get("City").getText(),
                ref.getUserInputs().get("State").getText(),
                ref.getUserInputs().get("Zip code").getText(),
                ref.getUserInputs().get("Phone").getText()
        );
        
        mainWindow.getInstance().updateDb(newEntry);
        
        System.out.println("hai inserito una persona ");
        
    }
}
