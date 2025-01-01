import java.util.Arrays;

public class hw2
{
    public static void main(String[] args)
    {

        System.out.printf("%10s%-15s%-15s%n","","algorithm A", "algorithm B" );
        for(int i = 10; i <= 100000; i = i * 10)
        {
            int[] randomNumbers = createArray(i);
            mergeSort(randomNumbers);

            long start1 = System.nanoTime();
            int commonA = findMostCommonA(randomNumbers);
            long end1 = System.nanoTime();
            long start2 = System.nanoTime();
            int commonB = findMostCommonB(randomNumbers);
            long end2 = System.nanoTime();

            System.out.printf("%-10d%-15d%-15d%n", i,(end1-start1), (end2-start2));
        }
    }

    public static int[] createArray(int size)
    {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++)
        {
            arr[i] = (int)(Math.random() * 10);
        }
        return arr;
    }

    public static int findMostCommonA(int[] a1)
    {
        int mostCommon = 0;
        int result = -1;
        int recent = -1;
        int count = 0;
        for(int i = 0; i < a1.length; i++)
        {
            if(a1[i] == recent)
            {
                count++;
                if(count > mostCommon)
                {
                    mostCommon = count;
                    result = recent;
                }
            }
            else
            {
                recent = a1[i];
                count = 1;
            }
        }
        return result;
    }

    public static int findMostCommonB(int[] a2)
    {
        int counter = 0;

        int[] counters = new int[a2.length];
        for(int i = 0; i < a2.length; i++)
        {
            int test = a2[i];
            for(int j = 0; j < a2.length; j++)
            {
                if(a2[j] == test)
                {
                    counter++;
                }
            }
            counters[i] = counter - 1;
            counter = 0;
        }

        int max = -1;
        int index = -1;
        for(int i = 0; i < counters.length; i++)
        {
            if(counters[i] > max)
            {
                max = counters[i];
                index = i;
            }
        }
        return a2[index];

    }

    public static void mergeSort(int[] arr)
    {
        if (arr == null || arr.length <= 1) 
        {
            return; // Array is already sorted or empty
        }

        int n = arr.length;
        int[] temp = new int[n]; // Temporary array for merging

        mergeSortRecursive(arr, temp, 0, n - 1);
    }

    private static void mergeSortRecursive(int[] arr, int[] temp, int left, int right) 
    {
        if (left >= right) 
        {
            return; // Base case: array has 0 or 1 element
        }

        int mid = left + (right - left) / 2; // Calculate the middle index

        mergeSortRecursive(arr, temp, left, mid); // Sort left half
            mergeSortRecursive(arr, temp, mid + 1, right); // Sort right half

        merge(arr, temp, left, mid, right); // Merge the two sorted halves
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) 
    {
        // Copy elements to the temporary array
        for (int i = left; i <= right; i++) 
        {
            temp[i] = arr[i];
        }

        int i = left; // Pointer for the left half
        int j = mid + 1; // Pointer for the right half
        int k = left; // Pointer for the merged array

        // Merge the two halves by comparing the elements
        while (i <= mid && j <= right)
        {
            if (temp[i] <= temp[j]) 
            {
                arr[k] = temp[i];
                i++;
            } else 
            {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements from the left half, if any
        while (i <= mid) 
        {
            arr[k] = temp[i];
            i++;
            k++;
        }

        // Copy the remaining elements from the right half, if any
        while (j <= right) 
        {
            arr[k] = temp[j];
            j++;
            k++;
        }
    }
}