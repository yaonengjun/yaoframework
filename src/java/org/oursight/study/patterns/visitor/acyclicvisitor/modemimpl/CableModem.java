package org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl;

import org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor.ICableModemVisitor;
import org.oursight.study.patterns.visitor.simplevisitor.IModem;
import org.oursight.study.patterns.visitor.simplevisitor.IModemVisitor;

public class CableModem implements IModem {

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
			ICableModemVisitor v = (ICableModemVisitor)visitor;
			return true;
		}catch(ClassCastException e) {
			e.printStackTrace();
			return false;
		}
	}

}
