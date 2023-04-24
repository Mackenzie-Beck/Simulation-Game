package simlationGame;
import java.util.ArrayList;

/*
 * 
 * 
 * 
 * 
 */



public class Tile extends Grid{
    private int[] location = new int[2];
    //private int[] north;
    //private int[] west;
    private byte biomeID;
    private int pop_density;
    private int tilePopulation;
    private int yield;
    private short maxGoods;
    private byte maxPop;
     
    
    
    
    //arrayLists to store objects and pops on tile
    private ArrayList<Pop> popsOnTile = new ArrayList<Pop>();
    private ArrayList<Good> goodsOnTile = new ArrayList<Good>();
 
    
    // constructors, adders, and removers---------------------------------------------------------------------------
    
    //int biomeID, int pop_density will be specific values for now. Can add world generation settings later
    public Tile(int[] location, int yield, byte biomeID) {
        this.location = location;
        this.biomeID = biomeID;
        this.maxGoods = 100;
        this.maxPop = 10;
        this.pop_density = 5;
        this.yield = yield;
    }
 
    public void add_good(Good good) {
    	if (goodsOnTile.size() < maxGoods) {
    		goodsOnTile.add(good);}
    }
    
    public void add_pop(Pop pop) {
    	if (tilePopulation < maxPop) {
    		popsOnTile.add(pop);
    		tilePopulation = popsOnTile.size();}
    }
    

    
    
    // Getters ----------------------------------------------------------------

    
    
    //For some reason you need to specify the arraylists type here. This has something to do with generic classes doesnt it? Ask franco about it.
    public ArrayList<Good> getGoodsOnTile() {return this.goodsOnTile;}
    
    public ArrayList<Pop> getpopsOntile() {return this.popsOnTile;}
    
    
    public int[] getLocation() {return location;}
 
    public short getBiomeID() {return biomeID;}
 
    public int getPopDensity() {return pop_density;}
 
 
    public int getYield() {return yield;}
    
    public short getMaxGoods(){return maxGoods;}
    
    public byte getMaxPop() {return maxPop;}
    
    
    
    
    
    //Other methods
    
    public boolean contains(Good good) {
    	return this.getGoodsOnTile().contains(good);
    	}
    }
 





