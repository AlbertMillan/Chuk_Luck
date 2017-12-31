import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.Math;


public class MyScanner  {
	String  str; 
//	Money   myMoney;
	// -------------------------
    Scanner userInput, strInput;
	// -------------------------

	//========================================================
	public MyScanner (){
	//========================================================
		userInput= new Scanner(System.in);
//		myMoney  = new Money();
	}

	
	//========================================================
	public int readInteger(int min, int max) {    // For the Menu
	//========================================================
		int result=-1;
		
		while ( ( (result < min) || (result > max) ) ) //&& (result != 0) )
		{
		    str=  userInput.nextLine();
		    strInput= new Scanner(str);
			try
			{
				if (str.isEmpty())
				{
			        throw new IllegalArgumentException();
				}
				else
				{
					result = strInput.nextInt();  /* May throw an Exception */
					if ( (result < min) || (result > max) )
					{
						System.out.println("Input out of range"); 
					}
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("Not a valid input.");
			}
			// First item
			catch (java.util.NoSuchElementException e)
			{
				System.out.println("No Such Element.");
			}
		    catch (IllegalArgumentException e) {
	            System.out.println("No input typed."); 
		    }
			catch (Exception e)
			{
				System.out.println("Just in case");
			}
			
			if ( (result < min) || (result > max) )
			{
				System.out.println("Please enter an integer between "+min+ " and " +max);
			}
			
			// ---------------
			strInput.close();			
			// ---------------
		}
		return result;
	}	

	
	//========================================================
	public MoneyI readMoney(String strMsg) {
	//========================================================
		int euros=0;
		int cents=0;
		float x= 0;
		
		System.out.print(strMsg);  

		
	    str=  userInput.nextLine();
	    if ( str.contains(".") )
	    {
	    	str= str.replace('.',',');
	    }

	    strInput= new Scanner(str);
	    
	    try{
	    	x= strInput.nextFloat();
	        euros= (int)x;
	        cents= Math.round( (x - (float)euros) * (float)100  );
		    
		    
		    if (euros<0)
		    {
		        throw new IllegalArgumentException("Money cannot be negative.");
		    }
	    }
	    catch (InputMismatchException e) {
            System.out.println("Invalid input value!");
            euros=0;
            cents=0;
        } 
		catch (java.util.NoSuchElementException e)
		{
			System.out.println("No element. Please enter an integer between 0 and 7.");
		}
	    catch (IllegalArgumentException e) {
	        System.out.println(e.toString());
	    	euros=0;
	    	cents=0;
	    }
	    
		strInput.close();
//		myMoney.set(euros, cents);
		
	    return new Money(euros, cents);

	}
	
	
}
