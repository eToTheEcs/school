/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048thegame;

import java.awt.Color;

/**
 *
 * @author Nicolas Benatti
 */
public class Tile {
    
    private int w, h, anchorx, anchory, number;
    
    Color color;
    
    public Tile(int _h, int _w, int _anchorx, int _anchory, Color _c) {
        
        h = _h;
        w = _w;
        anchorx = _anchorx;
        anchory = _anchory;
        color = _c;
        number = 0; // 0 => do not display the number on screen
    }
    
    public Tile(Tile other) {
        h = other.h;
        w = other.w;
        anchorx = other.anchorx;
        anchory = other.anchory;
        color = new Color(other.color.getRed(), other.color.getGreen(), other.color.getBlue());
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getAnchorx() {
        return anchorx;
    }

    public int getAnchory() {
        return anchory;
    }

    public Color getColor() {
        return color;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }
    
    
}
