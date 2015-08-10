package org.oursight.study.javase.multithreading.synchronization;

class Counter {
	private int c = 0;

	public synchronized void increment() {
		c++;
	}

	public synchronized void decrement() {
		c--;
	}

	public synchronized int value() {
		return c;
	}

}

//class Counter {
//	private int c = 0;
//
//	public void increment() {
//		c++;
//	}
//
//	public void decrement() {
//		c--;
//	}
//
//	public int value() {
//		return c;
//	}
//
//}

class CounterThread implements Runnable {

	private Counter couter;

	private String operation = "add";

	long sleepTime = 1000;

	public CounterThread(Counter c, String op, long time) {
		this.couter = c;
		this.operation = op;
		this.sleepTime = time;
	}

	public void run() {

		int i = 0;

		while (i < 5) {
			
			System.out.println(Thread.currentThread().getName() + " " + operation + " before : " + couter.value());

			if ("add".equals(operation)) {
				couter.increment();
			} else {
				couter.decrement();
			}

			System.out.println(Thread.currentThread().getName() + " " + operation + " after : " + couter.value());
			System.out.println();
			
			i++;

			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println(Thread.currentThread().getName() + "IS DONE");
	}
}

public class RunCounters {

	public static void main(String[] args) {

		Counter c = new Counter();

		Thread t1 = new Thread(new CounterThread(c, "add", 1000));
		t1.setName("Thread t1 ");
		t1.start();

		Thread t2 = new Thread(new CounterThread(c, "sub", 1000));
		t2.setName("Thread t2 ");
		t2.start();

//		Thread t3 = new Thread(new CounterThread(c, "add", 1000));
//		t3.setName("Thread t3 ");
//		t3.start();
	}

}
