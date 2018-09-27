package com.hp.java.core.sortingSearching;


import java.util.Scanner;

/**
 * Given an array A on size N, you need to find the number of ordered pairs(i,j) such that i<j and A[i]>A[j].
 * Input:
 * First line contains one integer, N, size of array.
 * Second line contains N space separated integers denoting the elements of the array A.
 *
 * output:
 * Print the number of ordered pairs(i,j) such that i<j and A[i]>A[j].
 *
 * SAMPLE INPUT
 * 5
 * 1 4 3 2 5
 * SAMPLE OUTPUT
 * 3
 */
public class MergeSortTest extends TestFixture {
    public static void main(){
        Scanner scanner = new Scanner(System.in);
        int inputArraySize = scanner.nextInt();

        //initialize the array
        int[] inputArray = new int[inputArraySize];
        for(int index=0;index<inputArraySize;index++){
            inputArray[index] = scanner.nextInt();
        }

        SortExecutor sortExecutor = new SortExecutor(ALGO.MERGESORT);
        sortExecutor.sort(inputArray);

        printInputArray(inputArray);
    }
}
