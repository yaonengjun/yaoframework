package org.oursight.study.patterns.visitor.simplevisitor.modemimpl;

import org.oursight.study.patterns.visitor.simplevisitor.IModem;
import org.oursight.study.patterns.visitor.simplevisitor.IModemVisitor;

public class LightModem implements IModem {

	@Override
	public void dial() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hangup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recv() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean accept(IModemVisitor visitor) {
		visitor.visit(this);
		
		return true;
	}

}
