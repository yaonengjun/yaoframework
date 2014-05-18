package org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl;

import org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor.ILightModemVisitor;
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
		try {
			ILightModemVisitor v = (ILightModemVisitor)visitor;
			return true;
		}catch(ClassCastException e) {
			e.printStackTrace();
			return false;
		}
	}

}
