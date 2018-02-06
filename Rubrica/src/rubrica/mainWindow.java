/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Nicolas Benatti
 */
public class mainWindow extends JFrame{
    
    private Map<String, JButton> buttons = new HashMap<>();   
    
    private JList contactList;
    
    private DefaultListModel dlm = new DefaultListModel();
    
    private JScrollPane listScroller = null;
    
    public mainWindow() {
        
        GridBagConstraints c = new GridBagConstraints();
        
        buttons.put("Add", new JButton("Add"));
        buttons.put("Edit", new JButton("Edit")); 
        buttons.put("Delete", new JButton("Delete"));
        buttons.put("Sort by name", new JButton("Sort by name"));
        buttons.put("Sort by ZIP", new JButton("Sort by ZIP"));
        
        // aggiungo i listener necessari
        this.buttons.get("Add").addActionListener( new addPersonListener() );
        
        // settaggio della lista contatti
        this.contactList = new JList(this.dlm);
        this.contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.contactList.setLayoutOrientation(JList.VERTICAL);
        this.contactList.setVisibleRowCount(-1);
        this.listScroller = new JScrollPane(this.contactList);
        this.listScroller.setPreferredSize(new Dimension(300, 100));
        
        
        this.contactList.setPreferredSize(new Dimension(300, 100));
        
        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new GridBagLayout());
                
        mainPanel.setBorder( BorderFactory.createEmptyBorder(10,10,10,10) );
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20, 10, 0, 10);
        c.gridwidth = 5;
        c.gridx = c.gridy = 0;
        mainPanel.add(this.contactList, c);
        
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 0;
        mainPanel.add(this.buttons.get("Add"), c);
        
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 1;
        mainPanel.add(this.buttons.get("Edit"), c);
        
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 2;
        mainPanel.add(this.buttons.get("Delete"), c);
        
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 3;
        mainPanel.add(this.buttons.get("Sort by name"), c);
        
        c.gridwidth = 1;
        c.gridy = 1;
        c.gridx = 4;
        mainPanel.add(this.buttons.get("Sort by ZIP"), c);
        
        this.add(mainPanel);
    }
    
}
