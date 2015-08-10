package org.oursight.study.javase.multithreading.join;

public class JoinSample {

	// 当前线程，显示一条消息
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	// MessageLoop子线程
	private static class MessageLoop implements Runnable {
		@Override
		public void run() {
			String importantInfo[] = { "Mares eat oats", "Does eat oats", "Little lambs eat ivy",
					"A kid will eat ivy too" };
			try {
				for (int i = 0; i < importantInfo.length; i++) {
					// 暂停4秒
					Thread.sleep(4000);
					// 打印一条消息
					threadMessage(importantInfo[i]);
				}
			} catch (InterruptedException e) { // 若线程收到中断
				threadMessage("I wasn't done!");
			}
		}
	}

	public static void main(String args[]) throws InterruptedException {
		// 在中断MessageLoop线程之前的时延（默认为1小时）
		long patience = 3000;

		// 如果命令行参数指定了秒数时延
		if (args.length > 0) {
			try {
				patience = Long.parseLong(args[0]) * 1000;
			} catch (NumberFormatException e) {
				System.err.println("Argument must be an integer.");
				System.exit(1);
			}
		}

		threadMessage("Starting MessageLoop thread"); // 主线程打印一条消息
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop());
		t.start(); // 启动MessageLoop子线程

		threadMessage("Waiting for MessageLoop thread to finish");
		// 循环直到MessageLoop线程退出
		while (t.isAlive()) {
			threadMessage("Still waiting...");
			// 主线程等待1秒，以让MessageLoop线程执行
			t.join(1000);
			// 若主线程执行达到给定时延，中断MessageLoop线程
			if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
				threadMessage("Tired of waiting!");
//				t.interrupt();
				// Shouldn't be long now
				// -- wait indefinitely
				t.join();
			}
		}
		threadMessage("Finally!");
	}
}
