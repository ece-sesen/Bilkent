/**
 * This class takes weight as an input and evaluate its value in different planets.
 * Print the result in a proper manner with a table.
 * 
 * @author Ece ŞEŞEN
 * @version 01.10.2024
 */

import java.util.Scanner;
public class Lab02_Q2 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        //Earth's gravity in different planets
        final double MERCURY = 0.38;
        final double VENUS = 0.91;
        final double MARS = 0.38;
        final double JUPITER = 2.34;

        //Take two input refer to people's weight 
        System.out.print("Enter weight of first earthling(kg): ");
        double weight1 = in.nextDouble();
        System.out.print("Enter weight of second earthling(kg): ");
        double weight2 = in.nextDouble();
        in.close();

        //Calculate weights.
        double w1Mer = weight1 * MERCURY;
        double w1Ven = weight1 * VENUS;
        double w1Mar = weight1 * MARS;
        double w1Jup = weight1 * JUPITER;

        double w2Mer = weight2 * MERCURY;
        double w2Ven = weight2 * VENUS;
        double w2Mar = weight2 * MARS;
        double w2Jup = weight2 * JUPITER;

        // Print the result table
        System.out.printf("%42s%10s%10s%10s%n", "MERCURY", "VENUS", "MARS", "JUPITER");
        System.out.printf("%23s%.1f%s%12.1f%10.1f%10.1f%10.1f%n", "EARTHLING ONE( ", weight1, "kg)", w1Mer, w1Ven, w1Mar, w1Jup  );
        System.out.printf("%22s%.1f%s%12.1f%10.1f%10.1f%10.1f%n", "EARTHLING TWO(", weight2, "kg)", w2Mer, w2Ven, w2Mar, w2Jup  );
    }
}
