/**
 * This program calculates the sum of entered number's digits.
 * User can terminate the program by entering zero as input.
 * 
 * @author Ece ŞEŞEN
 * @version 15.10.2024
 */

import java.util.Scanner;
public class Lab04_Q2 
{
    public static void main(String[]args)
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        System.out.print("Enter a positive number: ");
        int input = in.nextInt();

        while(!done)
        {
            if(input == 0) //Terminate the program
            {
                done = true;
                System.out.println("Program finished.");
            }
            else
            {
                int sum = 0;
                int digit = 0;

                while(input > 0) //Calculates the sum of digits
                {
                    digit = input % 10;
                    sum += digit;
                    input = input / 10;
                }
                System.out.println("Sum of digits " + sum + "\n");
                System.out.print("Enter a positive number: ");
                input = in.nextInt();
            }
        }
        in.close();
    }
}
