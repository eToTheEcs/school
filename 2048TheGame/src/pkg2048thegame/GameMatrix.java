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
public class GameMatrix {
    
    private Tile[][] mat;
    
    //private ArrayList<Integer> freeTiles;
    
    private int rows, cols;
    
    private int spawnCoordx, spawnCoordy;   // coordinates of the last spawn 
    
    public GameMatrix(int _rows, int _cols, int _startx, int _starty, int _tileH, int _tileW, Color _c) {
        
        rows = _rows;
        cols = _cols;
        
        this.mat = new Tile[rows][cols];
        
        int i, j;
        
        int runnerAnchorx = 1, runnerAnchory = 1;
        
        for(i = 0; i < rows; ++i) {
            runnerAnchorx = 0;
            for(j = 0; j < cols; ++j) {
                mat[i][j] = new Tile(_tileH, _tileW, runnerAnchorx, runnerAnchory, _c);
                runnerAnchorx += _tileW;
            }
            
            runnerAnchory += _tileH;
        }
        
        spawnTile();
    }
    
    public GameMatrix(GameMatrix other) {
        
        rows = other.rows;
        cols = other.cols;
        
        mat = new Tile[rows][cols];
        
        int i, j;
        // Color should be immutable, but who knows....
        for(i = 0; i < rows; ++i)
            for(j = 0; j < cols; ++j)
                mat[i][j] = new Tile(other.mat[i][j]);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public Tile get(int i, int j) {
        return mat[i][j];
    }
    
    // returns if 2 tiles sum up at a power of 2
    private boolean sumAtPow2(int tile1, int tile2) {
        
        return Integer.bitCount(tile1 + tile2) == 1;
    }
    
    // action utilities
    
    // the following 2 functions return the actual tiles shifted (0 tiles are empty tiles)
    
    private int shiftForward(Tile[] v, int start, int end) {
        
        int ctr = 0;
        
        for(int i = end; i > start; --i) {
            
            if(v[i-1].getNumber() != 0)
                ctr++;
            
            v[i].setNumber(v[i-1].getNumber());
            
        }
        
        v[start].setNumber(0);
        
        return ctr;
    }
    
    private int shiftBackwards(Tile[] v, int start, int end) {
        
        int ctr = 0;
        
        for(int i = start; i < end; ++i) {
            
            if(v[i+1].getNumber() != 0)
                ctr++;
            
            v[i].setNumber(v[i+1].getNumber());
        }
        
        v[end].setNumber(0);
        
        return ctr;
    }
    
    // returns an array representing the matrix column
    private Tile[] arrayFromCol(int col) {
        
        Tile[] res = new Tile[rows];
        
        for(int i = 0; i < rows; ++i) {
            res[i] = mat[i][col];   // shallow copy goes well
        }
        
        return res;
    }
    
    private void spawnTile() {
        
        spawnCoordx = (int)(Math.random() * cols);
        spawnCoordy = (int)(Math.random() * rows);
        
        System.out.println("** tile spawned at coords:" + spawnCoordy + ", " + spawnCoordx);
        
        mat[spawnCoordy][spawnCoordx].setNumber(2);
    }
    
    private void pack(int item, Direction dir) {
        
        int i, j;
        
        boolean pow2 = false, lastEmpty = false;
        
        switch(dir) {
            
            case DOWN:
                                
                for(i = rows - 1; i > 0; ) {
                    System.out.println("I'm at row " + i + ", col " + item);
                    if(!lastEmpty && ((pow2 = sumAtPow2(mat[i][item].getNumber(), mat[i-1][item].getNumber())) || 
                            (mat[i][item].isEmpty() || mat[i-1][item].isEmpty()))) {
                        
                        System.out.println("found matching tiles");
                        
                        Tile[] tmp = arrayFromCol(item);

                        if(shiftForward(tmp, 0, i) == 0) {
                            lastEmpty = true;
                            System.out.println("no more tiles to shift");
                        }
                        
                        if(pow2)
                            mat[i][item].setNumber( mat[i][item].getNumber() * 2 );
                    }
                    else {
                        --i;
                        lastEmpty = false;
                    }
                }
                
                System.out.println("==============================================");
                
                break;
                
            case UP:
                
                break;
                
            case LEFT:
                
                break;
                
            case RIGHT:
                
                break;
                
            default:
                break;
        }
    }
    
    // action movements
    
    public void swipeDown() {
        
        pack(spawnCoordx, Direction.DOWN);
        
        // generate spawn coordinates
        spawnTile();
    }
    
    public void swipeUp() {
        pack(spawnCoordx, Direction.UP);
        spawnTile();
    }
    
    public void swipeRight() {
        pack(spawnCoordy, Direction.RIGHT);
        spawnTile();
    }
    
    public void swipeLeft() {
        pack(spawnCoordy, Direction.LEFT);
        spawnTile();
    }
}
