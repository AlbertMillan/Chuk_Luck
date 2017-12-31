import java.lang.Comparable; /** A generic interface to impose a total ordering on the objects of the  
     class that is required to implement it. */ 
import java.lang.Exception;  /** A class that indicates events that a application must catch and handle. */ 

/** MoneyI interface "is a specialisation of" (extends) the Comparable<MoneyI> interface. */ 
public interface MoneyI extends Comparable<MoneyI> {  
// Inner classes: 
 /** InsufficientMoneyException class which "is a specialisation of" (extends) the Exception class and whose  
     objects represent the event of trying to subtract a larger sum of money from a smaller sum of money. */ 
     public class InsufficientMoneyException extends Exception { };           
// Method signatures: 
 /** Return the total Euro value of this sum of money; this total must include the conversions of all  
     multiples of 100 Cent in this sum of money to 1 Euro values, e.g., if the sum of money is 10 Euro and  
     214 Cent then totalEuro() should return 12. */ 
     public int totalEuro(); 
 /** Return the remaining Cent value of this sum of money having converted multiples of 100 Cent in this sum  
     of money to 1 Euro values, e.g., if the sum of money is 10 Euro and 214 Cent then remainingCent() should 
     return 14. */ 
     public int remainingCent(); 
 /** Return a new sum of money, whose value equals, the value of this sum of money plus the value of the  
     given sum of money. [Note: By the "given sum of money" I mean the object which is passed as a parameter 
     to this method (operation) when it is applied to another object.] */ 
     public MoneyI plus(MoneyI money);  
 /** Return a new sum of money, whose value equals, the value of this sum of money minus the value of the  
     given sum of money; assuming the value of this sum of money is greater than the value of the given sum  
     of money; otherwise the operation throws an InsufficientMoneyException. */   
     public MoneyI minus(MoneyI money) throws InsufficientMoneyException; 
 /** Note: Since the MoneyI interface "is a specialisation of" (extends) the Comparable<MoneyI> interface any 
     class which implements the MoneyI interface must also implement the Comparable<MoneyI> interface, that  
     is, your implementing class must also provide a definition for the compareTo method (operation) which  
     compares this sum of money with the given sum of money for order and returns -1, 0, or +1 as this sum  
     of money is less than, equal to, or greater than the given sum of money. */ 
     public int compareTo(MoneyI money);  
}