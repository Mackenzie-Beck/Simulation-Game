package simlationGame;
import java.util.ArrayList;
import java.util.Arrays;

public class Pop {
	
	// Attributes ---------------------------------------------------------------------------------------
	//Need Attributes
	private Needs needs;
	
	
	//action attributes
    private boolean[] actionFlags; 
    
    
    
    // Have to figure out a way to sort these. Maybe draw out the UML diagrams with someone one day.
    private byte gender;
    private byte culture;
    
    
    //private float[] ideology;
    
    
    private byte occupation;
    private short age;
    
    //Labor attributes.
   // private float labor = 100;
    //private float LaborRecoveryrate = (float)0.5;
    
    private Tile currentTile;
    
    
    
    // Constructors --------------------------------------------------------------------------------------
    public Pop(Tile tile){
    	this.needs = new Needs(this);
    	
    	// Set actionFlags initial state here
    	this.actionFlags = new boolean[2];
    	Arrays.fill(actionFlags, false);
    	actionFlags[1] = true;
    	
    	
    	this.currentTile = tile;
    	tile.add_pop(this);
    	
    }
    
    
    // getters and Setters-------------------------------------------------------------------------------------

    public Needs getNeeds() {
        return needs;
    }



    public byte getGender() {
        return gender;
    }

    public byte getCulture() {
        return culture;
    }

    public byte getOccupation() {
        return occupation;
    }

    public short getAge() {
        return age;
    }
    
    public boolean[] getActionFlags() {
    	return actionFlags;
    }

    public void setActionFlags(byte index, boolean value) {
    	actionFlags[index] = value;
    }


	/*
	 * public float getLabor() { return labor; }
	 * 
	 * public float getLaborRecoveryRate() { return LaborRecoveryrate; }
	 */

    public Tile getCurrentTile() {
        return currentTile;
    }

    
    
    // action methods that allow the pop to interact with the world -----------------------------------------------------------------
    
    /*
     * produce method allows a pop to produce goods on a given tile. The tile parameter is the tile that the pop is currently residing on
     */
    public void produce(Tile tile) {
    	
    	int yield = tile.getYield();
    	int count;
    	for (count = 0; count < yield; count++) {
    		new Good((byte)tile.getBiomeID(), tile);
    		}
    }
    
    /*
     * consumes a good object from the pops current tile. INCOMPLETE
     * needs logic for determining what needs the consumed good satisfies in the pop. 
     */
    public void consume(Tile tile, byte goodID) {
    	byte i;
    	
    	for (i=0; i < tile.getGoodsOnTile().size(); i++) {
    		//if the good stored on the tile is the same kind as the consumed good: Then reduce the appropriate need value and delete the specific good object
    		if (tile.getGoodsOnTile().get(i).getGoodID() == goodID) {
    			float goodValue = tile.getGoodsOnTile().get(i).getNeedValues()[goodID];
    			this.needs.setNeed("food", goodValue);
    			tile.getGoodsOnTile().remove(i);}
        }
    	
    }


  //  public void ask(Pop target, int need) {}
    //public void trade(Good buying, Good selling) {}
    //public void steal(Pop target, Good stealing) {}
    //public void give(Pop target, Good good) {}
    //public void sleep() {}
    
    
    /*
     * Method to allow pops to die. Deletes all references to the pop. Complete for now, however will have to incorporate inventory and family 
     * references when those become applicable.
     */
    public void die(Pop pop){
    	pop.currentTile.getpopsOntile().remove(pop);
    }
    
    /*
     * Allows pops to reproduce. functional for now, although I will have to decide what conditions are suitable for reproduction 
     * The method also only requires one pop and does not take sex into account. Definitley needs some work
     */
    public void reproduce(Tile tile) {
        Pop offspring = new Pop(tile);
        tile.add_pop(offspring);
    }
    
    
    // need and update functions ------------------------------------------------------------------------------------------------------
    
    /*
     * Updates pops age and checks if its so old that it dies 
     */
   public void updateAge() {age++; if (age >= 100) {die(this);}}
   
   
   /*
    * updates need values and weightings. Returns a byte value which represents what need is to be satisfied. 
    * This whole system will need to be reworked. 
    * 
    */
   public void updateNeeds() {
	   this.needs.updateNeeds();
	   this.needs.updateNeedWeight();
	   this.needs.actionFlagSetter();
   }

   
   public void satisfyNeeds() {
	   /*
	    * This function will loop through the pops needWeight list and take the highest value needWeight. Using the index to determine which need is satisfied.
	    * Will look through truth flags to determine which actions are valid. And then apply logic to choose which need is most appropriate. (in the current version it just loops through the 
	    * actionFlags list and picks the first true need.
	    * 
	    */
	   byte i;
	   float max = 0;
	   byte currNeed;
	   
	   
	   //for loop to find the maximum needWeighting and then get the correct need index
	   for (i=0; i < this.needs.getNeedWeight().size(); i++) {	   
		   if (this.needs.getNeedWeight().get(i) < max) { 
			   max = this.needs.getNeedWeight().get(i);
			   currNeed = i;}}
	   
	   // For loop to find the first true actionFlag and then satisfy the need
	   for (i=0; i < this.actionFlags.length; i++) {

		   
		   
			// For loop to find the first true actionFlag and then satisfy the need
			   for (i=0; i < this.actionFlags.length; i++) {
			       if (this.actionFlags[i]) {
			         
			               if (i == 0 && !(this.currentTile.getGoodsOnTile().isEmpty()) ) {
			                   this.consume(this.currentTile, (byte)0);
			               } else if (i == 1 && (this.currentTile.getGoodsOnTile().size() < this.currentTile.getMaxGoods())) {
			                   this.produce(this.currentTile);
			               }
			           } 
			       }
			   }
		   
		  
		   
	   } 
	   
	   
	  
   
   
   
   
//end of class 

	   }

   
   
   
   


