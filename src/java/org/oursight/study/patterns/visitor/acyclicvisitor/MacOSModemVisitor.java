package org.oursight.study.patterns.visitor.acyclicvisitor;

import org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor.IAdslModemVisitor;
import org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor.ICableModemVisitor;
import org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl.CableModem;

public class MacOSModemVisitor implements IModemAcyclicVisitor, IAdslModemVisitor, ICableModemVisitor {

	@Override
	public void visit(org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl.AdslModem modem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CableModem modem) {
		// TODO Auto-generated method stub
		
	}

}
