package com.hp.java.core.companyProblems.Nagarro;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Alice is taking a cryptography class and finding anagrams to be very useful.
 * We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string.
 * In other words, both strings must contain the same exact letters in the same exact frequency For example,
 * bacdc and dcbac are anagrams, but bacdc and dcbad are not.
 *
 * Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of
 * character deletions required to make the two strings anagrams. Can you help her find this number?
 *
 * Given two strings,  and , that may or may not be of the same length, determine the minimum number of character deletions
 * required to make  and  anagrams. Any characters can be deleted from either of the strings.
 *
 * For example, if  and , we can delete  from string  and  from string  so that both remaining strings are and  which are anagrams.
 */
public class MakingAnagrams {
    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int charCountTobeDeleted = 0;
        int maxStrLength = 0;
        int minStrLength = 0;
        String maxString = null;
        String minString = null;

        Map<Character,Integer> mapContainer = new HashMap();
        int str1Length = a.length();
        int str2Length = b.length();

        if(str1Length>str2Length){
            maxString = a;
            maxStrLength = str1Length;
            minString = b;
            minStrLength = str2Length;
        }
        else{
            maxString = b;
            maxStrLength = str2Length;
            minString = a;
            minStrLength = str1Length;
        }

        for(int index=0;index<maxStrLength;index++){
            Character chr = new Character(maxString.charAt(index));
            Integer count = mapContainer.get(chr);
            if(count==null) mapContainer.put(chr,1);
            else mapContainer.put(chr,count+1);
        }

        for(int index=0;index<minStrLength;index++){
            char chr = minString.charAt(index);
            Integer value = mapContainer.get(chr);
            if(value==null){
                charCountTobeDeleted++ ;
            }
            else{
                int intValue = value.intValue();
                if(intValue>1) mapContainer.put(chr,--intValue);
                else mapContainer.remove(chr);
            }
        }

        for(Integer val:mapContainer.values()){
            charCountTobeDeleted += val.intValue();
        }

        return charCountTobeDeleted;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String a = scanner.nextLine();
        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        System.out.println(res);
        scanner.close();
    }
}
