package com.hp.java.core.companyProblems.HackerRank;


import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/maximizing-xor/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign
 */
public class MaximizingXORTestCase {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int l = scanner.nextInt();
        int r = scanner.nextInt();

        MaximizingXORTestCase testCase = new MaximizingXORTestCase();
        int result = testCase.maximizingXor(l,r);
        System.out.println(result);
    }

    private int maximizingXor(int l, int r) {

        int s = l^r;
        int p = 1;
        while(s>0){
            p<<=1;
            s>>=1;
        }

        return p-1;
    }
}
