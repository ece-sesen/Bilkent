/**
 * This class take volume of the circle as input.
 * Calculate its surface area and circumference print all.
 * 
 * @author Ece ŞEŞEN
 * @version 09.10.2024
 */
import java.util.Scanner;
public class Lab02_Q1R
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

        //Calculate the circumference
        double circumference = 2 * Math.PI * radius;

        //Print the results
        System.out.printf("%s%16.1f%n", "The radius of the sphere is: ", radius);
        System.out.printf("%s%10.1f%n", "The surface area of the sphere is: ", asurfaceArea);
        System.out.printf("%s%9.1f", "The circumference of the sphere is: ", circumference);




    }
}