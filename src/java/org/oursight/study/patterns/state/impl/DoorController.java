package org.oursight.study.patterns.state.impl;

import org.oursight.study.patterns.state.IDoorController;

public class DoorController implements IDoorController {

	@Override
	public void lock() {
		System.out.println("The door is locked");
		
	}

	@Override
	public void unlock() {
		System.out.println("The door is unlocked");
		
	}

	@Override
	public void thankyou() {
		System.out.println("Thank you , you can pass");
		
	}

	@Override
	public void alarm() {
		System.out.println("Alarm, alarm!");
		
	}

}
