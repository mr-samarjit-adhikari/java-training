package com.hp.java.core.companyProblems.Amazon;

/**
 * Given two numbers a and b, you have to find the Nth number which is divisible by a or b.
 *
 * Input :
 *
 * First line consists of an integer T, denoting the number of test cases.
 * Second line contains three integers a, b and N .
 *
 * Output :
 *
 * For each test case, print the
 *
 * number in a new line.
 *
 * Constraints :
 *
 * SAMPLE INPUT
 *
 * 1
 * 2 3 10
 *
 * SAMPLE OUTPUT
 *
 * 15
 *
 * Explanation
 *
 * The numbers which are divisible by 2 or 3 are: 2,3,4,6,8,9,10,12,14,15
 * and the 10th number is 15.
 */

import java.util.Scanner;

import static java.lang.Math.max;

public class NumbersIITestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();

        while(tc>0){
            NumbersIITestCase testCase = new NumbersIITestCase();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int n = scanner.nextInt();

            long result = testCase.solve(a,b,n);
            System.out.println(result);
            tc--;
        }
        scanner.close();
    }

    private long solve(int a, int b, int n) {
        long dp[][] = new long[2][n+1];

        dp[0][0] = a;
        dp[1][0] = b;

        for(int i=1;i<=n;i++){
            if(dp[0][i-1] <= dp[1][i-1]){
                dp[0][i] = dp[0][i-1] + dp[0][0];
                dp[1][i] = dp[1][i-1];
            }else{
                dp[0][i] = dp[0][i-1];
                dp[1][i] = dp[1][i-1] + dp[1][0];
            }
        }

        return max(dp[0][n],dp[1][n]);
    }
}
