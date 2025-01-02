import java.io.FileNotFoundException;

/**
 * a console application for a student grading system.
 * By reading text file, program calculates student's exam points considering weight and find average of them.
 * 
 * @author ECE SESEN
 * @version 08.05.2024
 * 
 */

public class Main 
{
    public static void main(String [] args) throws FileNotFoundException
    {
        School bilkent = new School();
        bilkent.processTextFile("/Users/ece/Desktop/input.txt");
    }
}
