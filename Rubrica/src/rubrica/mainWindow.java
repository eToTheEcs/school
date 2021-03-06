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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

/**
 * this class uses singleton design pattern
 * @author Nicolas Benatti
 */
public class mainWindow extends JFrame implements ActionListener {
    
    private static mainWindow instance = null;
    
    private Map<String, JButton> buttons = new HashMap<>();
    private JList contactList = null;
    private ListModel dlm = new DefaultListModel();
    private JScrollPane listScroller = null;
    private boolean hasBeenSorted = false; 
    
// people "database"
    private Map<String, Persona> database = new HashMap<>();
    
    private mainWindow() {
        
        GridBagConstraints c = new GridBagConstraints();
        
        buttons.put("Add", new JButton("Add"));
        buttons.put("Edit", new JButton("Edit")); 
        buttons.put("Delete", new JButton("Delete"));
        buttons.put("Sort by name", new JButton("Sort by name"));
        buttons.put("Sort by ZIP", new JButton("Sort by ZIP"));
        
        // settaggio della lista contatti
        this.contactList = new JList(this.dlm);
        this.contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.contactList.setLayoutOrientation(JList.VERTICAL);
        this.contactList.setVisibleRowCount(-1);
        this.listScroller = new JScrollPane(this.contactList);
        this.listScroller.setPreferredSize(new Dimension(300, 100));
        
        // aggiungo i listener necessari
        this.buttons.get("Add").addActionListener( new addPersonListener() );
        this.contactList.addMouseListener(new showPersonListener(this));
        this.buttons.get("Edit").addActionListener( new editPersonListener() );
        this.buttons.get("Sort by ZIP").addActionListener(this);
        
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
    
    public static mainWindow getInstance() {
        
        if(instance == null) {
            
            mainWindow.instance = new mainWindow();
        }
        
        return mainWindow.instance;
    }
    
    public String getSelectedRecord() {
        
        return (String)this.contactList.getSelectedValue();
    }
    
    public int getSelectedRecordIndex() {
        
        return this.contactList.getSelectedIndex();
    }
    
    public Persona queryDB(String key) {
        
        return this.database.get(key);
    }
    
    public void insertInDB(Persona newEntry) {
        
        // advise that the list is no longer sorted
        hasBeenSorted = false;
        
        //NOTE: using 2 concatenated strings as the "unique" ID could be quite heavy..consider switching to integer IDs
        this.database.put(newEntry.getFirstname() + " " + newEntry.getLastname(), newEntry);   
        this.insertPerson(newEntry.getFirstname() + " " + newEntry.getLastname());
    }
    
    public void updateDB(String entryName, Persona newEntry, int listIndex) {
        
        Persona affectedRecord = this.database.get(entryName);
        
        // if name didn't change, there's no need to refresh screen
        Boolean[] hasNameChanged = new Boolean[2];  hasNameChanged[1] = hasNameChanged[0] = false;
        
        Persona diffResult = affectedRecord.compareDiff(newEntry, hasNameChanged);
         
        System.out.println("OLD RECORD: " + affectedRecord);
        System.out.println("NEW RECORD: " + newEntry);
        System.out.println("UNION: " + diffResult);
        
        // update the record
        this.database.remove(entryName);
        this.database.put(diffResult.getFirstname()+" "+diffResult.getLastname(), diffResult);
        
        System.out.println("db value for key: " + diffResult.getFirstname()+" "+diffResult.getLastname() + ": " + this.database.get(diffResult.getFirstname()+" "+diffResult.getLastname()));
        
        if(hasNameChanged[1])
            refreshList(diffResult.getFirstname()+" "+diffResult.getLastname(), listIndex);
    }
    
    // refresh values in the list, after updateDB operation
    private void refreshList(String newVal, int index) {
        ((DefaultListModel)this.dlm).setElementAt(newVal, index);
    }
 
    private void insertPerson(String toadd) {
        ((DefaultListModel)this.dlm).addElement(toadd);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(!hasBeenSorted) {
            Collection prv = Collections.list( ((DefaultListModel)dlm).elements() );
            Collections.sort((List<Persona>)prv);
            
            
            // ugly way :-(
            ((DefaultListModel<String>)dlm).clear();
            
            for(Object runner : prv)
                ((DefaultListModel)dlm).addElement(runner);
            
            System.out.println("list sorted");
            hasBeenSorted = true;
        }
    }
    
}
