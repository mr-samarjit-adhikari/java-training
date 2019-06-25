package com.hp.java.core.companyProblems.Amazon;

import java.util.Scanner;

import static java.lang.Math.min;

public class MicroandMiniTestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();

        while(tc>0){
            MicroandMiniTestCase testCase = new MicroandMiniTestCase();
            String inputString = scanner.next();
            int price = testCase.calculateStringPrice(inputString);
            System.out.println(price);
            tc--;
        }
    }

    private int calculateStringPrice(String inputString) {
        char[] inputStrArray = inputString.toCharArray();
        int inputStrArraySize = inputString.length();
        int[] price = new int[inputStrArraySize+1];

        price[0] = 1;
        int idx = 0;
        for(idx=1;idx<inputStrArraySize;idx++){
//            if(inputStrArray[idx] != inputStrArray[idx-1]){
                price[idx] = price[idx-1] + 1;
                if(idx>1 && (idx != inputStrArraySize-1)) {
                    String subStr1 = inputString.substring(0, idx);
                    String subStr2 = inputString.substring(idx, idx + min(inputStrArraySize-idx,subStr1.length()));
                    if (subStr1.equals(subStr2)) {
                        break;
                    }
                }
//            }else{
//                price[idx] = price[idx-1];
//            }
        }

        return price[idx-1];
    }
}
