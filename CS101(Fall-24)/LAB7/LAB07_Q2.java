/**
 * This is a program for bakery.
 * Bakery wanted to organize his process and put customers in order.
 * Buy single bread after selling more than onebread.
 * Dismiss customers who cannot take bread since dough is run out.
 * 
 * @author ECE ŞEŞEN
 * @version 26.11.2024
 * 
 */

public class LAB07_Q2
{
    public static void main(String[] args)
    {
        int[] queue = new int[20]; //assignment said it is limited

        System.out.println("---- Testing joinQueue ----");

        joinQueue(3, queue);
        joinQueue(2, queue);
        joinQueue(4, queue);
        joinQueue(1, queue);
        joinQueue(2, queue);
        joinQueue(2, queue);
        joinQueue(1, queue);
        joinQueue(1, queue);

        System.out.println("\n---- Testing full queue ----");
        for(int i = 0; i < 12; i++)
        {
            joinQueue(2, queue);
        }
        joinQueue(1, queue);

        System.out.println("\n---- Testing Bake First ----");
        bake(8, queue);

        System.out.println("\n---- Testing Bake Second ----");
        queue = new int[20];
        //queueSize = 0;
        bake(8, queue);
    }

    /**
     * Add orders to queue in a wanted way. (more than one order follow by one order)
     * @param orderedLoaf order
     * @param queue order of breads
     */
    public static void joinQueue(int orderedLoaf, int[] queue)
    {
        int size = arraySize(queue); //Find Current Size
        if (size >= 20)
        {
            System.out.println("Queue is full. Order rejected.");
        }
        else
        {
            int index = findIndex(queue, orderedLoaf);
            addInput(queue, orderedLoaf, index);
            size++;

           printQueue(queue);
        }
    }

    /**
     * Give bread according to queue and dismiss others who cannot take bread since dough is limited.
     * Print the one who can buy bread.
     * @param expectedLoaves bakers current dough
     * @param queue people waiting for bread
     */
    public static void bake(int expectedLoaves, int[] queue)
    {
        int current = arraySize(queue);
        int takeCust = 0;
        int sum = 0;
        for(int i = 0; i < current; i++)
        {
            if(sum + queue[i] <= expectedLoaves)
            {
                sum += queue[i];
                takeCust++;
            }
            else
            {
                break;
            }
        }

        for(int j = current - 1; j >= takeCust; j--)
        {
            System.out.println("Customer with order " + queue[j] + " loaves dismissed");
            removeInput(queue, j);
        }

        printQueue(queue);

        for(int k = 0; k < takeCust; k++)
        {
            removeInput(queue, queue[k]);
            if(k == takeCust - 1 )
            {
                System.out.println("All customers will receive their bread");
            }
        }
    }

    /**
     * Find length of the current array which means values is different than zero(default)
     * @param arr queue
     * @return length of the current queue
     */
    public static int arraySize(int[] arr)
    {
        int current = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != 0)
            {
                current++;
            }
        }
        return current;
    }

    /**
     * Add one between numbers which are greater than one.
     * @param arr queue
     * @param input one(1)
     * @return updated qeue
     */
    public static int findIndex(int[] arr, int input)
    {
        int current = arraySize(arr);
        if(input > 1)
        {
            return current;
        }
        else
        {
            for(int i  = 0; i < current - 1; i++)
            {
                if(arr[i] > 1 && arr[i + 1] > 1)
                {
                    return i + 1;
                }
            }
        }
        return -1;
    }

    /**
     * First, shift all elements after index to the right and then add wanted value to the desired index.
     * @param arr queue
     * @param input wanted number
     * @param index wanted element index
     */
    public static void addInput(int[] arr, int input, int index)
    {
        int current = arraySize(arr);
        for(int i = current; i > index; i--)
        {
            arr[i] = arr[i - 1];
        }
        arr[index] = input;
    }

    /**
     * Remove unwanted element and shift all elements after index to the left
     * @param arr queue
     * @param index index of unwanted element
     */
    public static void removeInput(int[] arr, int index)
    {
        int current = arraySize(arr);
        for(int i = index; i < current - 1; i++ )
        {
            arr[i] = arr[i + 1];
        }
        arr[current - 1] = 0;
    }

    /**
     * Print queue in a line
     * @param arr queue list
     */
    public static void printQueue(int[] arr)
    {
        int size = arraySize(arr);
        System.out.print("Current Queue: ");
        if(size == 0)
        {
            System.out.print("Empty");
        }
        else
        {
            for(int i = 0; i < size; i++)
            {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}


