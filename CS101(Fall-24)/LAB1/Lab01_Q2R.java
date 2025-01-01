/**
 * This is class calculates some mathematical expressions with revision.
 * 
 * @author Ece SESEN
 * @version 01.10.2024
 */

public class Lab01_Q2R 
{
    public static void main(String[] args)
    {
        //First expression is calculated
        double result1 = ((4.3 + 22) * (5.1 - 7.7)) / (32.2 / 17 - 22);
        System.out.println("Expression 1 evaluates to: " + result1); 

        //Second expression is calculated
        double result2 = (Math.pow(2,3) * (18 - 3.5 * 4.66)) / (Math.pow(2,4) - 34);
        System.out.println("Expression 2 evaluates to: " +result2);

        //Third expression is calculated
        double result3 = 3 * (Math.pow(24 - 0.222, -1/6)); 
        System.out.println("Expression 3 evaluates to: " + result3);

        //Revision
        double result4 = Math.pow(1.1, 1.1) * 36.89 / (17.1 - 3.6);
        System.out.println("Expression 4 evaluates to: " + result4);
    }
}
