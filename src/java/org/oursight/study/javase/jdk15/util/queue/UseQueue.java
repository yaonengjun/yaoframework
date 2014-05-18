package org.oursight.study.javase.jdk15.util.queue;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

public class UseQueue {
	
	public Queue queue;
	
	public UseQueue() {
		queue = new LinkedList();
	}
	
	public void testFIFO(PrintStream out) {
		queue.add("First");
		queue.add("Second");
		queue.add("Third");
		
		Object o;
		while((o = queue.poll()) != null) {
			out.println(o);
		}
	}
	
	public static void main(String[] args) {
		UseQueue useQueue = new UseQueue();
		useQueue.testFIFO(System.out);
	}
	

}
