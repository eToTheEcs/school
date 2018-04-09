/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048thegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * this class applies singleton design pattern
 * @author Nicolas Benatti
 */
public class MainWindow extends JFrame implements KeyListener {
    
    private static MainWindow instance = null;
    
    private GameMatrix gm = null;
    
    private final Color INITIAL_COURT_COLOR = Color.lightGray;
    
    // window dimensions
    private int w, h;
    
    // matrix order
    private int order;
    
    private MainWindow() {
        
        this.w = 400;
        this.h = 400;
        
        this.order = 3;
        
        setTitle("2048 THE GAME");
        setPreferredSize(new Dimension(w, h));
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        int tileH = h / this.order, tileW = w / this.order;
        
        this.gm = new GameMatrix(3, 3, 1, 1, tileH, tileW, INITIAL_COURT_COLOR);
        
        this.gm.get((int)(Math.random() * order), (int)(Math.random() * order)).setNumber(2);
    }

    public static MainWindow getInstance() {
        if(instance == null)
            instance = new MainWindow();
        return instance;
    }
    
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics); 
        
        // traverse the matrix and apply changes
        int i, j, r = gm.getRows(), c = gm.getCols();
        
        for(i = 0; i < r; ++i) {
            for(j = 0; j < c; ++j) {
                graphics.setColor(gm.get(i, j).getColor());
                graphics.fillRect(gm.get(i, j).getAnchorx(), gm.get(i, j).getAnchory(), gm.get(i, j).getW(), gm.get(i, j).getH());
                graphics.setColor(Color.BLACK);
                graphics.drawRect(gm.get(i, j).getAnchorx(), gm.get(i, j).getAnchory(), gm.get(i, j).getW(), gm.get(i, j).getH());
                if(gm.get(i, j).getNumber() != 0)
                    graphics.drawString(String.valueOf(gm.get(i, j).getNumber()), gm.get(i, j).getAnchorx(), gm.get(i, j).getAnchory());
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("key " + ke.getKeyText(w) + " typed");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("key " + ke.getKeyText(w) + " pressed");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("key " + ke.getKeyText(w) + " released");
    }
}
