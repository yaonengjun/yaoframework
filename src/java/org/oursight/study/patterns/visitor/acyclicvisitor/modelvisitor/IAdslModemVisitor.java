package org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor;

import org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl.AdslModem;

public interface IAdslModemVisitor {
	
	public void visit(AdslModem modem);

}
