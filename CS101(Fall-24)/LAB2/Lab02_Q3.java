/**
 * Thi s class takes a date including day, month, year, date and hour as a input
 * Print it by seperating each information
 * 
 * @author Ece ŞEŞEN
 * @version 01.10.2024
 */
import java.util.Scanner;
public class Lab02_Q3 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter date and time: ");
        String dateTime = in.nextLine();

        //Find the indexes of seperators
        int firstSlash = dateTime. indexOf("/");
        int secondSlash = dateTime.indexOf("/", firstSlash + 1);
        int comma = dateTime.indexOf(",");
        int line = dateTime.indexOf("-");
        int column = dateTime.indexOf(":");

        //Seperate inputs properly
        String year = dateTime.substring(0, firstSlash).trim();
        String month = dateTime.substring(firstSlash + 1, secondSlash).trim();
        String date = dateTime.substring(secondSlash + 1, comma).trim();
        String day = dateTime.substring(comma + 1, line).trim();
        String hour = dateTime.substring(line + 1, column).trim();
        String minute = dateTime.substring(column + 1).trim();

        //Print the result
        System.out.println("Date: " + date);
        System.out.println("Day:  " + day);
        System.out.println("Month:" + month);
        System.out.println("Year: " + year);
        System.out.println("Time: " + minute + " minutes after " + hour);
    }
}
