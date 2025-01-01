/**
 * This programe calculates user's IBM value considering inputs (weight and height).
 * Prints user's statue.
 * 
 * @author Ece ŞEŞEN
 * @version 09.10.2024
 */

import java.util.Scanner;
public class Lab03_Q1
{
    public static void main(String[] args)
    {
        //Constants
        final double UNDERWEIGHT = 18.5;
        final double NORMAL_WEIGHT = 24.9;
        final double OVERWEIGHT = 29.9;
    
        //Take weight and height as an input
        Scanner in = new Scanner (System.in);
        System.out.print("Enter your weight in kilograms: ");
        double weight = in.nextDouble();
        System.out.print("Enter your height in meters: ");
        double height = in.nextDouble();
        in.close();

        // Calculate BMI value and print it
        double bmi = weight / (height * height);
        System.out.printf("%s%.2f%n","Your BMI is: " , bmi);

        //Print the result
        if(bmi < UNDERWEIGHT)
        {
            System.out.println("You are underweight.");
        }
        else if(UNDERWEIGHT < bmi && bmi < NORMAL_WEIGHT)
        {
            System.out.println("You are of normal weight.");
        }
        else if(NORMAL_WEIGHT < bmi && bmi < OVERWEIGHT)
        {
            System.out.println("You are overweight.");
        }
        else
        {
            System.out.println("Your BMI indicates that you are above the recommended range."); 
        }
    }
}