public class MyBet  {

// Object (instance) variable definitions:

    /** Private money to bet. */
        private MoneyI value, playResult;

    /** Private number of GIVE_A_NUMBER bet. */
        private int number;

    /** Private type of bet. */
        private int type;

        public final static int GIVE_A_NUMBER = 2;
        public final static int TRIPLE        = 3;
        public final static int FIELD         = 4;
        public final static int HIGH          = 5;
        public final static int LOW           = 6;

// Constructor definitions:

    /** Construct MyBet object with the given type, money and number (if type=GIVE_A_NUMBER). */
        public MyBet(int type, MoneyI value, int number) {
            /* Throw an illegal argument exception if type<0 or type>4. */
            if (type < GIVE_A_NUMBER || type > LOW)
                throw new IllegalArgumentException
                   ("Type of bet not known  (should be between 0 and 4)");

            /* Throw an illegal argument exception if type<0 or type>4. */
            if (value.compareTo(ChuckALuck.ceroEuros)<=0)
                throw new IllegalArgumentException("You have to put money to bet");

            this.type  = type;
            this.value = value;
            this.number= number;
        }


// Object (instance) method definitions:
        public MoneyI value() {
            return this.value;
        }

        public MoneyI playResult() {
            return this.playResult;
        }



   /** Play */
        public boolean play(DiceI [] arrayDices) {
            boolean win= false;

            // Print the original bet
            System.out.println("       >>  " +  toString());


            switch (this.type)
            {
            case GIVE_A_NUMBER:   win= playGiveANumber(arrayDices);   break;
            case TRIPLE       :   win= playTriple(arrayDices);        break;
            case FIELD        :   win= playField(arrayDices);         break;
            case HIGH         :   win= playHigh(arrayDices);          break;
            case LOW          :   win= playLow(arrayDices);           break;
            default           :  break;
            }


            return win;
        }


   /** playGiveANumber */
        public boolean playGiveANumber(DiceI [] arrayDices) {
            int euros=0;
            int cents=0;
            boolean win= true;



            if (number==arrayDices[0].value() && number==arrayDices[1].value() && number==arrayDices[2].value() )
            {
                euros= value.totalEuro()     * 10;
                cents= value.remainingCent() * 10;
                playResult= new Money(euros,cents);
                System.out.println("              - Winnings:  "+ playResult.toString() + ",   Payout (Odds):   [10:1] '"+ number + "' on 3 dice");
            }
            else if (  (number==arrayDices[0].value() && number==arrayDices[1].value())  ||
                       (number==arrayDices[0].value() && number==arrayDices[2].value())  ||
                       (number==arrayDices[1].value() && number==arrayDices[2].value())
                    )
            {
                euros= value.totalEuro()     * 2;
                cents= value.remainingCent() * 2;
                playResult= new Money(euros,cents);
                System.out.println("              - Winnings:  "+ playResult.toString() + ",   Payout (Odds):   [2:1] '"+ number + "' on 2 dice");
            }
            else if (number==arrayDices[0].value() || number==arrayDices[1].value() || number==arrayDices[2].value() )
            {
                euros= value.totalEuro()     * 1;
                cents= value.remainingCent() * 1;
                playResult= new Money(euros,cents);
                System.out.println("              - Winnings:  "+ playResult.toString() + ",   Payout (Odds):   [1:1] '"+ number + "' on 1 dice");
            }
            else
            {
                win = false;
                System.out.println("              - Losings :  "+ value.toString() + ",   Payout (Odds):   [1:1] if '"+ number + "' on 1 dice. [2:1] if '"+ number + "' on 2 dice. [10:1] if '"+ number + "' on 3 dice");
            }

            return win;
        }


   /** playTriple */
        public boolean playTriple(DiceI [] arrayDices) {
            int euros=0;
            int cents=0;
            boolean win= true;



            if (arrayDices[0].value()==arrayDices[1].value() &&
                arrayDices[0].value()==arrayDices[2].value() &&
                arrayDices[0].value()>1 && arrayDices[0].value()<6)
            {
                euros= value.totalEuro()     * 30;
                cents= value.remainingCent() * 30;
                playResult= new Money(euros,cents);
                System.out.println("              - Winnings:  "+ playResult.toString() + ",   Payout (Odds):  [30:1] (All 3 dice show same number [but not 1s or 6s]");
            }
            else
            {
                win = false;
                System.out.println("              - Losings :  "+ value.toString() + ",   Payout (Odds):   [30:1] (All 3 dice show same number [but not 1s or 6s]");
            }

            return win;
       }

   /** playField */
        public boolean playField(DiceI [] arrayDices) {
            int euros=0;
            int cents=0;
            boolean win= true;

            int sum= arrayDices[0].value() + arrayDices[1].value() + arrayDices[2].value();


            if (sum<8 || sum>12 )
            {
                euros= value.totalEuro()     * 1;
                cents= value.remainingCent() * 1;
                playResult= new Money(euros,cents);
                System.out.println("              - Winnings:  "+ playResult.toString() + ",   Payout (Odds):   [1:1] (Total of 3 dice < 8 or Total is > 12)");
            }
            else
            {
                win = false;
                System.out.println("              - Losings :  "+ value.toString() + ",   Payout (Odds):   [1:1] (Total of 3 dice < 8 or Total is > 12)");
            }

            return win;
       }


   /** playHigh */
        public boolean playHigh(DiceI [] arrayDices) {
            int euros=0;
            int cents=0;
            boolean win= true;

            int sum= arrayDices[0].value() + arrayDices[1].value() + arrayDices[2].value();
			boolean triple= (arrayDices[0].value()==arrayDices[1].value() && arrayDices[0].value()==arrayDices[2].value());


			if ( !triple  &&  sum>10)
            {
                euros= value.totalEuro()     * 1;
                cents= value.remainingCent() * 1;
                playResult= new Money(euros,cents);
                System.out.println("              - Winnings:  "+ playResult.toString() + ",  Payout (Odds):   [1:1] (Total of 3 dice > 10 [but not a Triple])");
            }
            else
            {
                win = false;
                System.out.println("              - Losings :  "+ value.toString() + ",   Payout (Odds):   [1:1] (Total of 3 dice > 10 [but not a Triple])");
            }

            return win;
       }


   /** playHigh */
        public boolean playLow(DiceI [] arrayDices) {
            int euros=0;
            int cents=0;
            boolean win= true;

			int sum= arrayDices[0].value() + arrayDices[1].value() + arrayDices[2].value();
			boolean triple= (arrayDices[0].value()==arrayDices[1].value() && arrayDices[0].value()==arrayDices[2].value());


			if ( !triple  &&   sum<11)
            {
                euros= value.totalEuro()     * 1;
                cents= value.remainingCent() * 1;
                playResult= new Money(euros,cents);
                System.out.println("              - Winnings:  "+ playResult.toString() + ",  Payout (Odds):   [1:1] (Total of 3 dice < 11 [but not a Triple])");
            }
            else
            {
                win = false;
                System.out.println("              - Losings :  "+ value.toString() + ",   Payout (Odds):   [1:1] (Total of 3 dice < 11 [but not a Triple])");
            }

            return win;
       }



   /** Convert this MyBet to a String object; specialising the inherited toString method. */
    @Override public String toString() {
            String s =  value.toString() ;   // Money bet
            switch (this.type)
            {
            case GIVE_A_NUMBER:  s+= ",   'Give a number',   number: '" + this.number + "'";    break;
            case TRIPLE       :  s+= ",   'Triple' ";                               break;
            case FIELD        :  s+= ",   'Field'  ";                               break;
            case HIGH         :  s+= ",   'High'   ";                               break;
            case LOW          :  s+= ",   'Low'    ";                               break;
            default           :  break;
            }

            return s;
        }

} /* end Mybet class */
