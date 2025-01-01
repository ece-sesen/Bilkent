/**
 * Implementation of different searching methods and comparing their running times by printing their results.
 *
 * @author ECE SESEN
 * @version 22.11.2024
 */

#include <iostream>
#include <random>
#include <iomanip>
#include "chrono"

using namespace std;
chrono::time_point< std::chrono::system_clock > startTime;
chrono::duration< double, milli > elapsedTime;

///Prototypes
int* createArray(int);
void bubbleSort( int*, int );
int linearSearch(int*, int, int);
int linearSearchRecursive(int*, int, int);
int linearSearchRecursiveHelper(int*, int, int, int);
int binarySearch(int*, int, int);
int jumpSearch(int*, int, int);
int randomSearch(int*, int, int);
void shuffleArray(int*, int);


int main()
{
    double iterations = 1000; ///In order to avoid "0" running times
    double totalTime = 0;
    int result;
    int arraySize[13] = {10, 20, 40, 80, 160, 320, 640, 1280, 2560, 5120, 10240 ,20480, 40960 };

    for(int N = 10; N < 50000; N = N * 2) ///Array size loop
    {
        int *testArr = createArray(N);

            int start = testArr[2]; ///Key element at the beginning
            int mid = testArr[N / 2]; ///Key element in the middle
            int end = testArr[N - 2]; ///Key element at the end
            int notExist = N * 10 + 1; ///Key element does not exist

            int search;

            cout << "Array size is: " << N << endl;

            ///Prints array elements
            /*for(int i = 0; i < N; i++) ///Prints array elements
            {
                cout << testArr[i] << " ";
            }
            cout << endl;*/


            for (int element = 0; element < 4; element++) ///4 scenarios loop
            {
                if (element == 0) {
                    cout << "Key element is in the beginning: " << endl;
                    search = start;
                } else if (element == 1) {
                    cout << "Key element is in the middle: " << endl;
                    search = mid;
                } else if (element == 2) {
                    cout << "Key element is at the end: " << endl;
                    search = end;
                } else {
                    cout << "Key element does not exist: " << endl;
                    search = notExist;
                }
                totalTime = 0;

                for (int j = 0; j < iterations; ++j) {
                    startTime = std::chrono::system_clock::now();
                    result = linearSearch(testArr, N, search);
                    elapsedTime = std::chrono::system_clock::now() - startTime;
                    totalTime += elapsedTime.count();
                }
                std::cout << std::fixed;
                std::cout << std::setprecision(6);
                cout << result << " -> Linear Search time: " << (totalTime / iterations) << endl;;


                totalTime = 0;
                for (int j = 0; j < iterations; ++j) {
                    startTime = std::chrono::system_clock::now();
                    result = linearSearchRecursive(testArr, N, search);
                    elapsedTime = std::chrono::system_clock::now() - startTime;
                    totalTime += elapsedTime.count();
                }
                cout << result << " -> Linear Recursive Search time: " << (totalTime / iterations) << endl;


                totalTime = 0;
                for (int j = 0; j < iterations; ++j) {
                    startTime = std::chrono::system_clock::now();
                    result = binarySearch(testArr, N, search);
                    elapsedTime = std::chrono::system_clock::now() - startTime;
                    totalTime += elapsedTime.count();
                }
                cout << result << " -> Binary Search time: " << (totalTime / iterations) << endl;

                totalTime = 0;
                for (int j = 0; j < iterations; ++j) {
                    startTime = std::chrono::system_clock::now();
                    result = jumpSearch(testArr, N, search);
                    elapsedTime = std::chrono::system_clock::now() - startTime;
                    totalTime += elapsedTime.count();
                }

                cout << result << " -> Jump Search time: " << (totalTime / iterations) << endl;


                totalTime = 0;

                for (int j = 0; j < iterations; ++j) {
                    startTime = std::chrono::system_clock::now();
                    result = randomSearch(testArr, N, search);
                    elapsedTime = std::chrono::system_clock::now() - startTime;
                    totalTime += elapsedTime.count();
                }
                cout << result << " -> Random Search time: " << (totalTime / iterations) << endl;
                cout << endl;
            }
        delete[] testArr;
    }
}

///Create array in desired size
int* createArray(int size)
{
    int range = 10 * size;
    int* arr= new int[size];
    for(int i = 0; i < size; i++)
    {
        arr[i] = rand() % range; ///Fill it with random numbers
    }
    bubbleSort(arr, size);
    return arr;
}
///Make array sorted, ascending order
void bubbleSort( int* arr, int size)
{
    for(int i = size - 1; i > 0; i--)
    {
        for(int j = 0; j < i; j++)
        {
            if(arr[j + 1] < arr[j])
            {
                int temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
    }
}

///Algorithm 1: Linear Search
int linearSearch(int* arr, int size,  int key)
{
    for(int i = 0; i < size; i++)
    {
        if(arr[i] == key)
        {
            return i;
        }
    }
    return -1;
}

///Algorithm 2: Linear Recursive Search
int linearSearchRecursive(int* arr, int size, int key)
{
    return linearSearchRecursiveHelper(arr, size, 0, key );
}

///Helper method of linear recursive search
int linearSearchRecursiveHelper(int* arr, int size, int index, int key)
{
    if(index >= size)
    {
        return -1;
    }
    else if (arr[index] == key)
    {
        return index;
    }
    else
    {
        return linearSearchRecursiveHelper(arr, size, index + 1, key);
    }
}

///Algorithm 3: Binary Search
int binarySearch(int* arr, int size, int key)
{
    int mid;
    int low = 0;
    int high = size - 1;

    while (low <= high)
    {
        mid = (low + high) / 2;
        if(arr[mid] == key)
        {
            return mid;
        }
        else if(arr[mid] < key)
        {
            low = mid + 1;
        }
        else if(arr[mid] > key)
        {
            high = mid - 1;
        }
    }
    return -1;
}

///Algorithm 4: Jump Search
int jumpSearch(int* arr, int size, int key)
{
    int jump = (int)sqrt(size); ///Efficient amount of jump
    int startBlock = 0;
    int endBlock = jump;

    while (endBlock < size && arr[endBlock] < key)
    {
        startBlock = endBlock;
        endBlock += jump;

        if (endBlock >= size) ///In order to avoid out of index on array
        {
            endBlock = size;
        }
    }

    for (int i = startBlock; i < endBlock; i++) ///Search for key element between determined block
    {
        if (arr[i] == key)
        {
            return i;
        }
    }
    return -1;
}

///Algorithm 5: Random Search
int randomSearch(int* arr, int size, int key)
{
    int* tempArr = new int[size]; /// Create a copy of original one in order not to change array and lose any data
    copy(arr, arr + size, tempArr);

    shuffleArray(tempArr, size); ///Mix elements
    int result = linearSearch(tempArr, size, key);

    delete[] tempArr;
    return result;
}

void shuffleArray(int* arr, int size)
{
    /// Fisher-Yates shuffle
    for (int i = size - 1; i > 0; i--)
    {
        int j = rand() % (i + 1); /// Generate a random index in the range [0, i]
        swap(arr[i], arr[j]);
    }
}

