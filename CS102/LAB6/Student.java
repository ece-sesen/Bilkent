/**
 * This is Student class
 * 
 * @author ECE SESEN
 * @version 08.05.2024
 * 
 */

import java.util.*;
public class Student 
{
    //Instance variables
    private String name;
    private String surname;
    private String schoolID;
    private int age;
    private Grade[] grades;
    private int totalExam;
    private int examIndex;

    //Constructor
    public Student(String schoolID, String name, String surname, int age)
    {
        grades = new Grade[0]; // initialize an empty grades array at first
        totalExam = 0; 
        this.schoolID = schoolID;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    /*
     * make sure that the grades array in the student class has no empty indexes
     * As we add new grades to that student, you should create a larger array
     * an array of size “grades.length +1”
     * copy the existing grades to the new array, and finally add the new grade to the last position.
     */
    public void addGrade(Grade newResult)
    {
        grades = Arrays.copyOf(grades, grades.length + 1);
        grades[grades.length - 1] = newResult;    
    }

    /**
     * print the student in the following form: “School ID, Name Surname, Age”
     */
    public String toString()
    {
        String result = schoolID + ", " + name + " " + surname + ", " + age;
        return result;
    }

    /**
     * Compare given exam name by existing ones and determine whether it is unique or not.
     * @param name exaö name
     * @return true if exam name is unique, otherwise return false
     */
    public boolean isExamExist(String name)
    {
        boolean isExist = false;
        examIndex = -1;
        for(int i= 0; i < grades.length; i ++)
        {
            if((grades[i].getExamName()).equals(name))
            {
                isExist = true;
                examIndex = i;

            }
        }
        return isExist;
    }

    /**
     * to add a new grade for the student
     * should first look if there is already a grade in this student’s grades array with the same exam name
     * If there is already an entry with the same exam name, this method updates that grade object with the given weight and points
     * If this exam name does not exist, you should create a new grade object and add it to this student’s grades array.
     * 
     * @param examName exam name
     * @param weight exam weight
     * @param points exam point
     */
    public void setGrade(String examName, float weight, float points) 
    {
        try
        {
            if(examName.length() > 3)
            {
                if(!isExamExist(examName))
                {
                    Grade newGrade = new Grade(examName, weight, points);
                    addGrade(newGrade);
                    totalExam++;
                }
                else
                {
                    int existExam = examIndex;
                    grades[existExam].updateWeight(weight);
                    grades[existExam].updatePoint(points);
                }
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println(examName + " must be longer than 3 characters!\n");
        }
    }

    /**
     * Get student ID as String
     * @return ID
     */
    public String getID()
    {
        return schoolID;
    }

    /**
     * Get all grades stored in array
     * @return grades array
     */
    public Grade[] getGrades()
    {
        return grades;
    }

    /**
     * Get number of all exams to determine the filled part of the grades array
     * @return number of all exams
     */
    public int getTotalExam()
    {
        return totalExam;
    }

    /**
     * Get student name
     * @return name
     */
    public String getStudentName()
    {
        return name;
    }

    /**
     * Get student surnmame
     * @return surname
     */
    public String getStudentSurname()
    {
        return surname;
    }
}
