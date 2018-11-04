package com.hp.java.core.companyProblems.HackerRank;

import java.util.Scanner;

public class AbbreviationTestCase {
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
        int aindex = 0;

        if(aStringLen < bStringLen) return "NO";

        for(int bindex=0;bindex<bStringLen;bindex++){

            if(0==(aindex - aStringLen)) return "NO";

            while(aindex<aStringLen){
                if(bStrArray[bindex] == aStrArray[aindex])
                {
                    aindex++; break;
                }
                else if(aStrArray[aindex]>='a' && aStrArray[aindex]<='z'){
                    if(bStrArray[bindex] == (aStrArray[aindex]-32)){
                        if(bStrArray[bindex] == (aStrArray[aindex+1]-32)) {
                            aindex++;
                            continue;
                        }
                        else{
                            aindex++; break;
                        }
                    }
                    else if(aindex == aStringLen-1){
                        return "NO";
                    }
                    else {
                        aindex++;
                        continue;
                    }
                }
                else{
                    return "NO";
                }
            }
        }

        if(aindex<aStringLen){
            while (aindex< aStringLen){
                if(aStrArray[aindex]>='a' && aStrArray[aindex]<='z'){
                    aindex++; continue;
                }
                else{
                    return "NO";
                }
            }
        }

        return "YES";
    }
}
