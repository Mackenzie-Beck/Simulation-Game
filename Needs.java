package simlationGame;

import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;

public class Needs {
	/**
	 * This class is a subclass of Pop.java As such it needs a reference to a Pop
	 * object.
	 * 
	 * This class will store all of a pops need values, need weighting values, and the actual needs themselves. 
	 * 
	 * Very messy right now, The Good class needs to be reworked to fit into this system before it can be finished.
	 * 
	 * 
	 */

	// Attributes ------------------------------------------------------
	// This is the hashmap which will store all of a pops possible needs
	// Going forward this can be customized to represent pops of different species
	private HashMap<String, Float> needsMap = new HashMap<String, Float>();
	
	//needWeighting list
	private ArrayList<Float> needWeight =  new ArrayList<Float>();


	// Tier lists for needs. Tier1 is most important while tier 5 is least important
	private ArrayList<String> tier1;
	private ArrayList<String> tier2;
	private ArrayList<String> tier3;
	private ArrayList<String> tier4;
	private ArrayList<String> tier5;
	
	



	// Pop that is the parent of this need class
	Pop currPop;

	// Constructor
	// ---------------------------------------------------------------------------------------

	// The value of the key value pair represents the pops current value of that
	// needs
	// it is the value that determines whether a pop dies and is sued to calculate
	// needWeightings.
	// the tier lists determines at what value a need is considered lethal
	public Needs(Pop pop) {

		
		currPop = pop;

		needsMap.put("food", (float) 0);
		needsMap.put("water", (float) 0);
		// needsMap.put("air", 0);
		needsMap.put("hygiene", (float) 0);
		needsMap.put("sleep", (float) 0);
		needsMap.put("gender", (float) 0);
		needsMap.put("environmental", (float) 0);
		needsMap.put("social", (float) 0);
		needsMap.put("leisure", (float) 0);
		needsMap.put("culture", (float) 0);
		needsMap.put("sex", (float) 0);
		needsMap.put("propaganda", (float) 0);
		needsMap.put("politics", (float) 0);
		needsMap.put("travel", (float) 0);

		// Assign needs to their need tiers
		// ------------------------------------------------------------------------------

		// tier1 represents basic needs the pop will absolutely die without
		//Index of each need key in tier1 will determine index of each needs weighting in needWeighting
		tier1 = new ArrayList<String>();
		tier1.add("food");
		//tier1.add("sleep");
		//tier1.add("water");
		//tier1.add("hygiene");
		// tier1.add("air");
		
		// Need weightings here correspond to above tier
		needWeight.add((float)0); // food at index 0 
		//needWeight.add((float)0); // sleep at index 1
		//needWeight.add((float)0); // hygiene at index 2
		//needWeight.add((float)0); // water at index 3

		// tier2 represents needs which will increase the chance of death and cause
		// severe unhappiness.
		tier2 = new ArrayList<String>();
		//tier2.add("gender");
		//tier2.add("social");

		// Tier3 represents needs that cause the pops great suffering when absent, but
		// do not cause death of the pop
		tier3 = new ArrayList<String>();
		//tier3.add("culture");
		//tier3.add("environmental");

		// Tier4 and Tier5 represent needs that provide happiness, with tier5 being less
		// prioritized than tier 4.
		// This could be enhanced later. It does the job for now however.
		tier4 = new ArrayList<String>();
		//tier4.add("politics");
		//tier4.add("sex");
		//tier4.add("liesure");

		tier5 = new ArrayList<String>();
		//tier5.add("travel");
		//tier5.add("propoganda");

	}
	
	
	
	

	// Setters and Getters
	// ---------------------------------------------------------------------------------------

	public void setNeed(String need, float value) {
		if (needsMap.containsKey(need)) {
			needsMap.put(need, value);
		}
	}

	public Float getNeed(String key) {
		if (needsMap.containsKey(key)) {
			return needsMap.get(key);
		} else {
			return (float) -1;
		}
	}

    public ArrayList<Float> getNeedWeight() {
        return needWeight;
    }
	
	
    
    
    
    
    
    
	// need functions
	// ---------------------------------------------------------------------------------------
    
	public void updateNeeds() {
		/*
		 * Update need value directly in hashmap, will also check if a need is so high
		 * that the pop dies Will need a for loop for each need tier to iterate through
		 * string values of needsMap and update associated need value
		 * 
		 * Keep in mind that you can use the need tiers to set the rating as well as the severity of the need.
		 * You can set the value that it takes for a pop to die from lack of a resource and the rate per unit time of the simulation. Of increase
		 * for that need
		 * 
		 * Right now each tick is an hour in the simulation, keep that in mind when choosing a max need value and rate increase.
		 * 
		 * 
		 * tier list meanings:
		 * 
		 * tier1 - A pop dies from the needs absense after a certain period. 
		 * tier2 - after 3 months absense, so 2160 hours. The pop dies from suicide, for now this will just be another pop method identical to 
		 * 
		 */
		int i, j;
		

		// tier1 for loop, pop dies at 480 hours absense (which is 20 days)-----------------------------------------------------------
		for (i = 0; i < tier1.size(); i++) {
			// This loop updates each needValue in tier1, and then check if that need is
			// above 480 then it dies

			String tierkey = tier1.get(i);

			float need = (needsMap.get(tier1.get(i)) + (float) 1);
			this.setNeed(tierkey, need);

			// This is the if statemet where the tier1 need value are checked with the
			// string key supplied by tier1,
			
			//placeholder, eventually each need will have its own specific value which makes sense in the context of the simulation. For now since only hunger is simulated 
			//after 480 hours the pop dies of starvation
			if (need > 480) {
				currPop.die(currPop);
			}

		}

		//end of updateNeeds

	}


	public void updateNeedWeight() {
		/*
		 * Purpose: To take the now updated need values and process them into needWeightings
		 * Will take the raw need value from the needsMap dictionary and feed it through an algorithm to determine the pops prioritization of that need.
		 * 
		 * Input: values of needsMap{need: value} dictionary which are used to determine needWeights 
		 * 
		 * Output: Arraylist of needWeights stored as float values to be sorted in sortNeeds()
		 * 
		 * 
		 * A few things to consider: 
		 * 1. How does desperation affect a pops thinking? 
		 * 2. The absolute value of each needWeight will determine which need is placed first in the needsSorted list 
		 * This means that each tier1 and tier2 need will have a maximum value determined by how quickly that needs absense kills the pop.
		 * Higher level need tiers which do not cause death may still have need caps to ensure that a pop does not sacrafice themselves for something stupid like a massage or watching a movie. 
		 * 
		 * 
		 * each need may have its own values to represent an accurate rate for that need
		 * 
		 */
		byte i;
		
		for(i=0; i < tier1.size(); i++) {
			String needKey = tier1.get(i);
			float needValue = needsMap.get(needKey);
			float needWeightValue;
			
			//algorithm for food need
			if (i == 0) {
				needWeightValue = needValue/(float)480;
				needWeight.set(0, needWeightValue);}}
		
		
	}

	public void actionFlagSetter() {
		
		byte i;
		
		
		if (needWeight.get(0) > 1) {currPop.setActionFlags( (byte) 0, true);} 
		else{currPop.setActionFlags( (byte) 0, false);}
		
		}
	
	
	
	
	
	}
	
	// end of class -------------------------------------------


