/**
 * This is a computer based board game named "Ticket to Ride"
 * Game has three players and aims to complete all roads by using same colored cards.
 * 
 * Game is completed when at least one player completed its whole roads.
 * Print the number of all cards which include players' hands, the ones used for roads and cards in the remaining deck.
 * 
 * @author Ece ŞEŞEN
 * @version 23.10.2024
 */
public class Lab05_Q2 
{
    public static void main(String[] args)
    {
        //Constants
        final int PLAYER_NUMBER = 3;
        final int TOTAL_CARDS= 125;
        final int EACH_CARD = 25;
        final int DRAW_CARD = 3;
        final int EACH_START_CARD = 5;
        final int TOTAL_ROUTES = 6;

        String deck = "";

        //Player's open hands
        String player1 = "";
        String player2 = "";
        String player3 = "";

        //Create deck
        for(int i = 0; i < TOTAL_CARDS; i++)
        {
            if(i < EACH_CARD) { deck += "R"; }
            else if(i < EACH_CARD * 2) {deck += "Y";}
            else if(i < EACH_CARD * 3) {deck += "G";}
            else if(i < EACH_CARD * 4) {deck += "B";}
            else {deck += "M";}
        }

        //Mix cards and create rendom deck
        System.out.println("Welcome!\n" + "Let's shuffle the deck: ");
        int remainCards = deck.length();
        String randomDeck = "";
        
        for (int i = remainCards; i > 0; i--)
        {
            int randomIndex = (int)(Math.random() * remainCards ) ;
            char ch = deck.charAt(randomIndex);
            randomDeck += ch;
            deck = deck.replaceFirst(ch + " ", "");
        }
        deck = randomDeck;
        System.out.println(randomDeck + "\n");                                 

        //Allocate 5 cards to each player randomly.
        for(int i = 1; i <= PLAYER_NUMBER; i++ )
        {
            String allocateCard = deck.substring(0, EACH_START_CARD );
            deck = deck.replaceFirst(allocateCard, "");
            if(i == 1)
            {
                player1 += allocateCard;
            }
            else if (i == 2)
            {
                player2 = allocateCard;
            }
            else 
            {
               player3 += allocateCard;
            }
        }
        System.out.println("Game begins!\n" +
                           "   Player1: " + player1 + "\n" +
                           "   Player2: " + player2 + "\n" + 
                           "   Player3: " + player3);

        //Routes of players separately as a stirng and number of roads each completed
        String p1r2 = "", p1r3 = "", p1r4 = "", p1r5 = "", p1r6= "";
        int p1Completed = 0;
        String p2r2 = "", p2r3 = "", p2r4 = "", p2r5 = "", p2r6= "";
        int p2Completed = 0;
        String p3r2 = "", p3r3 = "", p3r4 = "", p3r5 = "", p3r6= "";
        int p3Completed = 0;

        //Game starts
        int round = 1;
        boolean isWinner = false;

        //Current player's cards and routes who is playing during execution. 
        String playingDeck = "";
        String playR2 = "";
        String playR3 = "";
        String playR4 = "";
        String playR5 = "";
        String playR6 = "";
        int playingDonedRoute = 0;

        //Game contiunes if player has not completed its all routes yet.
        do
        {
            System.out.println("\n###### Game round #" + round);

            for(int playerNo = 1; playerNo <= PLAYER_NUMBER; playerNo++)
            {
                //Tarsfer the datas from player to the current one
                if(playerNo == 1)
                {
                    playingDeck = player1;
                    playR2 = p1r2;
                    playR3 = p1r3;
                    playR4 = p1r4;
                    playR5 = p1r5;
                    playR6 = p1r6;
                }
                else if(playerNo == 2)
                {
                    playingDeck = player2;
                    playR2 = p2r2;
                    playR3 = p2r3;
                    playR4 = p2r4;
                    playR5 = p2r5;
                    playR6 = p2r6;
                }
                else
                {
                    playingDeck = player3;
                    playR2 = p3r2;
                    playR3 = p3r3;
                    playR4 = p3r4;
                    playR5 = p3r5;
                    playR6 = p3r6;
                }

                System.out.println("  ## Player" + playerNo + " ##\n" +
                                "     Previous hand: " + playingDeck );

                //Find the longest chain of same color of cards. 
                String longestRoute = "";
                String foundRoute = "";
                if(playingDeck.length() > 1)
                {
                    for(int first = 0; first < playingDeck.length() - 1; first++)
                    {
                        foundRoute = playingDeck.charAt(first) + "";
                        for(int second = first + 1; second < playingDeck.length(); second++ )
                        {
                            String ch1 = playingDeck.charAt(first) + "";
                            String ch2 = playingDeck.charAt(second) + "";
                            if(ch1.equals(ch2))
                            {
                                foundRoute += ch2;
                            }
                        }
                        if(foundRoute.length() > longestRoute.length())
                        {
                            longestRoute = foundRoute;
                        }
                    }
                }
                else
                {
                    longestRoute = playingDeck;
                }


                //Built route to current player's relevant route or make player to darw three card.
                int roadLength = longestRoute.length();
                boolean isDiscard = true; //Assume that player has series to discard. Otherwise, make player draw 3 cards and not discard anything.
                if(roadLength == 6 && playR6.equals(""))
                {
                    playR6 += longestRoute;
                }
                else if(roadLength == 5 && playR5.equals(""))
                {
                    playR5 += longestRoute;
                }
                else if(roadLength == 4 && playR4.equals(""))
                {
                    playR4 += longestRoute;
                }
                else if(roadLength == 3 && playR3.equals(""))
                {
                    playR3 += longestRoute;
                }
                else if(roadLength == 2 && playR2.equals(""))
                {
                    playR2 += longestRoute;
                }
                else //Player cannot build road so cards must be drawn
                {
                    isDiscard = false;
                    String drawCards = deck.substring(0, DRAW_CARD);
                    System.out.println("     Card drawn: " + drawCards);
                    playingDeck += drawCards;
                    deck = deck.replaceFirst(drawCards, "");
                }

                if(isDiscard) //Remove discarded cards from the player's hand
                {
                    System.out.println("     Route Completed!");
                    playingDonedRoute++;
                    playingDeck = playingDeck.replaceAll(longestRoute.charAt(0) + "", "");
                }
                
                System.out.println("     Current Hand: " + playingDeck + "\n" +
                                "     Route#2: " + playR2 + "\n" +
                                "     Route#3: " + playR3 + "\n" +
                                "     Route#4: " + playR4 + "\n" +
                                "     Route#5: " + playR5 + "\n" +
                                "     Route#6: " + playR6 + "\n");

                //Make information of player who plays update. Sync them.
                if(playerNo == 1)
                {
                    player1 = playingDeck;
                    p1r2 = playR2;
                    p1r3 = playR3;
                    p1r4 = playR4;
                    p1r5 = playR5;
                    p1r6 = playR6;
                    p1Completed += playingDonedRoute;
                }
                else if (playerNo == 2)
                {
                    player2 = playingDeck;
                    p2r2 = playR2;
                    p2r3 = playR3;
                    p2r4 = playR4;
                    p2r5 = playR5;
                    p2r6 = playR6;
                    p2Completed += playingDonedRoute;
                }
                else 
                {
                    player3 = playingDeck;
                    p3r2 = playR2;
                    p3r3 = playR3;
                    p3r4 = playR4;
                    p3r5 = playR5;
                    p3r6 = playR6;
                    p3Completed += playingDonedRoute;
                }
                playingDonedRoute = 0;

            }
            
            //If at least one of the player completed its all routes, it is the winner. End game!
            if(p1Completed == TOTAL_ROUTES - 1 || p2Completed == TOTAL_ROUTES - 1 || p3Completed == TOTAL_ROUTES - 1 )
            {
                isWinner = true;
                System.out.println("**********************\n" + 
                                   "Game finished!\n");
            }
            round++;
        }
        while(!isWinner);

        //Print the result
        String printPlayer = "";
        int printRoute = 0;
        String handDeck = "";
        int totalRoute = 0;
        int totalHand = 0;

        for(int i = 1; i <= PLAYER_NUMBER; i++)
        {
            if(i == 1)
            {
                printPlayer = "Player1";
                printRoute = p1r2.length() + p1r3.length() + p1r4.length() + p1r5.length() + p1r6.length();
                handDeck = player1;
            }
            else if( i == 2)
            {
                printPlayer = "Player2";
                printRoute = p2r2.length() + p2r3.length() + p2r4.length() + p2r5.length() + p2r6.length();
                handDeck = player2;
            }
            else
            {
                printPlayer = "Player3";
                printRoute = p3r2.length() + p3r3.length() + p3r4.length() + p3r5.length() + p3r6.length();
                handDeck = player3;
            }

            System.out.println(printPlayer + " total route points: " + printRoute +
                           "\n" + printPlayer + "'s remaining card count in the hand: " + handDeck.length() + "\n");
            totalRoute += printRoute;
            totalHand += handDeck.length();
        }
        
        System.out.println("Number of remaining cards on deck " + deck.length() + "\nTOTAL: " + (totalRoute + totalHand + deck.length()));          
    }
}
