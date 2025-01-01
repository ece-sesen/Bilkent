/**
 * This class takes weight as an input and evaluate its value in different planets.
 * Print the result in a proper manner with a table.
 * 
 * @author Ece ŞEŞEN
 * @version 09.10.2024
 */

 import java.util.Scanner;
 public class Lab02_Q2R
 {
     public static void main(String[] args)
     {
         Scanner in = new Scanner(System.in);
 
         //Earth's gravity in different planets
         final double MERCURY = 0.38;
         final double VENUS = 0.91;
         final double MARS = 0.38;
         final double JUPITER = 2.34;
         final double PLUTO = 0.06;
 
         //Take two input refer to people's weight 
         System.out.print("Enter weight of first earthling(kg) on Pluto: ");
         double weight1 = in.nextDouble();
         System.out.print("Enter weight of second earthling(kg) on Pluto: ");
         double weight2 = in.nextDouble();
         in.close();
 
         //Translate weight to the Earth
         double w1Earth = weight1 / PLUTO;
         double w2Earth = weight2 / PLUTO;


         //Calculate weights.
         double w1Mer = w1Earth * MERCURY;
         double w1Ven = w1Earth * VENUS;
         double w1Mar = w1Earth * MARS;
         double w1Jup = w1Earth * JUPITER;
 
         double w2Mer = w2Earth * MERCURY;
         double w2Ven = w2Earth * VENUS;
         double w2Mar = w2Earth * MARS;
         double w2Jup = w2Earth * JUPITER;
 
         // Print the result table
         System.out.printf("%42s%10s%10s%10s%n", "MERCURY", "VENUS", "MARS", "JUPITER");
         System.out.printf("%23s%.1f%s%12.1f%10.1f%10.1f%10.1f%n", "EARTHLING ONE( ", weight1, "kg)", w1Mer, w1Ven, w1Mar, w1Jup  );
         System.out.printf("%22s%.1f%s%12.1f%10.1f%10.1f%10.1f%n", "EARTHLING TWO(", weight2, "kg)", w2Mer, w2Ven, w2Mar, w2Jup  );
     }
 }
 