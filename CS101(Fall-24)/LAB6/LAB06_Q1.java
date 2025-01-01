/**
 * This is class which includes amny mathematical calculation methods.
 * It can find least common multiple or greatest common dividor.
 * Also determines whether two number are relatively prime.
 * Gives choices to users in order to determine which calculation they want to do.
 * 
 * @author Ece ŞEŞEN
 * @version 07.11.2024
 */

import java.util.Scanner;
public class LAB06_Q1
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean quit = false;

        while(!quit)
        {
            System.out.print("\n1- Binary and its reversal representation\n" +
                             "2- Find gcd or lcm\n" +
                             "3- Quit\n" +
                             "Enter yout choice: ");
        
            int choice = in.nextInt();

            if(choice == 1)
            {
                System.out.print("\nEnter a decimal number: ");
                int number = in.nextInt();
                System.out.println("Binary representation of " + number + " is " + intToBinary(number));
                System.out.println("Binary reversal of " + number + " is " + binaryReversal(number) );
            }
            else if(choice == 2)
            {
                System.out.print("\nEnter the first number: ");
                int first = in.nextInt();
                System.out.print("Enter the second number: ");
                int second = in.nextInt();

                if(!isRelativelyPrime(first, second))
                {
                    System.out.println(first + " and " + second + "are not relatively prime.");
                }
                else
                {
                    System.out.println(first + " and " + second + "are relatively prime.");
                }

                System.out.println("Greatest common divisor of " + first + " and " + second + " is " + greatestCommonDivisor(first, second));
                System.out.println("Least common multiple of " + first + " and " + second + " is " + leastCommonMultiply(first, second));
            }
            else
            {
                System.out.println("Good bye!");
                quit = true;
            }
        }     
        in.close();    
    }

    /**
     * Find greatest common divisor and return it
     * @param x first number
     * @param y second number
     * @return greatest common divisor
     */
    public static int  greatestCommonDivisor(int x, int y)
    {
        int smaller = findMax(x, y);
        int greatDiv = smaller;
        boolean found = false;

        for(int i = smaller; !found; i--)
        {
            if(x % i == 0 && y % i == 0)
            {
                found = true;
                greatDiv = i;
            }
        }
        return greatDiv;
    }

    /**
     * Find least common multiple and return it
     * @param x first number
     * @param y second number
     * @return least common multiple
     */
    public static int leastCommonMultiply(int x, int y)
    {
        int larger = findMax(x,y);
        int smallMultiple = larger;
        boolean found = false;

        for(int i = larger; !found; i++)
        {
            if(i % x == 0 && i % y == 0)
            {
                found = true;
                smallMultiple = i;
            }
        }
        return smallMultiple;
    }

    /**
     * Determien wheter they are relatively prime or not
     * @param x first number
     * @param y second number
     * @return true if they are relatively prime
     */
    public static boolean isRelativelyPrime(int x, int y)
    {
        return greatestCommonDivisor(x, y) == 1;
    }

    /**
     * Tranform the number to its binary representattion
     * @param num number
     * @return binary number
     */
    public static long intToBinary(int num)
    {
        long binary = 0;
        int count = 0;
        while(num > 0)
        {
            int decimal = num % 2;
            binary += (takePower(10, count) * decimal);
            count++;
            num = num / 2;
        }
        return binary;
    }

    /**
     * Find reversed order of binary representation of given number
     * @param num number
     * @return reversed binary number
     */
    public static long binaryReversal(int num)
    {
        long reversal = 0;
        long binary = intToBinary(num);
        int len = (binary + "").length();

        for(int i = 0; i < len; i++)
        {
            int decimal = (int)(binary % 10);
            reversal += takePower(10,len - i - 1) * decimal;
            binary = binary / 10;
        }
        return reversal;
    }

    /**
     * Find the biggest number
     * @param x first number
     * @param y second number
     * @return max number
     */
    public static int findMax(int x, int y)
    {
        int max = x;
        if( y > max)
        {
            max = y;
        }
        return max;
    }

    /**
     * Calculate number of power
     * @param number given number
     * @param power power of number
     * @return number of power
     */
    public static int takePower(int number, int power)
    {
        int result = 1;
        for(int i = 0; i < power; i++)
        {
            result *= number;
        }
        return result;
    }
}