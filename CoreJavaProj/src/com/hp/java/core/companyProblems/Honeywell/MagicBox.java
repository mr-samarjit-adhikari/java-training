package com.hp.java.core.companyProblems.Honeywell;

public class MagicBox {
    private String initString;
    private String outputString;

    public MagicBox(String initialInput) {
        this.initString = initialInput;
        this.outputString = "";
    }

    public void doMagic(int touchCount) {

        String startingString = initString;
        while(touchCount>0){
            StringBuilder stringBuilder = new StringBuilder();
            int startingStrLength = startingString.length();
            int strIndex = 0;
            while (startingStrLength>0) {
                char chr = startingString.charAt(strIndex);
                if (chr == '1') {
                    stringBuilder.append('0');
                } else if (chr == '0') {
                    stringBuilder.append("01");
                }
                strIndex++;
                startingStrLength--;
                startingString = stringBuilder.toString();
            }
            touchCount--;
            this.outputString = stringBuilder.toString();
        }
    }

    public String getCountZeroOne() {
        int stringLength = outputString.length();
        int index = 0;
        int oneCount = 0;
        int zeroCount = 0;

        while(stringLength>0){
            char chr = outputString.charAt(index);
            if(chr == '1'){
                oneCount++;
            }
            else if(chr == '0'){
                zeroCount++;
            }
            stringLength--;
            index++;
        }
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(oneCount);
        outputBuilder.append(" ");
        outputBuilder.append(zeroCount);
        //Reset the output String
        this.outputString = "";

        return outputBuilder.toString();
    }
}
