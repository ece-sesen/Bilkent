/**
 * This class take volume of the circle as input.
 * Calculate its surface area and print it.
 * 
 * @author Ece ŞEŞEN
 * @version 01.10.2024
 */
import java.util.Scanner;
public class Lab02_Q1
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        //Take volume of the circle from user
        System.out.print("Enter volume of sphere: ");
        double volume = in.nextDouble();
        in.close();

        //Find the radius from the volume
        double rCube = (3 * volume) / (4 * Math.PI);
        double radius = Math.pow(rCube, 1/3.0);

        //Calculate surface area of the circle
        double asurfaceArea = 4 * Math.PI * Math.pow(radius, 2);

        //Print the result
        System.out.printf("%s%16.1f%n", "The radius of the sphere is: ", radius);
        System.out.printf("%s%10.1f%n", "The surface area of the sphere is: ", asurfaceArea);
    }
}