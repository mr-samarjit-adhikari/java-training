package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

public class CandiesTestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();

        int[] arr = new int[arraySize];
        for(int i=0;i<arraySize;i++){
            arr[i] = scanner.nextInt();
        }

        CandiesTestCase testCase = new CandiesTestCase();
        long result = testCase.solve(arr);
        System.out.println(result);
    }

    private long solve(int[] arr) {
        int[] candies = new int[arr.length];//candies array
        int[] dp = new int[arr.length];

        dp[0] = 1;
        candies[0] = 1;

        for(int i=1;i<arr.length;i++){
            dp[i] = dp[i-1] + compAndDist(arr,i,candies);
            //System.out.println("i-"+i+"dp[i] - "+dp[i]);
        }

        return dp[arr.length-1];
    }

    private int compAndDist(int[] arr, int i, int[] candies) {
        int allocatedCandies = 0;
        if(0 == candies[i]) {
            candies[i] = 1; //minimum allocation
            allocatedCandies++;
        }

        if(arr[i] > arr[i-1]){
            if(candies[i] <= candies[i-1]) {
                candies[i] = candies[i - 1] + 1;
                allocatedCandies += (candies[i]-1);
            }
        }
        else if(arr[i-1] > arr[i]){
            if(candies[i-1] <= candies[i]){
                candies[i-1] = candies[i-1] +1;
                allocatedCandies++;
            }

        }

        return allocatedCandies;
    }
}