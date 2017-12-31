
/* --------------------------------------------------------------------------------------------------------------- */
/* Wallet class (data type) which implements the WalletI interface.                                                */
/* --------------------------------------------------------------------------------------------------------------- */

import java.lang.IllegalArgumentException;

public class Wallet implements WalletI {
// Instance variable defs:
   private MoneyI cash;

// Invariant def:
   private boolean invariant() { 
       return this.cash != null && this.cash.totalEuro() >= 0 && this.cash.remainingCent() >= 0; 
   }

// Constructor defs:
   public Wallet() { this.cash = new Money(0, 0); }
	
// Method defs:
/** Check the amount of money contained in this wallet; return the amount of money in this wallet. */
   public MoneyI check() { return this.cash; }

/** Put a sum of money in to this wallet; increasing amount of money in this wallet. */ 
	public void put(MoneyI sum) {
		if(sum == null) throw new IllegalArgumentException("[money: " + sum + "]");
		this.cash = this.cash.plus(sum);
	}

/** Take a sum of money from this wallet; assuming the sum taken is least equal to the amount of money contained in this wallet; otherwise the operation raises an InsufficientMoneyInWalletException and has no effect on this wallet. */  
	public void take(MoneyI sum) throws InsufficientMoneyInWalletException {
		if(sum == null) throw new IllegalArgumentException("[money: " + sum + "]");

		try{
            this.cash = this.cash.minus(sum); /* May throw a MoneyI.InsufficientMoneyException */
		}
		catch (MoneyI.InsufficientMoneyException e) {   
			throw new InsufficientMoneyInWalletException();
		}
	}
	
	
}   /* end Wallet class */
