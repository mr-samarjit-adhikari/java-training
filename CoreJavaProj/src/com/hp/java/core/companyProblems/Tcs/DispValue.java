package com.hp.java.core.companyProblems.Tcs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DispValue {

    public static void main(String a[]) {

        Map<String, String> Values = new HashMap<>();
        Values.put("Key 1", "Value 1");
        Values.put("Key 2", "Value 2");
        DispValue.iterateMap(Values);
    }

    public static void iterateMap(Map<String, String> Values)
    {

        for(Entry<String, String> entry:Values.entrySet())
        {
            System.out.print(entry.getValue()+", "+entry.getKey());
        }
    }
}

