package org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl;

import org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor.IAdslModemVisitor;
import org.oursight.study.patterns.visitor.simplevisitor.IModem;
import org.oursight.study.patterns.visitor.simplevisitor.IModemVisitor;

public class AdslModem implements IModem {
	

	@Override
	public boolean accept(IModemVisitor visitor) {
		
		try {
			IAdslModemVisitor v = (IAdslModemVisitor)visitor;
			return true;
		}catch(ClassCastException e) {
			e.printStackTrace();
			return false;
		}
		
	}

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

	

}
