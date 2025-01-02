/**
 * This is School class
 * School has students, students have grades
 * 
 * @author ECE SESEN
 * @version 08.05.2024
 * 
 */

import java.util.*;
import java.io.*;

public class School
{
    //Instance variables
    private Student[] students; //keep the students in ascending order by their School ID
    private int currentStudents;

    //Constructor
    public School()
    {
        students = new Student[10]; //creates a Student array of size 10
        currentStudents = 0;
    }

    /**
     * whenever the number of students in the school exceeds half of the current length of the students array, 
     * double the array size and copy the existing students to the new array. 
     * For example, the array size is initially 10, and we can add five students to the school. 
     * Then, when we try to add one more student, the array size should become 20
     */
    public void copyStudent()
    {
        if(currentStudents > students.length / 2)
        {
            students = Arrays.copyOf(students, students.length * 2);
        }
    }

    /**
     * create and add a new student to the students array. 
     * School IDs are required to be unique;
     * therefore, this method should first check if the given schoolID already belongs to an existing student
     * If the given schoolID is not already given to a different student, 
     * this method should create a new student object and 
     * add it to the correct position in the array, keeping the students in ascending order by Student ID.
     * @param schoolID
     * @param name
     * @param surname
     * @param age
     */
    public void addStudent(String schoolID, String name, String surname, int age)
    {
        try
        {
            if(!schoolIDExist(schoolID))
            {
                Student s = new Student(schoolID, name, surname, age);
                currentStudents++;
                if(shouldCopy())
                {
                    copyStudent();
                }
                int pos = findPosition(s);
                placeStudent(s, pos);
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException Exception)
        {
            System.out.println("Duplicate ID: " + schoolID + "\n");
        }   
    }

    /**
     * Compare array size with number of current students. 
     * @return true if more than half of the array is filled otherwise return false.
     */
    public boolean shouldCopy()
    {
        return currentStudents + 1 > students.length / 2;
    }

    /**
     * Place the student in correct place according to student's ID (increasing order)
     * @param student student object
     * @return index of the student
     */
    public int findPosition(Student student)
    {
        int index = 0;
        boolean found = false;
        if(currentStudents > 1)
        {
            for(int i = 0; i < currentStudents - 1 && !found; i++)
            {
                if(students[i].getID().compareTo(student.getID()) < 0)
                {
                    index++;
                }
                else
                {
                    found = true;
                }
            }
        }
        return index;
    }

    /**
     * We need to shift the students after index 2 by 1 to the right
     * @param s new  added student
     * @param index desired index to put student
     */
    public void placeStudent(Student s, int index)
    {
        if(currentStudents > 1)
        {
            for(int i = currentStudents - 1; i > index; i-- )
            {
                students[i] = students[i - 1];
            }
            
        }
        students[index] = s;
    }

    /**
     * Check whether given ID has already used
     * @param ID student ID number
     * @return true if this ID has already taken, otherwise returb false
     */
    public boolean schoolIDExist(String ID)
    {
        boolean isThere = false;
        boolean found = false;
        if(currentStudents != 0)
        {
            for(int i = 0; i < currentStudents && !found; i++)
            {
                if(students[i].getID().equals(ID))
                {
                    isThere = true;
                    found = true;
                }
            }
        }
        return isThere;
    }

    /**
     * This method should use binary search to find the desired student
     * if there is no student with the given schoolID, 
     * you should trigger an exception with the message: “No such student with the id ” + schoolID + “!”.
     * @param schoolID desired ID
     * @return the Student object that has the given Student ID
     */
    public Student getStudent(String schoolID)
    {
        int index = binarySearch(schoolID);
        Student s = null;
        
        try
        {
            if(index == -1)
            {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException exception)
        {
            System.out.println("No such student with the id " + schoolID + "!\n");
        }
        if(index >= 0)
        {
            s = students[index];
        }
        return s;
    }

    /**
     * Binary search for school ID
     * @param ID searching ID
     * @return student with given ID
     */
    public int binarySearch(String ID)
    {
        boolean found = false;
        int pos = - 1;
        int low = 0;
        int high = currentStudents - 1;
        while(low <= high && !found)
        {
            int mid = (low + high) / 2;
            if(students[mid].getID().compareTo(ID) == 0)
            {
                pos = mid;
                found = true;
            }
            else if(students[mid].getID().compareTo(ID) < 0)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
        return pos;
    }

    /**
     * You should use Quick Sort to sort the students in this new array
     * You should not change the order of the students in the original students array; 
     * If two students' names are identical, you should compare their surnames.
     * You may utilize the code from the course slides to implement the sort algorithm
     * @return this method should return a new array
     */
    public Student[] getStudentsByNameOrder()
    {
        Student[] nameOrderedStudents = Arrays.copyOf(students, currentStudents);
        quickSort(nameOrderedStudents, 0, currentStudents - 1);
        return nameOrderedStudents;
    }

    /**
     * to print the students' information in name order
     * This method should use toString method of the Student class to print each student’s information.
     */
    public void printStudentsByNameOrder()
    {
        Student[] print = getStudentsByNameOrder();
        for(int i = 0; i < currentStudents; i++)
        {
            System.out.println(print[i].toString());
        }
    }

    /**
     * Part of the quick sort which implements recursively.
     * @param arr gonna be sorted array
     * @param low initial index
     * @param high last index
     * @return next element
     */
    public int quickSortPart(Student[] arr, int low, int high)
    {
        Student pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) 
        {
            if (arr[j].getStudentName().compareTo(pivot.getStudentName()) < 0) // If current element is smaller than the pivot
            {
                i++; // Increment index of smaller element
                swapStudents(i, j, arr);
            }

            else if(arr[j].getStudentName().compareTo(pivot.getStudentName()) == 0)
            {
                if (arr[j].getStudentSurname().compareTo(pivot.getStudentSurname()) < 0) 
                {
                    i++; // Increment index of smaller element
                    swapStudents(i, j, arr);
                }
            }
        }
        swapStudents( i + 1, high, arr);
        return (i + 1);
    }

    /**
     * Quick sort (pivot: put smaller elements left to the pivot, other left). Repeat it until no more division is possible
     * @param arr gonna be sorted array
     * @param low initial index
     * @param high last index
     */
    public void quickSort(Student[] arr, int low, int high)
    {
        if (low < high) 
        {
            int pi = quickSortPart(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * prints all the students as they exist in the original students array, which is ordered by their School IDs.
     */
    public void printStudents()
    {
        for(int i = 0; i < currentStudents; i++)
        {
            System.out.println(students[i].toString());    
        }
    }

    /**
     * to calculate the average grade of a given student as (∑ (weight * points)) / ∑ (weight). 
     * @param s student object
     * @return average of all grades
     */
    public float getGradeAverage(Student s)
    {
        float num = 0;
        float denom = 0;
        if(s.getGrades().length == 0)
        {
            return 0;
        }
        else
        {
            for(int i = 0; i < s.getGrades().length; i++)
            {
                num += s.getGrades()[i].getWeight() * s.getGrades()[i].getPoints();
                denom += s.getGrades()[i].getWeight();
            }
            return num / denom;
        }
    }

    /**
     * print each student’s information (using its toString method) and grade average together, ordered by the average grade
     * The students with the same grade average will be ordered by their Student IDs.
     * This method would require you to calculate the average grade of each student and sort them in descending order
     * You can use any sorting algorithm you want
     * Exclude the students who have no grade
     */
    public void printStudentGradeAverages()
    {
        Student[] sortedAverage = Arrays.copyOf(students, students.length);
        bubbleSort(sortedAverage);
        for(int i = 0; i < currentStudents; i++)
        {
            if(getGradeAverage(sortedAverage[i]) != 0)
            {
                System.out.println(sortedAverage[i].toString() + " - Average: " + getGradeAverage(sortedAverage[i]));
            }  
        }
    }

    /**
     * Bubble sort for average grades (decreasing order)
     * @param s student array
     */
    public void bubbleSort(Student[] s) 
    {
        for(int i = 0; i < currentStudents; i++)
        {
            for(int j = 0; j < currentStudents - i - 1; j++)
            {
                if(getGradeAverage(s[j]) < getGradeAverage(s[j+1]))
                {
                    swapStudents(j, j + 1, s);
                }
                else if (getGradeAverage(s[j]) == getGradeAverage(s[j+1]))
                {
                    if(s[j+1].getID().compareTo(s[j].getID()) < 0)
                    {
                        swapStudents(j, j + 1, s);
                    }
                }
            }
        }
    }

    /**
     * Swap elements whose indexes are given
     * @param index1 first element
     * @param index2 second element
     * @param arr whole elements located in the same array
     */
    public void swapStudents(int index1, int index2, Student[] arr)
    {
        Student temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * This method should first find the student using the getStudent method 
     * to print the student’s information and all the grades this student has.
     * @param schoolID
     */
    public void printGradesOf(String schoolID)
    {
        Student print = getStudent(schoolID);
        System.out.println("Student: " + print.toString() + "\nGrades:");
        for(int i = 0; i < print.getTotalExam(); i++)
        {
            System.out.println(print.getGrades()[i].getExamName() + " (Weight: " + print.getGrades()[i].getWeight() + ") " +  print.getGrades()[i].getPoints() );
        }
    }

    /**
     * You will read this text file line by line and call the corresponding methods based the line’s content.
     * @param filename input.txt(given resource)
     */
    public void processTextFile(String filename) throws FileNotFoundException
    {
        File inputFile = new File(filename);
        if(!inputFile.exists())
        {
           throw new FileNotFoundException("File not found " + inputFile + "\n");
        }

        try(Scanner in = new Scanner(inputFile))
        {
            while(in.hasNextLine())
            {
                String line = in.nextLine();
                singleLine(line);
            }
        }
    }

    /**
     * Determines the implementation according the given sentence containing some datas
     * @param line sentence
     */
    public void singleLine( String line)
    {
        int twoDotIndex = -1;
        String title = "";
        if(line.contains(":"))
        {
            twoDotIndex = findTwoDot(line);
            title = line.substring(0, twoDotIndex);
        }
        else
        {
            title = line;
        }
        
        String[] words = separateWords(line);

        if(title.equals("Student"))
        {
            int length = words.length;
            int between = lastEmptyIndex(words[0]);
            String name = words[0].substring(0,between);
            String surname = words[0].substring(between + 1);

            addStudent(words[length - 1], name, surname, Integer.parseInt(words[length - 2]));
        }
        else if(title.equals("Grade"))
        {
            Student chosen = getStudent(words[0]);
            if(chosen != null)
            {
                chosen.setGrade(words[1], Float.parseFloat(words[2]), Float.parseFloat(words[3]));
            }
            
        }
        else if(title.equals("GradesOf"))
        {
            Student chosen = getStudent(words[0]);
            if(chosen != null)
            {
                printGradesOf(words[0]);
                System.out.println();
            }
        }
        else if(title.equals("PrintStudents"))
        {
            printStudents();
            System.out.println();
        }
        else if(title.equals("PrintByGradeAverages"))
        {
            printStudentGradeAverages();
            System.out.println();
        }
        else if(title.equals("PrintByNameOrder"))
        {
            printStudentsByNameOrder();
            System.out.println();
        }
    }

    /**
     * Finds the last white space that sentence has
     * @param s sentence
     * @return last index of the white space
     */
    public int lastEmptyIndex(String s)
    {
        int index = -1;
        boolean found = false;
        for(int i = s.length() - 1; i >= 0 && !found; i--)
        {
            char ch = s.charAt(i);
            if(Character.isWhitespace(ch))
            {
                found = true;
                index = i;
            } 
        }
        return index;
    }

    /**
     * Split the sentence regarding comma 
     * @param sentence
     * @return String array contains words separeted by comma
     */
    public String[] separateWords(String s)
    {
        int twoDot = findTwoDot(s);
        
        if(twoDot == -1)
        {
            String[] words = new String[1];
            words[0] = s;
            return words;
        }
        else
        {
            String data = s.substring(twoDot + 1);
            String[] words = data.split(",");
            trimWords(words);
            return words;
        }
    }

    /**
     * Eliminate the initial and the last parts of the sentence conatining white spaces
     * @param arr word array
     * @return updated array
     */
    public String[] trimWords(String[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            String s = arr[i];
            arr[i] = s.substring(first(s), last(s) + 1);
        }
        return arr;
    }

    /**
     * Determine the fisrt index which comes after the white space
     * @param s word
     * @return index of first letter
     */
    public int first(String s)
    {
        int index = -1;
        boolean found = false;
        for(int i = 0; i < s.length() && !found; i++)
        {
            char ch = s.charAt(i);
            if(!Character.isWhitespace(ch))
            {
                found = true;
                index = i;
            }
        }
        return index;
    }

    /**
     * Determine the last index which comes before the white space
     * @param s word
     * @return index of last letter
     */
    public int last(String s)
    {
        int index = -1;
        boolean found = false;
        for(int i = s.length() - 1; i >= 0  && !found; i--)
        {
            char ch = s.charAt(i);
            if(!Character.isWhitespace(ch))
            {
                found = true;
                index = i;
            }
        }
        return index;
    }

    /**
     * Determine the index where two dots located
     * @param s sentence
     * @return index of two dot
     */
    public int findTwoDot(String s)
    {
        int index = -1;
        boolean found = false;
        for(int i = 0; i < s.length() && !found; i++)
        {
            char ch = s.charAt(i);
            if(ch == ':')
            {
                found = true;
                index = i;
            }
        }
        return index;
    }

    /**
     * Get all students stored in array
     * @return students array
     */
    public Student[] getStudents()
    {
        return students;
    }
}
