package com.hp.java.core;

import java.util.HashSet;

public class CollectionHashSet {

	public static void main(String[] args) {
		
		HashSet<Object> hashSet = new HashSet<Object>();
		hashSet.add(3);
		hashSet.add("String 1");
		hashSet.add(3);
		
		System.out.println("Hash Set is "+hashSet);

	}

}
