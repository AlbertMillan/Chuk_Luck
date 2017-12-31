import java.lang.Exception; /** A class that indicates events that a application must catch and handle.  */

public interface WalletI { 
// Inner classes: 
 /** An InsufficientMoneyInWalletException class which "is a specialisation of" (extends) the Exception  
     class and whose objects represent the event of trying to take a sum of money from a wallet which  
     contains an insufficient amount of money. */ 
     public class InsufficientMoneyInWalletException extends Exception { };
// Method signatures: 
 /** Check the amount of money contained in this wallet; return the amount of money in this wallet. */ 
     public MoneyI check();         
 /** Put a sum of money in to this wallet; increasing amount of money in this wallet. */  
     public void put(MoneyI sum);  
 /** Take a sum of money from this wallet; assuming the sum taken is least equal to the amount of money  
     contained in this wallet; otherwise the operation raises an InsufficientMoneyInWalletException and  
     has no effect on this wallet. */   
     public void take(MoneyI sum) throws InsufficientMoneyInWalletException;  
}