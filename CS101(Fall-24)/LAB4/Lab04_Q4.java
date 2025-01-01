/**
 * This programs find prime numbers up to givrn limit.
 * Also print all prime numbers as list and their summation.
 * 
 * @author Ece ŞEŞEN
 * @version 15.10.2024
 */

import java.util.Scanner;
public class Lab04_Q4
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        //Variables
        boolean done = false;
        String iscontinue = "yes";
        String primeNumbers = "";
        int sum = 0;
        int count = 0;
        
        while(!done)
        {
            //Determine if the program continues or not
            if(iscontinue.equals("no"))
            {
                done = true;
                System.out.println("Program finished.");
            }
            else if(iscontinue.equals("yes"))
            {
                System.out.print("Enter a positive number: ");
                int input = in.nextInt();
                int eachNum = 2;

                //Find whether number is prime or not
                while(eachNum <= input)
                {
                    boolean isPrime= true;
                    int divider = 2;

                    while(divider < eachNum && isPrime)
                    {
                        if(eachNum % divider == 0 )
                        {
                            isPrime = false;
                        }
                        divider++;
                    }

                    if(isPrime)
                    {
                        primeNumbers += eachNum + " ";
                        sum += eachNum;
                        count++;
                    }
                    eachNum++;
                }

                System.out.println("Prime numbers: " + primeNumbers + "\n" +
                                       "Sum of primes: " + sum + "\n" +
                                       "Count of primes: " + count + "\n");
                                
                //Clean the previous execution results
                primeNumbers = "";
                sum = 0;
                count = 0;

                System.out.print("Do you want to calculate again? (yes/no): ");
                iscontinue = in.next();
            }

            //If user enter sth different from yes/no, ask again
            else 
            {
                System.out.print("Invalid choice! Enter yes/no: ");
                iscontinue = in.next();
                
            }
        }
        in.close();
    }
}