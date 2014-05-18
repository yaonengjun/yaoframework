package org.oursight.study.patterns.command.activeobject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestActiveObjectEngine {
	
	private boolean excuted = false;
	
	public void testSleepAndWakeup() {
		
		// 定义了一个内部类。注意：内部类是可以没有实现类的，直接new 接口名称。
		ICommand wakeupCommand = new ICommand() {
			public void execute() throws Exception {
				System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date(System.currentTimeMillis())) + "] WakeupCommand: StartWorking");
				excuted = true;
				System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date(System.currentTimeMillis())) + "] WakeupCommand: Done");
			}
		};
		
		ActiveObjectEngine engine = new ActiveObjectEngine();
		// 沉睡一会儿，然后通过wakeupCommand启动。
		ICommand sleepCommand = new SleepCommand(10, engine, wakeupCommand);
		engine.addCommand(sleepCommand);
		
		long startTime = System.currentTimeMillis();
		System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date(System.currentTimeMillis())) + "] ActiveObjectEngine: StartWorking, current state: " + excuted);
		engine.run();
		System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date(System.currentTimeMillis())) + "] ActiveObjectEngine: Done, current state: " + excuted);
		long endTime = System.currentTimeMillis();
	}
	
	public static void main(String[] args) {
		TestActiveObjectEngine test = new TestActiveObjectEngine();
		test.testSleepAndWakeup();
	}

}
