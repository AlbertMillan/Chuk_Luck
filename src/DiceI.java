
public interface DiceI {
	// Method signatures:  
 /** Return the number of faces on this dice. [Note: By "this dice" I mean the object which this method  
     (operation) is applied to.] */ 
     public int faces(); 
 /** Get the top face value on this dice. */ 
     public int value(); 
 /** Simulates rolling this dice by randomly changing its top face value. */ 
     public void roll();  
}
