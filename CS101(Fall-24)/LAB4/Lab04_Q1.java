/**
 * This class calculates the factorial of input.
 * User can terminate the program by entering sentinal value (-1).
 * 
 * @author Ece ŞEŞEN
 * @version 15.10.2024
 */

import java.util.Scanner;
public class Lab04_Q1
{
    public static void main (String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        System.out.print("Enter a number: ");
        int input = in.nextInt();

        while(!done)
        {
            //Sentinal value to teminate the program
            if(input == -1)
            {
                done = true;
                System.out.println("Program finished.");
            }
            else
            {
                int counter = 1;
                int factorial = 1;

                //Calculate factorial
                while(counter <= input)
                {
                    factorial = factorial * counter;
                    counter++;
                }

                System.out.println("Factorial of " + input + " is " + factorial + "\n");
                
                System.out.print("Enter a number: ");
                input = in.nextInt();
            }
        }
        in.close();
    }
}