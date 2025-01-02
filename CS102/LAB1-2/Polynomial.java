/**
 * This is a Polynomial class
 * It consistes variables that each object has its own.
 * This class has several methods such as finding degree or coefficient in specifit degree, evaluate the result in given point...
 * 
 * @author ECE SESEN
 * @version 27.02.2024
 */
public class Polynomial 
{
    //Instance variables
    private double[] coefficients = new double[100];
    private String polynomial = "P(x) = ";

    //Constructors
    public Polynomial(int d, double c)
    {
        this.coefficients[d] = c;
    }
    public Polynomial()
    {
        this(0,0);
    }
    public Polynomial(double[] input)
    {
        for(int i = 0; i < input.length; i++)
        {
            this.coefficients[i] = input[i];
        }
    }

    /**
     * Returns the coefficient of the term with that degree
     * @param degree specific term's degree
     * @return given degree's coefficient
     */
    public double getCoefficient(int degree)
    {
        return this.coefficients[degree];
    }

    /**
     * Finds the greatest degree which corresponds with polynomial degree
     * @return the polynomial degree
     */
    public int getDegree()
    {
        int degree = 0;
        boolean found = false;
        for(int i = this.coefficients.length - 1; !found && i >= 0; i--)
        {
            if(this.coefficients[i] != 0)
            {
                degree = i;
                found = true;
            }
        }
        return degree;
    }

    /**
     * Returns the String representation of the polynomial
     */
    public String toString()
    {
        if(this.coefficients[getMinDegree()] < 0)
        {
            this.polynomial += addSign(getMinDegree());
        }
        this.polynomial += getTerm(getMinDegree());

        for(int i = getMinDegree() + 1; i <= getDegree(); i++)
        {
            if(this.coefficients[i] != 0)
            {
                this.polynomial += addSign(i) + getTerm(i);
            }
        }
        return this.polynomial;
    }

    /**
     * Evaluates the polynomial
     * @param x given value
     * @return result
     */
    public double eval(double x)
    {
        double result = 0;
        int degree = getDegree();
        for(int i = 0; i <= degree; i++ )
        {
            result += this.coefficients[i] * Math.pow(x,i);
        }
        return result;
    }
    
    /**
     * Evaluates the polynomial using Horner’s method
     * @param x is given valur
     * @return result of calculation 
     */
    public double eval2(double x)
    {
        double result = 0;
        int degree = getDegree();
        for(int i = degree; i > 0; i--)
        {
            result = (this.coefficients[i] + result) * x;
        }
        result += this.coefficients[0];
        return result;
    }

    /**
     * Gives the degree of first term
     * @return min degree
     */
    public int getMinDegree()
    {
        boolean found = false;
        int minDegree = 0;
        for(int i = 0; !found & i < this.coefficients.length; i++)
        {
            if(this.coefficients[i] != 0)
            {
                found = true;
                minDegree = i;
            }
        }
        return minDegree;
    }

    /**
     * Gives the term without its sign but with its degree and "x"
     * @param i degree of term
     * @return term
     */
    public String getTerm(int i)
    {
        String result = "";
        result += Math.abs(this.coefficients[i]);
        if( i == 1 )
        {
            result += "x";
        }
        else if ( i > 1)
        {
            result += "x^" + i;
        }
        return result;
    }

    /**
     * Get the sign of the term with given degree
     * @param i degree
     * @return "-" if negative, otherwise return "+"
     */
    public String addSign(int i)
    {
        String sign = "";
        if(this.coefficients[i] < 0)
        {
            sign = " - ";
        }
        else if(this.coefficients[i] > 0)
        {
            sign = " + ";
        }
        return sign;
    }

    /**
     * Gives all coefficients of polynomial as an array
     * @return array
     */
    public double[] coefArray()
    {
        return this.coefficients;
    }

    /**
     * Sums the two polynomials and give the final polynomial
     * @param p2 second polynomial
     * @return addition result
     */
    public Polynomial add(Polynomial p2)
    {
        Polynomial result = new Polynomial();
        for(int i = Math.min(this.getMinDegree(), p2.getMinDegree()); i <= Math.max(this.getDegree(), p2.getDegree()); i++)
        {
            result.coefArray()[i] = this.getCoefficient(i) + p2.getCoefficient(i);
        }
        return result;
    }

    /**
     * Substract the polynomial from given one. Give the final polynomail
     * @param p2 second polynomial
     * @return subtraction result
     */
    public Polynomial sub(Polynomial p2)
    {
        Polynomial result = new Polynomial();
        for(int i = Math.min(this.getMinDegree(), p2.getMinDegree()); i <= Math.max(this.getDegree(), p2.getDegree()); i++)
        {
            result.coefArray()[i] = this.getCoefficient(i) - p2.getCoefficient(i);
        }
        return result;
    }

    /**
     * Multiply polynomial with given one and give final polynomial
     * @param p2 second polynomial
     * @return multiplication result
     */
    public Polynomial mul(Polynomial p2)
    {
        Polynomial result = new Polynomial();
        for(int i = this.getMinDegree(); i <= this.getDegree(); i++)
        {
            for(int j = p2.getMinDegree(); j <= p2.getDegree(); j++)
            {
                result.coefArray()[i + j] += this.getCoefficient(i) * p2.getCoefficient(j);
            }
        }
        return result;
    }

    /**
     * Finds the common x values in the range [1, 200] that make current polynomial equal to given polynomial 
     * @param p2 given polynomial
     * @return array of all possible values
     */
    public int[] findEqual(Polynomial p2)
    {
        int counter = 0;
        int [] result = new int[200];
        for(int i = 1; i <= 200; i++)
        {
            if(this.eval(i) == p2.eval(i))
            {
                result[counter] = i;
                counter++;
            }
        }
        return result;
    }
    
    /**
     * Gives a list ol values separated by comma
     * @param arr all possible values
     * @return list of the values
     */
    public String equalValues (int[] arr)
    {
        String result = "";
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != 0)
            {
                result += arr[i] + ", ";
            }
        }
        return result;
    }

    /**
     * Find composition of the current polynomial with given polynomial.
     * @param p2 given polynomial
     * @return polynomial
     */
    public Polynomial compose (Polynomial p2)
    {
        Polynomial result = new Polynomial();
        for(int i = this.getMinDegree(); i <= this.getDegree(); i++ )
        {
            Polynomial step = new Polynomial(0, this.getCoefficient(i));
            for(int j = 0; j < i; j++)
            {
                step = step.mul(p2);
            }
            result = result.add(step);
        }
        return result;
    }

    /**
     * Divide single term with given single term
     * @param p2 given polynomial
     * @return polynomial
     */
    public Polynomial divTerm(Polynomial p2)
    {
        int c1 = (int)(this.getCoefficient(this.getDegree()));
        int c2 = (int)(p2.getCoefficient(p2.getDegree()));
        int coef = c1 / c2;
        int degree = this.getDegree() - p2.getDegree();
        Polynomial result = new Polynomial( degree, coef);
        return result;
    }

    /**
     * Divide whole polynomial with given polynomial, ignore remainder
     * @param p2 given polynomial
     * @return quotient
     */
    public Polynomial div(Polynomial p2)
    {
        Polynomial a = this;
        Polynomial result = new Polynomial();
        Polynomial divider = new Polynomial(p2.getDegree(), p2.getCoefficient(p2.getDegree()));
        Polynomial step = new Polynomial();
        for(int i = a.getDegree(); i >= a.getMinDegree(); i--)
        {
            Polynomial dividend = new Polynomial(i, a.getCoefficient(i));
            if(i >= divider.getDegree())
            {
                step = dividend.divTerm(divider);
                a = this.sub(step.mul(p2));
                result = result.add(step);
            }
        }
        return result;
    }
}
