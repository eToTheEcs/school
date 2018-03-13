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
public class dataManager {
    
    static private dataManager instance = null;
    static private final String CSV_SEPARATOR = ";";
    private final String fileName;         // this is the file on which the people data is saved, in CSV format(with a possibly custom separator)
    private final int MODE;     // 0: fetch, 1: save
    
    public dataManager (int _MODE) {
        
        fileName = "data.txt";
        
        if(_MODE == 0) {
            
            
        }
        else if(_MODE == 1) {
            
            
        }
    }
    
    /*public static dataFetcher getInstance() {
        
        if(instance == null)
            instance = new dataFetcher();
        
        return instance;
    }*/
    
    
}
