/**
 * This is a main class which tests method implementation.
 * 
 * @author ECE SESEN
 * @version 24.04.2024
 */

public class Lab_5_Tester 
{
    public static void main(String [] args)
    {
        System.out.println("\nQueston 1: \n");
        Lab_5 a1 = new Lab_5(4, 5);
        a1.fillChar('x', 2, 4, 1, 4);
        a1.print();

        System.out.println("\nQuestion 2: \n");
        a1.fillChar('y', 1, 3, 0, 5);
        a1.print();

        System.out.println("\nQuestion 3: \n");
        Lab_5 a2 = new Lab_5(10, 10);
        a2.pattern1();
        a2.print();
        

        System.out.println("\nQuestion 4: \n");
        Lab_5 a3 = new Lab_5(7, 19);
        a3.pattern1();
        a3.print();

        System.out.println("\nQuestion 5: \n");
        Lab_5 a4 = new Lab_5(4, 3);
        a4.pattern1();
        a4.print();

        System.out.println("\nQuestion 6: \n");
        Lab_5 a5 = new Lab_5(3, 13);
        a5.pattern2(5, 0);
        a5.print();

        System.out.println("\nQuestion 7: \n");
        a5.pattern2(5, 2);
        a5.print();

        System.out.println("\nQuestion 8: \n");
        Lab_5 a6 = new Lab_5(7, 13);
        a6.pattern2(5, 4);
        a6.print();
        
        System.out.println("\nQuestion 9: \n");
        Lab_5 a7 = new Lab_5(4, 19);
        a7.pattern2(9, 4);
        a7.print();

        System.out.println("\nQuestion 10: \n");
        Lab_5 a8 = new Lab_5(5, 5);
        a8.pattern3();
        a8.print();
        
        System.out.println("Found: " + a8.findMaxRowSum());
        System.out.println("Expected = 16");

        System.out.println("\nQuestion 11: \n");
        Lab_5 a9 = new Lab_5(9, 35);
        a9.pattern3();
        a9.print();
    }
}
