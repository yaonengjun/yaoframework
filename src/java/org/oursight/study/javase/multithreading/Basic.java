package org.oursight.study.javase.multithreading;

import java.util.Iterator;
import java.util.Set;

public class Basic {

	public static void listAllThreads() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for (Iterator iterator = threadSet.iterator(); iterator.hasNext();) {
			Thread thread = (Thread) iterator.next();
			System.out.println(thread.getId() + ": " + thread.getName());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// listAllThreads();
		Thread thread = new Thread(new MyThread());
		thread.start();
		thread.interrupt();
	}

}

class MyThread implements Runnable {
	public void run() {

		int i = 0;
		while (i <10000) {
			i++;
			try {
				System.out.println("Thead A: " + i); // 只能被执行到3，因为线程被外部中断了。
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}