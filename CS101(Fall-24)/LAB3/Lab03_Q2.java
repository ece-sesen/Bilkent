/**
 * This program determines whether the student is eligible to take scholorship or not.
 * Takes unput from user and callculate score.
 * Print the result with its reasons is the student is not eligible for scholorship.
 * 
 * @author Ece ŞEŞEN
 * @version 09.10.2024
 */

import java.util.Scanner;
public class Lab03_Q2 
{
    public static void main(String[] args)
    {
        //Constants
        final int BASE_SCORE = 200;

        final int POINT_EACH_CREDIT = 15;
        final int POINT_EACH_EXTRA_ACTIVITY = 10;
        final int POINT_EACH_HONOR = 20;
        final int PER_INCOME_CUT = 5000;
        final int POINT_INCOME_CUT = 5;

        final int MAX_PT_CREDIT = 600;
        final int MAX_PT_EXTRA = 300;
        final int MAX_PT_HONOR = 100;

        //Take inputs
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the student's GPA: ");
        double gpa = in.nextDouble();
        System.out.print("Enter the total number of completed credit hours: ");
        int completedCredits = in.nextInt();
        System.out.print("Enter the extracurricular activity hours: ");
        int extraHours = in.nextInt();
        System.out.print("Enter the number of academic honors received: ");
        int academicHonors = in.nextInt();
        System.out.print("Enter the total annual family income: ");
        double familyIncome = in.nextDouble();
        in.close();

        //Calculate and print scholorship eligibilty score 
        double score = BASE_SCORE;
        int ptOfCredits = POINT_EACH_CREDIT * completedCredits;
        if(ptOfCredits > MAX_PT_CREDIT)
        {
            score += MAX_PT_CREDIT;
        }
        else
        {
            score += ptOfCredits;
        }

        int ptOfExtras = POINT_EACH_EXTRA_ACTIVITY * extraHours;
        if(ptOfExtras > MAX_PT_EXTRA)
        {
            score += MAX_PT_EXTRA;
        }
        else
        {
            score += ptOfExtras;
        }

        int ptOfHonors = POINT_EACH_HONOR * academicHonors;
        if(ptOfHonors > MAX_PT_HONOR)
        {
            score += MAX_PT_HONOR;
        }
        else
        {
            score += ptOfHonors;
        }

        double ptOfIncome = familyIncome / PER_INCOME_CUT * POINT_INCOME_CUT;
        score = score - ptOfIncome;
        
        System.out.println("Total scholarship score: " + (int)score);

        //Determine eligibility for the scholorship
        String reasons = "";

        if(gpa < 3.5)
        {
            reasons += "GPA is below 3.5.\n";
        }
        if(completedCredits < 30)
        {
            reasons += "Fewer than 30 completed credit hours.\n";
        }
        if(extraHours < 20)
        {
            reasons += "Fewer than 20 extracurricular activity hours.\n";
        }
        if(familyIncome > 75000)
        {
            reasons += "Family income exceeds $75000.\n";
        }
        if(score < 900)
        {
            reasons += "Total scholarship score is below 900 points."; 
        }

        //Print result
        {
            if(!reasons.equals(""))
            {
                System.out.println("The student is not eligible for the scholarship. Reason:\n" + reasons);
            }
            else
            {
                System.out.println("The student is eligible for the scholarship.");
            }
        }
    }
}
