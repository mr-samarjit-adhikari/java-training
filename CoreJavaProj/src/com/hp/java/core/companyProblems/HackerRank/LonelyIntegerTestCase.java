package com.hp.java.core.companyProblems.HackerRank;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * You will be given an array of integers. All of the integers except one occur twice. That one is unique in the array.
 *
 * Given an array of integers, find and print the unique element.
 *
 * For example,
 * a=[1,2,3,4,3,2,1], the unique element is 4
 *
 * Input Format
 *
 * The first line contains a single integer n,
 * , denoting the number of integers in the array.
 * The second line contains space-separated integers describing the values in array a.
 */
public class LonelyIntegerTestCase {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int[] inputArray = new int[count];

        for(int index=0;index<count;index++){
            inputArray[index] = scanner.nextInt();
        }

        LonelyIntegerTestCase testCase = new LonelyIntegerTestCase();
        int integer = testCase.findLonelyInteger(inputArray);
        System.out.println(integer);
    }

    private int findLonelyInteger(int[] inputArray) {
        Map<Integer,Integer> intMapStore = new HashMap(inputArray.length);

        for(int arrayEle:inputArray){
            Integer value = intMapStore.get(arrayEle);
            if(value==null){
                intMapStore.put(arrayEle,arrayEle);
            }
            else if(value == arrayEle){
                //remove the element
                intMapStore.remove(arrayEle);
            }
        }

        return Integer.parseInt(intMapStore.keySet().iterator().next().toString());
    }
}
