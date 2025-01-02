/**
 * This is a class that contains methods such as several patterns, sum calculation adn finding the max value among the rows
 * Array contains characters
 * 
 * @author ECE SESEN
 * @version 24.04.2024
 */

public class Lab_5
{
    //Instance variables
    private char[][] arr;
    private boolean startFill;
    private int current;
    private boolean startPrint;
    private int length;
    private int arrRow;
    private int arrColumn;
    private int[] middle;
    private int eachRow;
    private int maxRow;

    //Constructor
    public Lab_5(int rowNum, int columnNum)
    {
        startFill = true; //For fill method
        startPrint = true; //For print method
        arrRow = rowNum;
        arrColumn = columnNum;
        arr = cretateArray(rowNum, columnNum);
        middle = new int[4]; //rowStart, rowEnd, colStart, colEnd
    }

    /**
     * Initializes an empty 2D char array using the given sizes
     * you should keep a char[][] array class member and use it in your methods.
     * @param numRows number of rows
     * @param numColumns number of columns
     */
    public char[][] cretateArray(int numRows, int numColumns)
    {
        char[][] arr = new char[numRows][numColumns];
        return arr;
    }

    /**
     * Fills the desired portion of the 2D character array with the given character.
     * This method will start the recursion; you can call different helper methods within this method to recurse.
     * For example, a separate method can handle filling specific rows, and you may call it repeatedly to fill the desired portion.
     * @param c char used in filling
     * @param rowStart inclusive number of start
     * @param rowEnd exclusive number of end
     * @param columnStart inclusive number of start
     * @param columnEnd exclusive number of end
     */
    public void fillChar(char c,int rowStart,int rowEnd,int columnStart,int columnEnd)
    {
        if(startFill)
        {
            current = arr.length;
            startFill = false;
        }
        current--;
        if(current == 0)
        {
            fillRow(c, current, arr, rowStart, rowEnd, columnStart, columnEnd, arr[0].length);
        }
        else
        {
            fillRow(c, current, arr, rowStart, rowEnd, columnStart, columnEnd, arr[0].length - 1);
            fillChar(c, rowStart, rowEnd, columnStart, columnEnd);
        }
        startFill = true;
    }

    /**
     * @param c specific char to fill
     * @param rowIndex specific row
     * @param arr 2D char array
     * @param rowStart initial index of row
     * @param rowEnd last index of row
     * @param columnStart initial index of column
     * @param columnEnd last index of column
     * @param columnIndex recent index of column
     */
    public void fillRow(char c, int rowIndex, char[][] arr, int rowStart,int rowEnd, int columnStart, int columnEnd, int columnIndex)
    {
        if(columnIndex == 0)
        {
            if(isFill(columnStart, columnEnd, columnIndex, rowIndex, rowStart, rowEnd))
            {
                arr[rowIndex][columnIndex] = c;
            }
        }
        else
        {
            if(isFill(columnStart, columnEnd, columnIndex, rowIndex, rowStart, rowEnd))
            {
                arr[rowIndex][columnIndex] = c;
            }
            fillRow(c, rowIndex, arr, rowStart, rowEnd, columnStart, columnEnd, columnIndex - 1);
        }
    }

    /**
     * Determine whether current index of row and column is between the given boundries
     * @param columnStart initial index of column 
     * @param columnEnd last index of column
     * @param columnIndex recent column index
     * @param rowIndex recent row index
     * @param rowStart initial index of row
     * @param rowEnd last index of row
     * @return true if it is in between, otherwise false
     */
    public boolean isFill(int columnStart, int columnEnd, int columnIndex, int rowIndex, int rowStart, int rowEnd)
    {
        return (columnStart <= columnIndex && columnIndex < columnEnd) && (rowStart <= rowIndex && rowIndex < rowEnd);
    }

    /**
     * Produces the first pattern on the existing array
     * This pattern fills the array with alternating rectangular boundaries filled with chars # and *
     * Make use of your fillChar method in your recursive patterns
     * 
     * dimensions of the array do not need to match, and based on the size, the boundary at the center may be a line
     */
    public void pattern1()
    {
        findMiddle(0, arrRow, 0, arrColumn);
        pattern1(middle[0], middle[1], middle[2], middle[3]);
    }

    /**
     * Starts with '*' and continue with '#'. Complete the shape by following ordinary pattern
     * @param midRS inital raw index of smallest shape
     * @param midRE last raw index of smallest shape
     * @param midCS inital column index of smallest shape
     * @param midCE last column index of smallest shape
     */
    public void pattern1(int midRS, int midRE, int midCS, int midCE)
    {
        if(midRS == 0 && midRE == arrRow && midCS == 0 && midCE == arrColumn)
        {
            fillChar('*',midRS, midRE, midCS, midCE);
        }

        else
        {
            pattern1(midRS - 1, midRE + 1, midCS - 1, midCE + 1);
            if(isEven(midRS))
            {
                fillChar('*',midRS, midRE, midCS, midCE);
            }
            else
            {
                fillChar('#',midRS, midRE, midCS, midCE);
            }

        }
    }

    /**
     * Finds the smallest shape's indexes which is located in the center of the largest shape
     * @param rowStart initial row index = 0
     * @param rowEnd largest shape's row index
     * @param colStart initiak column index = 0
     * @param colEnd largest shpae's column index
     */
    public void findMiddle(int rowStart, int rowEnd, int colStart, int colEnd)
    {
        if(rowEnd == rowStart || rowStart > rowEnd || colStart == colEnd || colStart > colEnd)
        {
            middle[0] = rowStart - 1;
            middle[1] = rowEnd + 1;
            middle[2] = colStart - 1;
            middle[3] = colEnd + 1;
        }
        else
        {
            findMiddle(rowStart + 1, rowEnd - 1, colStart + 1, colEnd - 1);
        }
    }

    /**
     * Determşine whether the number is even or odd
     * @param n number
     * @return true if given number is even otherwisw return false
     */
    public boolean isEven(int n)
    {
        return n % 2 == 0;
    }

    /**
     * Produces the second pattern on the existing array
     * Any cell that is not a * is filled with # in this pattern
     * You can start by filling the whole array with #.
     * Then, fill each row with the desired width and shift the starting index for the next row
     * 
     * Assume the rows are wrapped so that if we need to print more *, they appear at the beginning of the same row
     * @param fillWidth fills the rows of the array with * chars of the given width
     * @param shiftAmount in each row, the * pattern is shifted by the given shift amount
     */
    public void pattern2(int fillWidth, int shiftAmount)
    {
        fillChar('#', 0, arrRow, 0, arrColumn);
        pattern2(arrRow - 1, (arrRow - 1) * shiftAmount, fillWidth, shiftAmount);
    }

    /**
     * Helper method of patter2()
     * @param rowStart initial index of row
     * @param columnStart initial index of column
     * @param fillWidth amount of *
     * @param shiftAmount amount of shift
     */
    public void pattern2(int rowStart, int columnStart, int fillWidth, int shiftAmount)
    {
        if(rowStart == 0)
        {
            fillChar('*', rowStart, rowStart + 1, 0, fillWidth);
        }
        else
        {
            pattern2(rowStart - 1, columnStart - shiftAmount, fillWidth, shiftAmount);
            if(columnStart % arrColumn > (columnStart + fillWidth) % arrColumn)
            {
                fillChar('*', rowStart, rowStart + 1, columnStart % arrColumn, arrColumn);
                fillChar('*', rowStart, rowStart + 1, 0, (columnStart+ fillWidth) % arrColumn);
            }
            else
            {
                fillChar('*', rowStart, rowStart + 1, columnStart % arrColumn, (columnStart + fillWidth) % arrColumn);
            }
        }
    }

    /**
     * Produces the third pattern on an empty array
     * This pattern fills the first row and first column with 1
     * Then, it fills the rest of the array such that for each element arr(x,y) = (arr(x-1,y) + arr(x,y-1)) % 10
     * each element is equal to the sum of its up and left neighbors in Modulus 10
     * 
     * Since the array we use is made of char variables, 
     * you either need to work on a separate integer array or 
     * convert the chars to integers using the method Integer.parseInt(String s).
     * 
     * You may check if an element is set or not by comparing it to the char '\u0000'
     */
    public void pattern3()
    {
        fillChar('1', 0, 1, 0, arrColumn);
        fillChar('1', 0, arrRow, 0, 1);
        pattern3(arrRow - 1, arrColumn);
    }

    /**
     * Helper method of pattern3()
     * @param lastRow last index of row
     * @param lastColumn last index of column
     */
    public void pattern3(int lastRow, int lastColumn )
    {
        if(lastRow == 1)
        {
            calcRow(lastRow, arrColumn - 1, arr );
        }
        else
        {
            pattern3(lastRow - 1, arrColumn);
            calcRow(lastRow, lastColumn - 1, arr);
        }
    }

    /**
     * Creates an array resembles to pascal triangle
     * @param rowIndex specific row index
     * @param colEnd last index of column
     * @param arr 2D char array
     */
    public void calcRow(int rowIndex, int colEnd, char[][] arr)
    {
        if(colEnd == 1)
        {
            int result = (Integer.parseInt(arr[rowIndex - 1][colEnd] + "") + Integer.parseInt(arr[rowIndex][colEnd - 1] + "")) % 10;
            arr[rowIndex][colEnd] = (char)(result + '0');
        }
        else
        {
            calcRow(rowIndex, colEnd - 1, arr);
            int result = (Integer.parseInt(arr[rowIndex - 1][colEnd] + "") + Integer.parseInt(arr[rowIndex][colEnd - 1] + "")) % 10;
            arr[rowIndex][colEnd] = (char)(result + '0');
        }
    }

    /**
     * Assuming pattern3() is called before, this method finds the highest row sum
     * You may implement an int findRowSum(int row) method to calculate the sum of values in a specific row recursively
     * @return the highest sum.
     */
    public int findMaxRowSum()
    {
        return findMaxRowSum(arrRow - 1, arr);
    } 

    /**
     * Helper method of findMaxRowSum()
     * @param arrRow total row number
     * @param arr 2D char array
     * @return max value of the row
     */
    public int findMaxRowSum(int arrRow, char[][] arr)
    {
        if(arrRow == 0)
        {
            maxRow = sumRow(arrRow, arrColumn - 1, arr);
        }
        else
        {
            findMaxRowSum(arrRow - 1, arr);
            int other = sumRow(arrRow, arrColumn - 1, arr);
            if(other > maxRow)
            {
                maxRow = other;
            }
        }
        return maxRow;
    }

    /**
     * Calculate each raw's summation
     * @param rowIndex speciific row
     * @param curCol total column which is decreased during reccursion
     * @param arr 2D char array
     * @return summation of the specific row
     */
    public int sumRow(int rowIndex, int curCol, char[][]arr)
    {
        if(curCol == 0)
        {
            eachRow = arr[rowIndex][curCol] - 48;
        }
        else
        {
            sumRow(rowIndex, curCol - 1, arr);
            eachRow += arr[rowIndex][curCol] - 48;
        }
        return eachRow;
    }

    /**
     * Prints the current array on the screen
     * You may add additional helper methods to print a row, 
     * which can be called recursively to print all the rows.
     */
    public void print()
    {
        if(startPrint)
        {
            length = arr.length; //to avoid from infinite recursion
            startPrint = false;
        }
        String result = "";
        if(arr.length < 0)
        {
            result += "Array is empty!";
        }
        else if(length == 1)
        {
            result += printRow(arr, length - 1, arr[0].length - 1 ) + "\n" ;
        }
        else
        {
            length--;
            result += printRow(arr, length, arr[0].length - 1 ) + "\n";
            print();
        }

        System.out.println(result);
        startPrint = true;
    }

    /**
     * Make string of one specigic row
     * @param arr 2D char array
     * @param rowIndex specific row
     * @param columnNum end of the recent column corresponds to end of sumation 
     * @return stirng of row
     */
    public String printRow(char[][] arr, int rowIndex, int columnNum)
    {
        String result = "";
        if(columnNum == 0)
        {
            if(arr[rowIndex][0] == '\u0000' )
            {
                result = "[ ] ";
            }
            else 
            {
                result = "[" + arr[rowIndex][0] + "] ";
            }
            
        }
        else
        {
            if(arr[rowIndex][columnNum] == '\u0000' )
            {
                result += printRow(arr, rowIndex, columnNum - 1) + "[ ] ";
            }
            else 
            {
                result += printRow(arr, rowIndex, columnNum - 1) + "[" + arr[rowIndex][columnNum] + "] ";
            }
        }
        return result;
    }
}