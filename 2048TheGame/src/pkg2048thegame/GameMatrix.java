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
    
    private int rows, cols;
    
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
}
