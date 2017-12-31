/* --------------------------------------------------------------------------------------------------------------- */
/* Money class (data type) which implements the MoneyI interface.                                                  */
/* --------------------------------------------------------------------------------------------------------------- */

import java.lang.IllegalArgumentException;

public class Money implements MoneyI{
// Instance variable defs:  
   private final static int EURO_DIV_CENT = 100;
   private final int euro, cent;
	
// Invariant def:
   private boolean invariant() { return this.euro >= 0 && 0 <= this.cent && this.cent < Money.EURO_DIV_CENT; }

// Constructor defs:  
/** Construct a sum of money. */
   public Money(int euro, int cent) { 
       if (euro < 0 || cent < 0) throw new IllegalArgumentException("[euro: " + euro + ", cent: " + cent + "]");
       //this.euro = euro + (cent / this.EURO_DIV_CENT);   // warning "static"
       this.euro = euro + (cent / Money.EURO_DIV_CENT);
       this.cent = cent % Money.EURO_DIV_CENT;
   }
/** Construct a zero sum of money. */
   public Money() { this(0, 0); }
	
// Method defs:	
/** Return the total Euro value of this sum of money; this total must include the conversions of all multiples of 100 Cent in this sum of money to 1 Euro values, e.g., if the sum of money is 10 Euro and 214 Cent then totalEuro() should return 12. */
   public int totalEuro() { return this.euro; }

/** Return the remaining Cent value of this sum of money having converted multiples of 100 Cent in this sum of money to 1 Euro values, e.g., if the sum of money is 10 Euro and 214 Cent then remainingCent() should return 14. */
   public int remainingCent() { return this.cent; }


/** Return a new sum of money, whose value equals, the value of this sum of money plus the value of the given sum of money; [Note: By the "given sum of money" I mean the object which is pasted as a parameter to this method (operation) when it is applied to another object]. */
	public MoneyI plus(MoneyI money)  {

		if(money == null)
		{
			throw new IllegalArgumentException("[money: " + money + "]\r\n");
		}
       return new Money(this.totalEuro() + money.totalEuro(), this.remainingCent() + money.remainingCent());
	}

/** Return a new sum of money, whose value equals, the value of this sum of money minus the value of the given sum of money; assuming the value of this sum of money is greater than the value of the given sum of money; otherwise the operation throws an InsufficientMoneyException. */  
	public MoneyI minus(MoneyI money) throws InsufficientMoneyException {
        if (money == null) throw new IllegalArgumentException("[money: " + money + "]");
		
		if(this.compareTo(money)<0) 
		{
			throw new InsufficientMoneyException();
		}

       int euro = this.totalEuro();
       int cent = this.remainingCent();
       if (this.remainingCent() < money.remainingCent()) { /* Note: €50 and 50c minus €49 and 70c equals €0 and 80c. */
          euro -= 1;
          cent += EURO_DIV_CENT;
       }
       return new Money(euro - money.totalEuro(), cent - money.remainingCent());  
	}

	
/** Note: Since the MoneyI interface "is a specialisation of" (extends) the Comparable<MoneyI> interface any class which implements the MoneyI interface must also implement the Comparable<MoneyI> interface, that is, your implementing class must also provide a definition for the compareTo method (operation) which compares this sum of money with the given sum of money for order and returns -1, 0, or +1 as this sum of money is less than, equal to, or greater than the given sum of money. */
	public int compareTo(MoneyI money) {
        if (money == null) throw new IllegalArgumentException("[money: " + money + "]");
		if (this.totalEuro() > money.totalEuro())
			return 1;
		else if (this.totalEuro() < money.totalEuro())
			return -1;
		else
		{
			if (this.remainingCent() > money.remainingCent())
				return 1;
			else if (this.remainingCent() < money.remainingCent())
				return -1;
			else 
				return 0;
		}
	}

	/** Convert this sum of money to a String object. */
	   @Override 
	   public String toString() 
	   { 
		   return String.format("€%3d.%02d ", totalEuro(), remainingCent());
//		    return "€" + this.totalEuro() + " and " + this.remainingCent() + "c"; 
	   }
	   
	  
}   /* end Money class */
