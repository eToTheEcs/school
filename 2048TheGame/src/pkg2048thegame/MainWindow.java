/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048thegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
        
        int randomi = (int)(Math.random() * order), randomj = (int)(Math.random() * order);
        
        System.out.println("("+randomi+", "+randomj+")");
        
        this.gm.get(randomi, randomj).setNumber(2);
        
        this.addKeyListener(this);
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
                Tile tile = gm.get(i, j);
                graphics.setColor(decideTileColor(tile.getNumber()));
                graphics.fillRect(tile.getAnchorx(), tile.getAnchory(), tile.getW(), tile.getH());
                graphics.setColor(Color.BLACK);
                graphics.drawRect(tile.getAnchorx(), tile.getAnchory(), tile.getW(), tile.getH());
                if(tile.getNumber() != 0) {
                    graphics.setFont(new Font("Ubuntu", Font.BOLD, 33));
                    graphics.drawString(String.valueOf(tile.getNumber()), 
                                          tile.getAnchorx() + tile.getW()/3, 
                                          tile.getAnchory() + tile.getH()/2);
                }
            }
        }
    }
    
    private Color decideTileColor(int tileValue) {
        
        switch(tileValue) {
            
            case 0:
            case 2:
                return new Color(245, 245, 245);
            case 4:
                return new Color(245, 245, 220);
            case 8:
                return new Color(255, 160, 122);
            case 16:
                return new Color(255, 127, 80);
            case 32:
                return new Color(255, 99, 71);
            case 64:
                return new Color(255, 0, 0);
            case 128:
                return new Color(255, 240, 96);
            case 256:
                return new Color(240, 224, 80);
            case 512:
                return new Color(240, 224, 16);
            case 1024:
                return new Color(240, 208, 0);
            case 2048:
                return new Color(240, 192, 0);
            case 4096:
                return Color.black;
            default:
                return Color.black;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("key " + ke.getKeyCode() + " pressed");
       
        switch (ke.getKeyCode()) {
            case 38:
                //this.gm.swipeUp();
                break;
            case 37:
                //this.gm.swipeLeft();
                break;
            case 40:
                //this.gm.swipeDown();
                break;
            case 39:
                //this.gm.swipeRight();
                break;
            default:
                break;
        }
        
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent ke) {}

    @Override
    public void keyTyped(KeyEvent ke) {}
}
