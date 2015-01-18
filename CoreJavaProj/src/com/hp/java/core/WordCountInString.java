package com.hp.java.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class WordCountInString {
	
	public static void main(String[] args) {
		String fileInputName = "/home/ixsamar/workspace-java/workspace-training/CoreJavaProj/src/com/hp/java/core/WordCountInput.txt";
		String inputString = "";
		Map<String,Integer>  Dict = new HashMap<String, Integer>();
		
		BufferedReader reader = null;
		// Read from file and store in StringBuilder
		try {
			reader =  new BufferedReader(new FileReader(fileInputName));
			String line = reader.readLine();
			StringBuilder sb = new StringBuilder();
			while(line != null){
				sb.append(line);
				sb.append(" ");
				line = reader.readLine();
			}
			
			inputString = sb.toString();
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found " + fileInputName);
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}

		String[] strArray = inputString.split(" ");
		for(String str:strArray){
			if(Dict.containsKey(str)){
				Integer value = Dict.get(str);
				Dict.put(str, value++);
			}
			else{
				Dict.put(str, 1);
			}
		}
		
		for(String str:Dict.keySet()){
			System.out.println(str + " " +Dict.get(str));
		}
	}

}
