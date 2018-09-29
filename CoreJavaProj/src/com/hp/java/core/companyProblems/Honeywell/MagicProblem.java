package com.hp.java.core.companyProblems.Honeywell;

import java.util.Scanner;

/**
 * Recently Oz has found a magical string consisting of single digit "1".
 * After experimenting on the string, Oz found a weird magical property of the string that
 * is whenever he touches the string then each digit "1" of string changed to digit "0" and
 * each digit "0" of string changed to "01". Oz found this property interesting and immediately asked a question to RK :
 * "How many 1's and 0's will be in the magical string if he touches the string M times ?"
 *
 * Input :
 * The first line contains the number of test cases T . Each test case consists of a positive integer - M .
 * Output :
 * For each test case output two space-separated integers, number of 1's and number of 0's in the magical string if Oz touches the string M times.
 * Constraints :
 * 1<= T <=20
 * 1<= M <=90
 *
 * SAMPLE INPUT
 * 2
 * 1
 * 2
 * SAMPLE OUTPUT
 * 0 1
 * 1 1
 */

public class MagicProblem {
    public static void main(String[] args){
        String initialInput = "1";
        Scanner scanner = new Scanner(System.in);
        int noOfTestsCases = scanner.nextInt();

        int[] positiveIntArray = new int[noOfTestsCases];
        for(int index=0;index < noOfTestsCases;index++){
            positiveIntArray[index] = scanner.nextInt();
        }

        MagicBox magicBox = new MagicBox(initialInput);
        for(int index=0;index<noOfTestsCases;index++){
            magicBox.doMagic(positiveIntArray[index]);
            System.out.println(""+magicBox.getCountZeroOne());
        }
    }
}
