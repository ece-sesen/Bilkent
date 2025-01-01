/**
 * This is the same with the third lab question.
 * It uses useDelimiter() nethed and its implemetation.
 */
import java.util.Scanner;
public class useDelimineter 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter: ");
        // Change delimiter
        in.useDelimiter("/|,|-|:");

        // Read the contents of the scanner
        String year = in.next().trim();
        String month = in.next().trim();
        String date = in.next().trim();
        String day = in.next().trim();
        String hour = in.next().trim();
        String minute = in.nextLine().trim();
        in.close();

        //Print the result
        System.out.println("Date: " + date);
        System.out.println("Day:  " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        System.out.println("Time: " + minute + " minutes after " + hour);
    }
}
