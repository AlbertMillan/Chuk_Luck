/* ------------------------------------------------------------------------------------------------- */
/* Dice class [which implements DiceI interface]                                                     */
/* ------------------------------------------------------------------------------------------------- */

public class Dice implements DiceI {

// Object (instance) variable definitions:

    /** Private fixed number of faces on this dice. */
        private final int faces;

    /** Private current top face value of this dice. */
        private int value;

    /** Unique identifier for this Dice object. */
        private final int id;  

// Class (non-instance) variable definitions: 

    /** Class variable dynamically counting the number of Dice objects created. */
        private static int instances = 0;

// Constructor definitions:     

    /** Construct a dice with the given number of faces (numberOfFaces) numbered from 1 though to numberOfFaces (where numberOfFaces is an int &ge; 2). */
        public Dice(int numberOfFaces) {
            /* Throw an illegal argument exception if numberOfFaces < 2. */
            if (numberOfFaces < 2) 
	            throw new IllegalArgumentException
	               ("For argument numberOfFaces = " + numberOfFaces + " which should be >= 2!");
        	// this.instances += 1;     // warning
        	Dice.instances += 1;
	        this.id = Dice.instances;
            this.faces = numberOfFaces; 
	        this.value = (int)(Math.random() * this.faces) + 1; 
        }

    /** Construct a D6 by calling the above constructor with 6. */
        public Dice() { 
	        this(6); /* Invoke the above Dice constructor with value 6. */
        }  

// Object (instance) method definitions:
 
   /** Return the number of faces on this dice. */
        public int faces() { 
	        return this.faces; 
        }

   /** Get the top face value on this dice. */
        public int value() { 
	        return this.value; 
        }
      
   /** Set the top face value on this dice. Only for testing purposes*/
        public void set(int n) { 
	        this.value=n; 
        }
      
   /** Simulate rolling this dice by randomly changing its top face value. */
        public void roll() { 
	        this.value = (int)(Math.random() * this.faces()) + 1; 
        }  
        
   /** Convert this dice to a String object; specialising the inherited toString method. */     
    @Override public String toString() { 
	        String s = "[" + "dice[" + this.id + "]" + ": " + this.getClass().getName() + " | " 
	        + "instances = " + Dice.instances + ", id = " + this.id + ", " 
	        + "faces = " + this.faces() + ", value = " + this.value() + "]";

	        if (1 < Dice.instances && this.id < Dice.instances) s = "\n" + s;
	        else if (this.id == Dice.instances) s = "\n" + s + "\n";
	 
	        return s;
        }     

} /* end Dice class */
