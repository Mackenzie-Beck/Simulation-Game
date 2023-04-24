package simlationGame;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class Good {
	/**
	 * The good class 
	 */
	
	
	
    private byte goodID; 
    private Tile currentTile;
    public float[] needValues = new float[1]; // index of each float in array correlates to need index. 
 
    
    
    // constructor 
    public Good(byte biomeID, Tile tile){
        this.goodID = biomeID;
        tile.add_good(this);
        this.currentTile = tile;
        
        // A switch statement that takes the goodID and then initializes the needValues array based on the appropriate Good/Biome index. 
        switch(this.goodID) {
        case 0: // Good object is grain. 
        	needValues[0] -= (float)6;
        
        }
        
    }
 
    
    
    // Getters 
    public int getGoodID() {return goodID;}

    public float[] getNeedValues() {return needValues;}
 
    //setters
    

    
    
    
    
    // good specific functions
    public void consume() {
        // code to delete this instance of the Good class from the tree
    	currentTile.getGoodsOnTile().remove(this);
    }
    
    
    
    
    
    
}