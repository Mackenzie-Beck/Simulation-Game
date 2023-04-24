package simlationGame;

import java.util.Scanner;
import java.util.random.*;

public class main {

	public static void main(String[] args) throws InterruptedException{
		boolean playing = true;
		Scanner input = new Scanner(System.in);
		int day = 0;
		byte hour = 0;
		int year = 0;
		GUI simulationTime = new GUI((byte)0);
		
		
		
		
		
		//setup
		System.out.println("What is the worldSize?");
		
		Grid.worldSize = input.nextInt();
		
		//create for loop to run Tile constructor for the correct number of rows and columns
    	int row,col, i, j;
    	Tile[][] grid = new Tile[Grid.worldSize][Grid.worldSize];
    	for (row=0; row < Grid.worldSize; row++) {
    		for (col=0; col < Grid.worldSize; col++){
    			int[] location = new int[2];
    			int yield = (int)(Math.random()*10);
    			byte biomeID = (byte)(Math.random()*4);
    			location[0] = row;
    			location[1] = col;
    			
    			Tile tile = new Tile(location, yield, biomeID);
    			grid[row][col] = tile;
    		} //end of inner loop
	
	}// end of outer loop
    	// now there is a square grid of tiles this is defined by worldSize can eventually set length and width variables
    	//so world can be further customized

    	//For loop for populating grid
    	for (col = 0; col < Grid.worldSize; col++) {
    		for (row = 0; row < Grid.worldSize; row++) {
    			for(i = 0, j = 2; i < j; i++) {
    				new Pop(grid[col][row]);
    			} 
    		}}
    	
    	
		//current main game loop
		while (playing) {



	    	
	    	//Put a for loop here to iterate through the entire grid to update pop states.
	    	for (row = 0; row < Grid.worldSize; row++) {
	    		for (col = 0; col < Grid.worldSize; col++) {
	    			for (i = 0; i < grid[row][col].getpopsOntile().size(); i++) {
	    				
	    				
	    				//update pop needs
	    				//first check if the tile is empty of pops so you do not throw an exception
	    				if( grid[row][col].getpopsOntile().size() > 0 ) {
	    					grid[row][col].getpopsOntile().get(i).updateNeeds();
	    					
	    					if (grid[row][col].getpopsOntile().size() > 0 ) {
	    						grid[row][col].getpopsOntile().get(i).satisfyNeeds();
	    					}}
	    				
	    				//display some output for debugging
	    				System.out.print(grid[row][col].getpopsOntile().isEmpty() + " ");
	    				System.out.print(grid[row][col].getGoodsOnTile().size() + " ");
	    				System.out.println();
	    				
	    			}
	    		}	
	    	}
	    	
	    	
	    	
	    	

	   // Date simulation for aging timing functionality---------------------------------------------------------------
	    	//also where pop aging occurs
	    	hour++;
	    	simulationTime.updateTime(hour, day, year);
	    	if (hour ==24) {
	    		hour = 0;
	    		day++;
			if (day == 365) {
				day = 0;
				year++;
				for (col = 0; col < Grid.worldSize; col++) {
		    		for (row = 0; row < Grid.worldSize; row++) {
		    			for (i = 0; i < grid[col][row].getpopsOntile().size(); i++) {
		    				((Pop) grid[col][row].getpopsOntile().get(i)).updateAge();}}}}
			
		
		
			
			//end of time loop
	    	}

		
		//controls speed of simulation 
		//Thread.sleep(50);
		
		}
		
	 //end of main while loop
	}
		
		

}
