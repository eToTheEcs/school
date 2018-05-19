/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2048thegame;

import java.awt.Color;
import java.security.InvalidParameterException;

/**
 *
 * @author Nicolas Benatti
 */
public class GameMatrix {
    
    private Tile[][] mat;
    
    private boolean[][] mask;  // "phantom" matrix, indicates wether a cell is occupied or not
    
    private int rows, cols;
    
    private int spawnCoordx, spawnCoordy;   // coordinates of the last spawn 
    
    public GameMatrix(int _rows, int _cols, int _startx, int _starty, int _tileH, int _tileW, Color _c) {
        
        rows = _rows;
        cols = _cols;
        
        this.mat = new Tile[rows][cols];
        this.mask = new boolean[rows][cols];
        
        int i, j;
        
        int runnerAnchorx = 1, runnerAnchory = 1;
        
        for(i = 0; i < rows; ++i) {
            runnerAnchorx = 0;
            for(j = 0; j < cols; ++j) {
                mat[i][j] = new Tile(_tileH, _tileW, runnerAnchorx, runnerAnchory, _c);
                runnerAnchorx += _tileW;
                mask[i][j] = false;
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
        
        // just to be sure...
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
    
    // returns true if 2 tiles sum up at a power of 2
    private boolean sumAtPow2(int tile1, int tile2) {
        
        return Integer.bitCount(tile1 + tile2) == 1;
    }
    
    // action methods
    
    // the following 2 functions return the actual number of tiles shifted (zero-value tiles are empty tiles)
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
    private Tile[] arrayFromCol(int index, Orientation ori) {
        
        Tile[] res = new Tile[rows];
        
        int i;
        
        if(ori == Orientation.HORIZONTAL) {
            
            for(i = 0; i < cols; ++i)
                res[i] = (Tile)mat[index][i].clone();
        }
        else {
            
            for(i = 0; i < rows; ++i)
                res[i] = (Tile)mat[i][index].clone();
        }
        
        return res;
    }
    
    private void stickArrayToCol(Tile[] v, int index, Orientation ori) {
        
        int i;
        
        if(ori == Orientation.VERTICAL) {
            for(i = 0; i < rows; ++i) 
                mat[i][index].setNumber(v[i].getNumber());
        }
        else {
            
            for(i = 0; i < cols; ++i)
                mat[index][i].setNumber(v[i].getNumber());
        }
    }
    
    private void spawnTile() {
        
        do {
            spawnCoordx = (int)(Math.random() * cols);
            spawnCoordy = (int)(Math.random() * rows);
        } while(mask[spawnCoordy][spawnCoordx]);
        
        System.out.println("** tile spawned at coords:" + spawnCoordy + ", " + spawnCoordx + " **");
        
        mat[spawnCoordy][spawnCoordx].setNumber(2);
        mask[spawnCoordy][spawnCoordx] = true;
    }
    
    // updates tile state in the mask
    private void refreshMask(int index, Orientation ori) {
        
        int i;
        
        if(ori == Orientation.VERTICAL) {
            
            for(i = 0; i < rows; ++i) {
                mask[i][index] = (mat[i][index].getNumber() != 0);
            }
        }
        else if(ori == Orientation.HORIZONTAL) {
            
            for(i = 0; i < cols; ++i) {
                mask[index][i] = (mat[index][i].getNumber() != 0);
            }
        }
        else {
            throw new InvalidParameterException("GameMatrix::refreshMatrix() => use Orientation::<VERTICAL/HORIZONTAL>");
        }
    }
    
    private void pack(int item, Direction dir) {
        
        int i;
        
        boolean pow2 = false, lastEmpty = false, imEmpty = false;
        
        switch(dir) {
            
            case DOWN:
                                
                for(i = rows - 1; i > 0; ) {
                    
                    imEmpty = ( mat[i-1][item].isEmpty() && (!mat[i][item].isEmpty()) );
                    
                    if(!lastEmpty && ((mat[i][item].isEmpty() || mat[i-1][item].isEmpty()) || (pow2 = sumAtPow2(mat[i][item].getNumber(), mat[i-1][item].getNumber())))) {
                        
                        // do all the computations on a fake column (to prevent empty shifts from modifying real matrix)
                        Tile[] tmp = arrayFromCol(item, Orientation.VERTICAL);
                        
                        if(shiftForward(tmp, 0, (imEmpty) ? i - 1 : i) == 0) {
                            lastEmpty = true;
                            --i;
                            continue;
                        }

                        if(pow2)
                            tmp[i].setNumber(mat[i][item].getNumber() * 2);
                        
                        // fill the real column with processed values
                        stickArrayToCol(tmp, item, Orientation.VERTICAL);
                        refreshMask(item, Orientation.VERTICAL);
                    }
                    else {
                        --i;
                        lastEmpty = false;
                    }
                }
                
                break;
                
            case UP:
                
                for(i = 0; i < rows - 1; ) {
                    
                    imEmpty = ( mat[i+1][item].isEmpty() && (!mat[i][item].isEmpty()) );
                                        
                    if(!lastEmpty && ((mat[i][item].isEmpty() || mat[i+1][item].isEmpty()) || (pow2 = sumAtPow2(mat[i][item].getNumber(), mat[i+1][item].getNumber())))) {
                        
                        // do all the computations on a fake column (to prevent empty shifts from modifying real matrix)
                        Tile[] tmp = arrayFromCol(item, Orientation.VERTICAL);
                        
                        if(shiftBackwards(tmp, (imEmpty) ? i + 1 : i, rows - 1) == 0) {
                            lastEmpty = true;
                            ++i;
                            continue;
                        }
                        
                        if(pow2)
                            tmp[i].setNumber(mat[i][item].getNumber() * 2);
                        
                        // fill the real column with processed values
                        stickArrayToCol(tmp, item, Orientation.VERTICAL);
                        refreshMask(item, Orientation.VERTICAL);
                    }
                    else {
                        ++i;
                        lastEmpty = false;
                    }
                }
                
                break;
                
            case LEFT:
                      
                for(i = 0; i < cols - 1; ) {
                    
                    imEmpty = ( mat[item][i+1].isEmpty() && (!mat[item][i].isEmpty()) );
                                        
                    if(!lastEmpty && ((mat[item][i].isEmpty() || mat[item][i+1].isEmpty()) || (pow2 = sumAtPow2(mat[item][i].getNumber(), mat[item][i+1].getNumber())))) {
                        
                        // do all the computations on a fake column (to prevent empty shifts from modifying real matrix)
                        Tile[] tmp = arrayFromCol(item, Orientation.HORIZONTAL);
                        
                        if(shiftBackwards(tmp, (imEmpty) ? i + 1 : i, cols - 1) == 0) {
                            lastEmpty = true;
                            ++i;
                            continue;
                        }
                        
                        if(pow2)
                            tmp[i].setNumber(mat[item][i].getNumber() * 2);
                        
                        // fill the real column with processed values
                        stickArrayToCol(tmp, item, Orientation.HORIZONTAL);
                        refreshMask(item, Orientation.HORIZONTAL);
                    }
                    else {
                        ++i;
                        lastEmpty = false;
                    }
                }
                
                break;
                    
            case RIGHT:
                
                for(i = cols - 1; i > 0; ) {
                    
                    imEmpty = ( mat[item][i-1].isEmpty() && (!mat[item][i].isEmpty()) );
                                        
                    if(!lastEmpty && ((mat[item][i].isEmpty() || mat[item][i-1].isEmpty()) || (pow2 = sumAtPow2(mat[item][i].getNumber(), mat[item][i-1].getNumber())))) {
                        
                        // do all the computations on a fake column (to prevent empty shifts from modifying real matrix)
                        Tile[] tmp = arrayFromCol(item, Orientation.HORIZONTAL);
                        
                        if(shiftBackwards(tmp, 0,(imEmpty) ? i - 1 : i) == 0) {
                            lastEmpty = true;
                            --i;
                            continue;
                        }
                        
                        if(pow2)
                            tmp[i].setNumber(mat[item][i].getNumber() * 2);
                        
                        // fill the real column with processed values
                        stickArrayToCol(tmp, item, Orientation.HORIZONTAL);
                        refreshMask(item, Orientation.HORIZONTAL);
                    }
                    else {
                        --i;
                        lastEmpty = false;
                    }
                }
                
                break;
                
            default:
                break;
        }
    }
    
    // action movements
    
    public void swipeDown() {
        
        for(int i = 0; i < cols; ++i)
            pack(i, Direction.DOWN);
        
        // generate spawn coordinates
        spawnTile();
    }
    
    public void swipeUp() {
        
        for(int i = 0; i < cols; ++i)
            pack(i, Direction.UP);
        
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
