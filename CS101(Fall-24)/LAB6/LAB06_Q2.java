/**
 * This class takes mail and password from user.
 * Ask continously while user enter invalid mail or password.
 * 
 * @author Ece ŞEŞEN
 * @version 07.11.2024
 */
import java.util.Scanner;
public class LAB06_Q2 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean isMailValid = false;
        while(!isMailValid)
        {
            System.out.print("Enter the email: ");
            String email = in.next();
            isMailValid = validateEmail(email);

            if(!isMailValid)
            {
                System.out.println("The email is not valid, please enter  a valid email:");
            }
            else
            {
                isMailValid = true;
            }
        }

        boolean isPasswordValid = false;
        while(!isPasswordValid)
        {
            System.out.print("Enter the Password: ");
            String password = in.next();
            isPasswordValid = validatePassword(password);

            if(!isPasswordValid)
            {
                System.out.println("The password is not valid, please enter another password");
            }
            else
            {
                System.out.println("The password is valid\n" + 
                                   "Registration is successful");
                isPasswordValid = true;
            }
        }
        in.close();
    }

    /**
     * If mail is valid print the information of student. 
     * (Name, surname, university)
     * @param mail student mail
     * @return true if mail is valid
     */
    public static boolean validateEmail(String mail)
    {
        String name = "";
        String surname = "";
        String university = "";

        int atSign = mail.indexOf("@");
        if(atSign == -1)
        {
            return false;
        }
        else
        {
            String beforeAt = mail.substring(0, atSign);
            String afterAt = mail.substring(atSign + 1);

            int secondDot = afterAt.indexOf(".");
            if(!afterAt.contains(".edu.tr"))
            {
                return false;
            }
            else
            {
                university = afterAt.substring(0, secondDot);
                int firstDot = beforeAt.indexOf(".");
                if(firstDot == -1)
                {
                    return false;
                }
                else
                {
                    if(beforeAt.substring(0, firstDot).equals("") || beforeAt.substring(firstDot, atSign).equals("") )
                    {
                        return false;
                    }
                    else
                    {
                        name = beforeAt.substring(0, firstDot);
                        surname = beforeAt.substring(firstDot + 1, atSign);
                    }
                }
            }
        }
        System.out.println("Student Name: " + makeCapitalLetter(name) +
                           "\nStudent Surname: " + makeCapitalLetter(surname) +
                           "\nUniversity Name: " + makeCapitalLetter(university));
        return true;
    }

    /**
     * Find the string located between given indexes
     * @param s string
     * @param startIndex start index
     * @param endIndex end index
     * @return string 
     */
    public static String findBetweenString(String s, int startIndex, int endIndex)
    {
        return s.substring(startIndex, endIndex);
    }

    /**
     * Make first letter of given string capital
     * @param s stirng
     * @return string with capital first letter
     */
    public static String makeCapitalLetter(String s)
    {
        String initial = s.charAt(0) + "";
        String capital = initial.toUpperCase();
        return capital + s.substring(1);
    }

    /**
     * Password needs to have at least 8 digits, 1 uppercase, 1 lowercase, 1 digit
     * @param password student's mail password
     * @return true if it has at least 8 digits
     */
    public static boolean validatePassword(String password)
    {
        boolean isValid = isDigitExist(password) && isLowercaseExist(password) && isUppercaseExist(password) && password.length() >= 8;
        return isValid;
    }

    /**
     * Determine string has at least one digit
     * @param s string
     * @return true if there is at least one digit
     */
    public static boolean isDigitExist(String s)
    {
        for(int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if( '0' <= ch && ch <= '9')
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine string has at least one uppercase letter
     * @param s string
     * @return true if there is at least one uppercase letter
     */
    public static boolean isUppercaseExist(String s)
    {
        for(int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if( 'A' <= ch && ch <= 'Z')
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine string has at least one lowercase letter
     * @param s string
     * @return true if there is at least one lowercase letter
     */
    public static boolean isLowercaseExist(String s)
    {
        for(int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if( 'a' <= ch && ch <= 'z')
            {
                return true;
            }
        }
        return false;
    }
}
