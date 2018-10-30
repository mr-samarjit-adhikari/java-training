package com.hp.java.core.companyProblems.Capillary;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Maximum Subarray Sum - Kadane's algorithm for single possible array
 * Given an array A of N integers. Now, you have to output the sum of unique values of the maximum subarray sum of all
 * the possible subarrays of the given array A.
 * Note: Subarray means contiguous elements with atleast one element in it.
 *
 * Input Format
 *
 * The first line of the input contains a single integer N, the total number of elements in array A.
 * The next line of the input contains N space-separated integers representing the elements of the array.
 *
 * Output Format
 *
 * The only single line of the output should contain a single integral value representing the answer to the problem.
 *
 * Constraints
 * 1<= N <=2000
 * 0<=|Ai| <= 10^9
 *
 * Sample Input:
 * 4
 * 5 -2 7 -3
 *
 * Sample Output:
 * 17
 *
 */

public class MaxSubArraySumTestCase {

    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O: */
        Map<Integer,Integer> sumCache = new HashMap<Integer, Integer>(); /* <sum,repeat count>*/
        Scanner scanner = new Scanner(System.in);
        int arrayElementCount = scanner.nextInt();                 // Reading input from STDIN
        int[] arraysElements = new int[arrayElementCount];

        for(int index=0;index<arrayElementCount;index++){
            arraysElements[index] = scanner.nextInt();
        }

        MaxSubArraySumTestCase maxSubArraySumTestCase = new MaxSubArraySumTestCase();
        for(int index=0;index<arrayElementCount;index++){
            maxSubArraySumTestCase.calculateAndStoreMaxSubArraySum(arraysElements,index,arrayElementCount-1,sumCache);
        }

        int totalSum = 0;
        for(Integer sumValue:sumCache.keySet()){
            totalSum += sumValue.intValue();
        }
        System.out.println(totalSum);
    }

    private void calculateAndStoreMaxSubArraySum(int[] inputArray, int startIndex,int endIndex,
                                                 Map<Integer, Integer> sumCache) {
        int sum =0, ans=0;

        for (int index=startIndex;index<=endIndex;index++){

            if(index==startIndex && inputArray[index] <0){ //first element of array is -ive
                checkAndStoreToCache(sumCache,inputArray[index]);
                continue;
            }

            if(sum+inputArray[index] >0){   //Kadane's algorithm
                sum += inputArray[index];
            }else{
                sum = 0;
            }
            ans = Math.max(ans,sum);
            checkAndStoreToCache(sumCache,ans);
        }
    }

    private void checkAndStoreToCache(Map<Integer, Integer> sumCache, int key) {
        Integer sumValue = sumCache.get(key);
        if(sumValue == null){
            sumCache.put(key,1);
        }
        else{
            sumCache.put(key,sumValue+1);
        }
    }

}
