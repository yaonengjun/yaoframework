package org.oursight.study.javase.thread;

public class Join {
	public static void main(String[] args) {
		Thread t = new Thread(new RunnableImpl());
		t.start();
		try {
			t.join(1000);
			System.out.println("joinFinish");
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}

class RunnableImpl implements Runnable {

	public void run() {
		try {
			System.out.println("Begin sleep");
			Thread.sleep(2000);
			System.out.println("End sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
