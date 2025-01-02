/**
 * This is a polynomial tester class.
 * Using methods defined is Polynomial class, it prints the results.
 * 
 * @author ECE SESEN
 * @version 27.02.2024
 * 
 */
import java.util.Arrays;
public class PolynomialTester 
{
    public static void main(String[] args)
    {
        //Constructor with 2 parameters is called
        Polynomial p1 = new Polynomial(0,3);
        System.out.println(p1);
        double coEf1 = p1.getCoefficient(2);
        double coEf1_2 = p1.getCoefficient(5);
        int deg1 = p1.getDegree();
        double r1 = p1.eval(2);
        double r1_horner =p1.eval2(2);
        System.out.println("Coefficient of degree 2: " + coEf1 +
                            "\nCoefficient of degree 5: " + coEf1_2 +
                            "\nDegree of polynomial: " + deg1 +
                            "\nResult when x = 2: " + r1 + " " + r1_horner );
        System.out.println();

        //Constructor with no parameters is called
        Polynomial p2 = new Polynomial();
        System.out.println(p2);
        double coEf2 = p2.getCoefficient(2);
        int deg2 = p2.getDegree();
        double r2 = p2.eval(2);
        double r2_horner = p2.eval2(2);
        System.out.println("Coefficient of degree 2: " + coEf2 +
                            "\nDegree of polynomial: " + deg2 +
                            "\nResult when x = 2: " + r2 + " " + r2_horner );
        System.out.println();

        //Constructor with array parameter is called
        double[] input = {-1, 2, -3, 0, 5};
        Polynomial p3 = new Polynomial(input);
        System.out.println(p3);
        double coEf3 = p3.getCoefficient(10);
        double coEf3_2 = p3.getCoefficient(2);
        int deg3 = p3.getDegree();
        double r3 = p3.eval(1);
        double r3_horner =p3.eval2(1);
        System.out.println("Coefficient of degree 10: " + coEf3 +
                            "\nCoefficient of degree 2: " + coEf3_2 +
                            "\nDegree of polynomial: " + deg3 +
                            "\nResult when x = 1: " + r3 + " " + r3_horner );  
        System.out.println();

        //Add-subtract-multiply polynomials
        double[] in = {3, 4, 5, 2};
        Polynomial p4 = new Polynomial(in);
        double[] in2 = {2, 4, 1};
        Polynomial p5 = new Polynomial(in2);
        System.out.println("Addition: " + p4.add(p5));
        System.out.println("Subtraction: " + p4.sub(p5) );
        System.out.println("Multiplication: " + p4.mul(p5));
        System.out.println();

        //Solve two polynomials together and find common values
        double[] a1 = {10};
        Polynomial a = new Polynomial(a1);
        double[] b1 = {2, 1};
        Polynomial b = new Polynomial(b1);
        System.out.println(a);
        System.out.println(b);
        int[] result = a.findEqual(b);
        System.out.println("Possible results: " + a.equalValues(result));
        System.out.println();

        //Compose polynomials
        double[] c = {3, 4, 1};
        Polynomial c1 = new Polynomial(c);
        double[] d = {2, 1};
        Polynomial d1 = new Polynomial(d);
        System.out.println("Compose: " + c1.compose(d1)); 
        System.out.println();

        //Divide polynomials
        double[] e = {3, 4, 1, 3, 0, 2};
        Polynomial e1 = new Polynomial(e);
        double[] f = {2, 1};
        Polynomial f1 = new Polynomial(f);
        System.out.println("Division: " + e1.div(f1));
    }
}
