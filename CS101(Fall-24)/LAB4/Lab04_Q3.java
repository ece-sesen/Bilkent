/**
 * This program both reverse the given number and sum its digits.
 * If user enters non-positive integer or a number whose digit number is more than 8, the program terminates.
 * 
 * @author Ece ŞEŞEN
 * @version 15.10.2024
 */

import java.util.Scanner;
public class Lab04_Q3 
{
    public static void main(String[] args)
    {
        final int MAX_DIGIT = 8;
        Scanner in = new Scanner(System.in);
        boolean done = false;
        System.out.print("Enter a positive integer: ");
        int input = in.nextInt();

        while(!done)
        {
            int digitNum = (input + "").length();
            if(input <= 0 || digitNum > 8)
            {
                done = true;

                if(input <= 0)
                {
                    System.out.println("Number is not positive. ");
                }
                else
                {
                    System.out.println("Number exceeds " + MAX_DIGIT + " digits.");
                }
                System.out.println("Program finished.");
            }
            else
            {
                int counter = 1;
                int digit = 0;
                int sum = 0;
                int reverseNumber = 0;

                while(input > 0)
                {
                    digit = input % 10;
                    sum += digit;
                    input = input / 10;
                    reverseNumber += (Math.pow(10, (digitNum - counter)) * digit);
                    counter++;
                }
                System.out.println("Reversed number: " + reverseNumber);
                System.out.println("Sum of digits: " + sum + "\n");

                System.out.print("Enter a positive integer: ");
                input = in.nextInt();
            }
        }
        in.close();
    }
}
