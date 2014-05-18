package org.oursight.study.patterns.command.activeobject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SleepCommand implements ICommand {
	
	private boolean started = false;
	private long startedTime = 0;
	
	private ICommand wakeupCommand;
	private ActiveObjectEngine engine = null; 
	private long sleepInterval = 0;
	
	
	public SleepCommand(long sleepInterval, ActiveObjectEngine engine, ICommand wakeupCommand) {
		this.wakeupCommand = wakeupCommand;
		this.engine = engine;
		this.sleepInterval = sleepInterval;
	}
	
	/**
	 *  执行方法，此处是ActiveObject应用模式的关键代码（1 of 2）。
	 * @see org.oursight.study.patterns.command.activeobject.ICommand#execute()
	 */
	public void execute() throws Exception {
		long currentTime = System.currentTimeMillis();
//		System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(currentTime)) + "] SleepCommand: StartWorking");
		
		if(!started) {
			started = true;
			startedTime = currentTime;
			engine.addCommand(this);
			
		}else if((currentTime - startedTime) <= sleepInterval) {
			System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date(System.currentTimeMillis())) + "] SleepCommand: Still sleeping");
			engine.addCommand(this);
			// or do nothing, both will do.
			
		}else {
			System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSSS").format(new Date(System.currentTimeMillis())) + "] SleepCommand: It's WakeupCommand's turn");
			engine.addCommand(wakeupCommand);
		}
		
//		System.out.println("[" + new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(currentTime)) + "] SleepCommand: Done");
		
	}

}
