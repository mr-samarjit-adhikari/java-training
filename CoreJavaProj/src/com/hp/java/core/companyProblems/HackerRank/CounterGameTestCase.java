package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/counter-game/problem
 * https://www.hackerrank.com/challenges/counter-game/forum --  discussion
 */

public class CounterGameTestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();

        CounterGameTestCase testCase = new CounterGameTestCase();

        while(testcases>0){
            long n = scanner.nextInt();
            String winner = testCase.counterGame(n);
            System.out.println(winner);
            testcases -- ;
        }
    }

    private String counterGame(long n) {
        long count = 0 ;
        n = n-1;
        while(n>0){
            n &= (n-1);
            count++;
        }
        return (((count & 0x1)==0x1)?"Louise":"Richard");
    }

}
