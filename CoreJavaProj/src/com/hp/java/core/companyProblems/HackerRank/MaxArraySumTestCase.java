package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * Max Array Sum
 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate
 * the sum of that subset.
 * For example, given an array we have the following possible subsets:
 * Subset     Sum
 * [-2, 3, 5]  6
 * [-2, 3]     1
 * [-2, -4]    -6
 * [-2, 5]     3
 * [1, -4]     -3
 * [1, 5]      6
 * [3, 5]      8
 * Our maximum subset sum is 8.
 * Function Description
 * Complete the maxSubsetSum function in the editor below. It should return an integer representing the
 * maximum subset sum for the given array.
 * maxSubsetSum has the following parameter(s):
 * arr: an array of integers
 *
 * Input Format
 * The first line contains an integer, n.
 * The second line contains space-separated integers arr[i].
 * Constraints
 * 1<=n<=10 pow 5
 * -10 pow 4 <=arr[i] <=10 pow 4
 * Output Format
 * Return the maximum sum described in the statement.
 *
 * Sample Input 0
 * 5
 * 3 7 4 6 5
 * Sample Output 0
 * 13
 * Explanation 0
 * Our possible subsets ar
 */

public class MaxArraySumTestCase {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int arrayElementCount = scanner.nextInt();
        int inputArray[] = new int[arrayElementCount];

        for(int index=0;index<arrayElementCount;index++){
            inputArray[index] = scanner.nextInt();
        }

        MaxArraySumTestCase maxArraySumTestCase = new MaxArraySumTestCase();
        int maxSum=0,prevMaxSum=0;
        for(int idx=0;idx<arrayElementCount;idx++) {
             maxSum = maxArraySumTestCase.maxSubsetSum(inputArray, idx,arrayElementCount-1);
             if(maxSum > prevMaxSum){
                 prevMaxSum = maxSum;
             }
        }
        System.out.println(prevMaxSum);
    }

    private int maxSubsetSum(int[] inputArray,int startIdx,int endIdx) {
        int inputArrayLen = endIdx-startIdx+1;

        int dp[] = new int[inputArrayLen];
        dp[0] = dp[1] = inputArray[startIdx];

        if(inputArrayLen==1) return dp[0];
        for(int index=2;index<inputArrayLen;index++){
            dp[index] = max(dp[index-1], max(dp[index-2],dp[index-2]+inputArray[index]));
        }

        return max(dp[inputArrayLen-1],dp[inputArrayLen-2]);
    }
}
