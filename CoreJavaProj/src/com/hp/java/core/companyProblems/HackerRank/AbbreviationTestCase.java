package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

public class AbbreviationTestCase {
    public enum Match{
        CHAR_MATCH,
        CHAR_SKIP,
        CHAR_MISMATCH,
        UNKNOWN,
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        AbbreviationTestCase testCase = new AbbreviationTestCase();
        for(int count=0;count<testCaseCount;count++){
            String str1 = scanner.nextLine();
            String str2 = scanner.nextLine();
            String result = testCase.abbreviation(str1, str2);
            System.out.println(result);
        }
    }

    private String abbreviation(String str1, String str2) {
        int bStringLen = str2.length();
        int aStringLen = str1.length();
        char[] bStrArray = str2.toCharArray();
        char[] aStrArray = str1.toCharArray();
        Match [][] dp = new Match[bStringLen][aStringLen];
        int aIndex = 0;
        int bIndex = 0;

        if(aStringLen < bStringLen) return "NO";

        initializeDP(dp,bStringLen,aStringLen);

        dp[0][0] = compareChars(bStrArray[0],aStrArray[0]);
        if(dp[0][0] == Match.CHAR_SKIP){
            aIndex++;
        }else if(dp[0][0] == Match.CHAR_MATCH){
            aIndex++;bIndex++;
        }else{
            return "NO";
        }

        int aindex = aIndex;
        for(int bindex=bIndex;bindex<bStringLen;bindex++){
            while(aindex<aStringLen){
                Match matchStatus = checkAndSet(bStrArray,bindex,aStrArray,aindex,dp);

                if(matchStatus == Match.CHAR_SKIP){
                    aindex++;continue;
                }else if(matchStatus == Match.CHAR_MATCH){
                    aindex++;break;
                }
                else{
                    return "NO";
                }
            }
        }

        return "YES";
    }

    private void initializeDP(Match[][] dp,int bStrlen,int aStrLen) {
        for(int bindex=0;bindex<bStrlen;bindex++){
            for(int aindex=0;aindex<aStrLen;aindex++){
                dp[bindex][aindex] = Match.UNKNOWN;
            }
        }
    }

    private Match checkAndSet(char[] bStrArray, int bindex, char[] aStrArray, int aindex,Match dp[][]) {
        Match lastMatch = Match.UNKNOWN;
        Match currMatch = Match.UNKNOWN;

        if((bindex-1) >= 0 && (aindex-1)>=0){
            if(dp[bindex-1][aindex-1] != Match.UNKNOWN){
                lastMatch =   dp[bindex-1][aindex-1];
            }
        }

        if(bindex>=0 && (aindex-1) >= 0){
            if(dp[bindex][aindex-1] != Match.UNKNOWN){
                lastMatch = dp[bindex][aindex-1];
            }
        }

        if(lastMatch != Match.UNKNOWN){
            if((lastMatch == Match.CHAR_SKIP) || (lastMatch == Match.CHAR_MATCH)) {
                dp[bindex][aindex]= currMatch = compareChars(bStrArray[bindex],aStrArray[aindex]);
            }
            else{
                return Match.CHAR_MISMATCH;
            }
        }

        return currMatch;
    }


    private Match compareChars(char bStrChar, char aStrChar) {

        if ((aStrChar == bStrChar)||(bStrChar == aStrChar -32)) {
            return Match.CHAR_MATCH;
        }
        else if(aStrChar >= 'a' && aStrChar<='z'){
            return Match.CHAR_SKIP;
        }

        return Match.CHAR_MISMATCH;
    }
}
