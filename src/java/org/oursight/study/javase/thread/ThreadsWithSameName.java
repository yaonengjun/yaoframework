package org.oursight.study.javase.thread;

public class ThreadsWithSameName {
	
	public static void main(String[] args) {
		Thread1 thread1 = new Thread1();
		thread1.setName("thread name");
		thread1.start();
		
		Thread2 thread2 = new Thread2();
		thread2.setName("thread name");
		thread2.start();
	
		
	}
}
	
	class Thread1 extends Thread {
		
		public Thread1() {
			
		}
		
		public void run() {
			try {
				System.out.println("Thread1 is about to sleep");
				Thread.sleep(5000);
				System.out.println("Thread1 wake up from sleep");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	class Thread2 extends Thread {
		
		public void run() {
			try {
				System.out.println("Thread2 is about to sleep");
				Thread.sleep(5000);
				System.out.println("Thread2 wake up from sleep");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

