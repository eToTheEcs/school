/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Nicolas Benatti
 */
public class addPersonWindow extends JFrame {
    
    private Map<String, JTextArea> userInputs = new HashMap<String, JTextArea>();
    
    //private Map<String, JLabel> windowLabels = new HashMap<String, JLabel>();
    
    private ArrayList<JLabel> windowLabels = new ArrayList<JLabel>();
    
    JButton ok, cancel;
    
    public addPersonWindow() {
        
        int i;
        
        GridBagConstraints c = new GridBagConstraints();
        
        this.userInputs.put("Firstname", new JTextArea());
        this.userInputs.put("Lastname", new JTextArea());
        this.userInputs.put("Address", new JTextArea());
        this.userInputs.put("City", new JTextArea());
        this.userInputs.put("State", new JTextArea());
        this.userInputs.put("Zip code", new JTextArea());
        this.userInputs.put("Phone", new JTextArea());
        
        for(Map.Entry<String, JTextArea> el : this.userInputs.entrySet())
            el.getValue().setPreferredSize(new Dimension(150, 20));
        
        /*this.windowLabels.put("FirstName", new JLabel("FirstName"));
        this.windowLabels.put("LastName", new JLabel("LastName"));
        this.windowLabels.put("Address", new JLabel("Address"));
        this.windowLabels.put("City", new JLabel("City"));
        this.windowLabels.put("State", new JLabel("State"));
        this.windowLabels.put("Zip code", new JLabel("Zip code"));
        this.windowLabels.put("Phone", new JLabel("Phone"));*/
        
        this.windowLabels.add(new JLabel("Firstname"));
        this.windowLabels.add(new JLabel("Lastname"));
        this.windowLabels.add(new JLabel("Address"));
        this.windowLabels.add(new JLabel("City"));
        this.windowLabels.add(new JLabel("State"));
        this.windowLabels.add(new JLabel("Zip code"));
        this.windowLabels.add(new JLabel("Phone"));
        
        for(JLabel runner : this.windowLabels) {
            
            runner.setPreferredSize(new Dimension(150, 20));
        }
        
        this.ok = new JButton("Ok");
        this.ok.setPreferredSize(new Dimension(40, 20));
        this.ok.addActionListener(new storeNewPersonListener(this));
        
        this.cancel = new JButton("Cancel");
        this.ok.setPreferredSize(new Dimension(40, 20));
        
        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridBagLayout());
        
        mainPanel.setBorder( BorderFactory.createEmptyBorder(10,10,10,10) );
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 10, 0, 10);
        c.gridwidth = 3;
        c.gridx = 0;
        for(i = 0; i < this.windowLabels.size(); ++i) {
            c.gridy = i;
            
            mainPanel.add(this.windowLabels.get(i), c);
        }
        
        c.gridx = 3;
        for(i = 0; i < this.userInputs.size(); ++i) {
            c.gridy = i;
            
            mainPanel.add(this.userInputs.get(this.windowLabels.get(i).getText()), c);
        }
        
        c.gridwidth = 1;
        
        c.gridx = 2;
        c.gridy = 7;
        mainPanel.add(ok, c);
        
        c.gridx = 5;
        c.gridy = 7;
        mainPanel.add(cancel, c);
        
        this.add(mainPanel);
    }
    
    Map<String, JTextArea> getUserInputs() {
        
        return this.userInputs;
    }
}
