import java.util.*;

public class ChuckALuck {
	
	//----------------------------------------------
	public static MyScanner input         = new MyScanner();
	public static MoneyI ceroEuros        = new Money(0,0);  
	public static DiceI [] arrayDices     = new Dice[3];
	public static ArrayList<MyBet> myBets = new ArrayList<MyBet>();
	//----------------------------------------------

	
	// =================================================================
	public static void main(String[] args) {
	// =================================================================
        int     option       = 1;   // Menu
        boolean endOfProgram = false;

        // ------------------------------
		WalletI myWallet = new Wallet();
		MoneyI myBetI    = ceroEuros;
        // ------------------------------
	    arrayDices[0]= new Dice();
	    arrayDices[1]= new Dice();
	    arrayDices[2]= new Dice();
			
		System.out.print("Welcome to Chuck-a-Luck, a game of chance. "); 
      		 
        // while the wallet has money and the user doesn't select 0  (exit)		
		while ( (option != 0) &&  (endOfProgram == false) )
		{
			// ---   --------------------------
			option = menu( myWallet.check(), myBets );
			// ---   --------------------------
			try {
				switch (option)
				{
					case 1: 
						MoneyI cash = input.readMoney("How much money do you want to add to your Wallet [Format: x.xx  or  x,xx]?  ");
						if(cash.compareTo(ceroEuros)>0)
						{
							myWallet.put(cash);
						}
						break;
					        
					case 2:  // GIVE_A_NUMBER
					case 3:  // TRIPLE
					case 4:  // FIELD
					case 5:  // HIGH 
					case 6:  // LOW
						int number=1;
						System.out.println("\r\nMoney in your Wallet: "+ myWallet.check().toString()+". " );
						myBetI=  input.readMoney("How much money do you want to bet [Format: x.xx  or  x,xx]?  ") ;
						if(myBetI.compareTo(ceroEuros)>0)
						{
							myWallet.take(myBetI);   /* May throw a WalletI.InsufficientMoneyInWalletException */
							if (option == MyBet.GIVE_A_NUMBER)
							{
								System.out.print  ("Give a number: ");
						    	number= input.readInteger(1,6);
							}
							//---------------------------------------------
							myBets.add(new MyBet(option, myBetI, number));
							//---------------------------------------------
						}
						break;
 
					case 7: play(myWallet);
					    break;
					        
				}
			}
			catch (IllegalArgumentException e) {      // Be careful!!! ----  Money.Insuff..
				System.out.println(e.toString());
				endOfProgram= true;     
			}
			catch (WalletI.InsufficientMoneyInWalletException e) {      
				// e.printStackTrace();
				System.out.println("\r\nInsufficient money in your wallet.");
				System.out.println("Wallet: " + myWallet.check() + "  <  Bet: " +  myBetI);
				myBetI= ceroEuros;
				//endOfProgram= true;     
			}
			
			
			if (endOfProgram!=true)
			{
	  		    // If money in Wallet is less or equal to 0 euros
				endOfProgram= ( myWallet.check().compareTo(ceroEuros) <= 0 && 
						        myBets.isEmpty());
			}
		}
		if(myWallet.check().compareTo(ceroEuros) > 0)
		{
			System.out.println("End of Game!!!");  
			System.out.println("Money in your wallet: "+ myWallet.check().toString());
		}
		else
		{
			System.out.println("End of Game because money in your wallet is equal to zero euro.");
		}
	}		

	
	// =================================================================
	public static void play(WalletI myWallet) {
	// =================================================================
        arrayDices[0].roll();   
        arrayDices[1].roll();
        arrayDices[2].roll();
		
        /* Only for testing purposes */
//		((Dice)arrayDices[0]).set(1);
//		((Dice)arrayDices[1]).set(1);
//		((Dice)arrayDices[2]).set(1);
		
        
        System.out.println("Dice rolls... ");
        System.out.println("     +-----+-----+-----+");
        System.out.println("     |  "+arrayDices[0].value() + "  |  " + arrayDices[1].value() + "  |  " + arrayDices[2].value() + "  |");
        System.out.println("     +-----+-----+-----+");
        System.out.println();
        System.out.println();
		
		System.out.println("Play results... ");
		for (int i=0; i< myBets.size();i++)
		{
			// This function writes a message
			if (myBets.get(i).play(arrayDices))   // Winning
			{
				myWallet.put(myBets.get(i).value());
				myWallet.put(myBets.get(i).playResult());
			}
		}
		
		//----------------------------
		myBets.clear();     
		//----------------------------
	}
	
	
	//========================================================
	public static int menu(MoneyI moneyInWallet, ArrayList<MyBet> bets)
	//========================================================
	{
		int max=1;   // Max number of options in menu
		System.out.println("\n\n");
		System.out.println("==============================================");
		System.out.println("  Menu:      (Wallet= " + moneyInWallet + ")");
		System.out.println("==============================================");
		System.out.println("  1. Put Money in your wallet ");
		if (moneyInWallet.compareTo(ceroEuros)>0 || !myBets.isEmpty())
		{
			max=6; // Max number of options in menu
			System.out.println("   . .....................");
			System.out.println("   . New Bet: ");
			System.out.println("   . .....................");
			System.out.println("  2.   - Give a number");
			System.out.println("  3.   - Triple");
			System.out.println("  4.   - Field");
			System.out.println("  5.   - High");
			System.out.println("  6.   - Low");
			
			if (!bets.isEmpty())
			{
				System.out.println("   . ..........................................");
				System.out.println("   . Your Bets: ");
				System.out.println("   . ..........................................");
				for (int i=0; i< bets.size();i++)
				{
					System.out.println("          >>   " + bets.get(i).toString() );
				}
				max= 7;  // Max number of options in menu
				System.out.println();
				System.out.println("  - ------------------------------------------");
				System.out.println("  7. Play");
			}
			System.out.println("  - ------------------------------------------");
			System.out.println("  0. Exit Game");
			System.out.println("==============================================");
		}
		
		return input.readInteger(0,max);
	}

}
		

