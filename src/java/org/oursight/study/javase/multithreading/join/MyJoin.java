package org.oursight.study.javase.multithreading.join;

public class MyJoin {

	public static void main(String[] args) throws InterruptedException {
		Thread a = new Thread(new MyThreadA());
		Thread b = new Thread(new MyThreadB());
		Thread c = new Thread(new MyThreadC());

		int i = 0;
		while (i < 5) {
			i++;
			System.out.println("Before join: " + i);
		}
		
		System.out.println("Before start");
		a.start();
		 a.join(3000); // 等待A线程执行3秒
//		a.join();

		b.start();
		b.join();

		c.start();
		c.join();

		// a.join();
		// b.join();
		// c.join();
		
		int j = 0;
		while (j < 5) {
			j++;
			System.out.println("After join: " + j);
		}
		//
		System.out.println("After join");
	}
}

class MyThreadA implements Runnable {
	public void run() {

		int i = 0;
		while (i < 5) {
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

class MyThreadB implements Runnable {
	public void run() {

		int i = 0;
		while (i < 5) {
			i++;
			try {
				System.out.println("Thead B: " + i); // 只能被执行到3，因为线程被外部中断了。
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}

class MyThreadC implements Runnable {
	public void run() {

		int i = 0;
		while (i < 5) {
			i++;
			try {
				System.out.println("Thead C: " + i); // 只能被执行到3，因为线程被外部中断了。
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}