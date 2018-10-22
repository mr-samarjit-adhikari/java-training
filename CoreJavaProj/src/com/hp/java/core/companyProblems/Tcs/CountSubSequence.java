package com.hp.java.core.companyProblems.Tcs;

import java.io.*;
import java.util.*;

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
