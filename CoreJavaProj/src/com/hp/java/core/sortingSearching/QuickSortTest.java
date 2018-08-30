package com.hp.java.core.sortingSearching;

import java.util.Scanner;

/**
 * Input Format:
 *
 * The first line contains a single integers N denoting the size of the array.
 * The next line contains N space separated integers denoting the contents of the array.
 *
 * Output Format:
 *
 * Print N space separated integers, i.e. the final sorted array.
 * SAMPLE INPUT
 * 5
 * 4 3 1 5 2
 * SAMPLE OUTPUT
 * 1 2 3 4 5
 */
public class QuickSortTest {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int inputArraySize = scanner.nextInt();

        //initialize the array
        int[] inputArray = new int[inputArraySize];
        for(int index=0;index<inputArraySize;index++){
            inputArray[index] = scanner.nextInt();
        }

        SortExecutor  sortExecutor  = new SortExecutor(ALGO.QUICKSORT);
        sortExecutor.sort(inputArray);

        printInputArray(inputArray);
    }

    private static void printInputArray(int[] inputArray) {
        System.out.println("Sorted input: ");
        for(int input:inputArray){
            System.out.print(input+" ");
        }
    }
}
