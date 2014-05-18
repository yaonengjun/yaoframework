package org.oursight.study.javase.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WhatIsEmptyListSize {
	public static void main(String[] args) {
		List linkedList = new LinkedList();
		List arrayList = new ArrayList();
		
		System.out.println("Empty LinkedList size: " + linkedList.size());
		System.out.println("Empty ArrayList size: " + arrayList.size());
	}

}
