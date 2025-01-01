/**
 * This program make the given number reverse and sum its digits. Also determines the sum of its digits are even or odd.
 * If user enters non-positive integer or a number whose digit number is more than 8, the program terminates.
 * 
 * @author Ece ŞEŞEN
 * @version 23.10.2024
 */

 import java.util.Scanner;
 public class Lab04_Q3R
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
                 System.out.println("Sum of digits: " + sum);

                 if(sum % 2 == 0)
                 {
                    System.out.println("Sum of digits are even.\n");
                 }
                 else
                 {
                    System.out.println("Sum of digits are odd.\n");
                 }
 
                 System.out.print("Enter a positive integer: ");
                 input = in.nextInt();
             }
         }
         in.close();
     }
 }
 