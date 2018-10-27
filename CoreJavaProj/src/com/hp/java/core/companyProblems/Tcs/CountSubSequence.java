package com.hp.java.core.companyProblems.Tcs;

import java.io.*;

/**
 * Counting the Subsequences
 * You are given a string  consisting of the upper/lower case alphabets.
 * You have to answer  queries based on the string . Each query consists of a character (alphabet).
 * You need to count number of subsequences ending with the given character. Since the answer can be large output it modulo
 *
 * Input format:
 *
 * First line consists of a String  , as described above.
 * Next line consists of a number  .
 * Each of the next  lines consists of a character.
 *
 * Output format:
 * For each query, output the required answer separated by a space.
 *
 * Constraints:
 * 1 \le |S| \le 10^5
 * 1 \le Q \le 10^6
 *
 * Sample Input
 * Ryan
 * 2
 * y
 * X
 * Sample Output
 * 2 0
 * Explanation
 * In the first query: There are 2 subsequences of S that end with 'y'. {"Ry", "y"}
 *
 * In the second query: No subsequences that end with 'X'.
 */

public class CountSubSequence {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        String S = br.readLine();
        int Q = Integer.parseInt(br.readLine().trim());
        String[] arr = new String[Q];
        for(int i_arr=0; i_arr<Q; i_arr++)
        {
            arr[i_arr] = br.readLine();
        }

        int[] out_ = helpPrivateRyan(S, arr);
        System.out.print(out_[0]);
        for(int i_out_=1; i_out_<out_.length; i_out_++)
        {
            System.out.print(" " + out_[i_out_]);
        }

        wr.close();
        br.close();
    }
    static int[] helpPrivateRyan(String S, String[] arr){
        // Write your code here
        int inputStrLength = S.length();
        int inputCharListLength = arr.length;
        int[] outout = new int[inputCharListLength];

        for(int index=0;index<inputCharListLength;index++){
            String item = arr[index];
            //item is of length of 1
            assert(item.length()==1);
            char chr = item.charAt(0);
            int count = findSubSeqCount(S,chr);

            outout[index] = count;
        }

        return outout;
    }

    private static int findSubSeqCount(String inputStr, char chr) {
        int count = 0;
        int inputStrLen = inputStr.length();

        for(int index=0;index<inputStrLen;index++){
            char inputChr = inputStr.charAt(index);
            if(inputChr == chr){
                count = index +1;
                break;
            }
        }
        return count;
    }
}
