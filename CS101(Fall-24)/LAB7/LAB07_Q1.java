/**
 * This program calculates some matrix operations.(multiple, determine the shape: rectangle, trianle, subset)
 * Prints the matrix and also the relevant result.
 * 
 * @author ECE ŞEŞEN
 * @version 26.11.2024
 */

public class LAB07_Q1
{
    public static void main(String [] args)
    {
        int[][] matrixOne = {{1, 2, 3}, {4, 5, 6}};
        int[][] matrixTwo = {{7, 8}, {9, 10}, {11, 12}};
        int[][] matrixSquare = {{1, 0, 0},{2, 1, 0},{3, 4, 1}};
        int[][] childMatrix = {{1, 5},{8, 9}};
        int[][] parentMatrix = {{1, 0, 2, 3},{4, 1, 5, 6},{7, 8, 9, 10}};

        System.out.println("Matrix One:");
        printMatrix(matrixOne);
        System.out.println("Matrix Two:");
        printMatrix(matrixTwo);
        System.out.println("Multiplication Result:");
        int[][] result = multiplyMatrices(matrixOne, matrixTwo);
        printMatrix(result);
        System.out.println("Checking if matrix is rectangular:");
        printMatrix(matrixSquare);
        String rectangularResult = checkRectangular(matrixSquare);
        System.out.println("Result: " + rectangularResult);
        System.out.println("Checking if child matrix is a subset of parent matrix.");
        System.out.println("Parent:");
        printMatrix(parentMatrix);
        System.out.println("Child:");
        printMatrix(childMatrix);
        boolean isSubsetResult = isSubset(parentMatrix, childMatrix);
        System.out.println("Is child matrix a subset of parent matrix? " + isSubsetResult); 
    }

    /**
     * Multiply two matrixes
     * @param matrixOne first 2D array
     * @param matrixTwo second 2D array
     * @return result
     */
    public static int[][] multiplyMatrices(int[][] matrixOne, int[][] matrixTwo)
    {
        if(matrixOne.length != matrixTwo[0].length)
        {
            return null;
        }
        else
        {
            int row = matrixOne.length;
            int[][] multiplied = new int[row][row];
            for(int i  = 0; i < matrixOne.length; i++)
            {
                for(int j = 0; j < matrixTwo[0].length; j++)
                {
                    int sum = 0;
                    for(int k = 0; k < matrixOne[0].length; k++)
                    {
                        int number = matrixOne[i][k] * matrixTwo[k][j];
                        sum += number;
                    }
                    multiplied[i][j] = sum;
                }
            }
            return multiplied;
        }
    }

    /**
     * Determine the shape of the matrix
     * @param matrix 2D array
     * @return name of the shape
     */
    public static String checkRectangular (int[][] matrix)
    {
        String result = "";
        if(isLowerTriangle(matrix))
        {
            result = "Lower Triangle";
        }
        else if(isUpperTriangle(matrix))
        {
            result = "Upper Triangle";
        }
        else
        {
            result = "Neither";
        }
        return result;
    }

    /**
     * Determine whether matrix is lower triangle or not (upper triangle: below the diagnol is zero)
     * @param matrix 2D array
     * @return true is array is upper triangle
     */
    public static boolean isUpperTriangle(int[][] matrix)
    {
        boolean isUpper = true;
    
        for(int i = matrix.length - 1; i > 0; i--) 
        {
            for(int k = 0; k < i ; k++)
            {
                if(matrix[i][k] != 0)
                {
                    isUpper = false;
                    break;
                }
            }
        }
        return isUpper;
    }

    /**
     * Determine whether matrix is lower triangle or not (lower triangle: above the diagnol is zero)
     * @param matrix 2D array
     * @return true is array is lower triangle
     */
    public static boolean isLowerTriangle(int[][] matrix)
    {
        boolean isLower = true;
    
        for(int i = 0; i < matrix.length - 1; i++)
        {
            for(int k = matrix[0].length - 1; k > i; k--)
            {
                if(matrix[i][k] != 0)
                {
                    isLower = false;
                    break;
                }
            }
        }
        return isLower;
    }

    /**
     * Determine wheter child is subset of parent.
     * @param parent big 2D array
     * @param child small 2D array
     * @return true if child is subset of parent
     */
    public static boolean isSubset(int[][] parent, int[][] child)
    {
        boolean result = false;
        int rowP = parent.length;
        int colP = parent[0].length;
        int rowC = child.length;
        int colC = child[0].length;

        int[][] test = new int[rowC][colC];

        for(int i = 0; i < rowP - rowC + 1; i++)
        {
            for(int k = 0; k < colP - colC + 1; k++)
            {
                for(int a = 0; a < rowC; a++)
                {
                    for(int b = 0; b < colC; b++)
                    {
                        test[a][b] = parent[i + a][k + b];
                    }
                }

                if(areTheySame(test, child))
                {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Determine if two 2D arrays are equal or not.
     * @param part1 first 2D array
     * @param part2 second 2D array
     * @return wheter they are equal or not
     */
    public static boolean areTheySame (int[][] part1, int[][] part2)
    {
        boolean areSame = true;
        for(int i = 0; i < part1.length && areSame; i++)
        {
            for(int k = 0; k < part1[0].length; k++)
            {
                if(part1[i][k] != part2[i][k])
                {
                    areSame = false;
                    break;
                }
            }
        }
        return areSame;
    }

    /**
     * Print values in rows and columns
     * @param matrix 2D array
     */
    public static void printMatrix(int[][] matrix)
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[i].length; j++)
            {
                int num = matrix[i][j];
                System.out.printf("%3d%s", num, " "); //To make all numbers aligned.
            }
            System.out.println();
        }
    }
}