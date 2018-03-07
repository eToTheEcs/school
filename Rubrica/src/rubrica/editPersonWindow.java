/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

/**
 *
 * @author Nicolas Benatti
 */
public class editPersonWindow extends addPersonWindow {
    
    public editPersonWindow() {
        super(true);
        
        this.ok.removeActionListener(super.al);
        this.ok.addActionListener(new storeNewPersonListener(this, true));
    }
}
