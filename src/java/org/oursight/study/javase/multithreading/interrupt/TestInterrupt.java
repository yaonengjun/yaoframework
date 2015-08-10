package org.oursight.study.javase.multithreading.interrupt;


public class TestInterrupt {
	
	public static void main(String[] args) {
		Thread testThread = new Thread(new MyThread());
		testThread.start();
		
		try {
			Thread.sleep(3000);
			testThread.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
