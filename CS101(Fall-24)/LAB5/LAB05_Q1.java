/**
 * This class checks input if it is valid or not.
 * Prints CS according to taken input.
 * 
 * @author Ece ŞEŞEN
 * @version 23.10.2024
 */
import java.util.Scanner;
public class Lab05_Q1
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int height = 0;
        System.out.print("Please input the height: ");

        // Take input as long as it is not valid.
        do
        {
            height = in.nextInt();
            if(height < 0)
            {
                System.out.print("It is negative number! Enter again: ");
            }
            else if( height > 30)
            {
                System.out.print("Invalid input! Enter a number less than 30: ");
            }
        }
        while(height > 30 || height < 0);
        in.close();

        //Print the CS logo
        for(int i = 1; i <= height; i++)
        {
            if(i == 1 || i == height)
            {
                System.out.println(" *****    *****");
            }
            else if ( i <= height / 2)
            {
                System.out.println("*        *     ");
            }
            else if(i == height / 2 + 1)
            {
                System.out.println("*         *****");
            }
            else if( i < height)
            {
                System.out.println("*              *");
            }
        }
    }
}