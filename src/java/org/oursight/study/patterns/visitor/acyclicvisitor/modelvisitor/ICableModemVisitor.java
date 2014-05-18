package org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor;

import org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl.CableModem;

public interface ICableModemVisitor {
	
	public void visit(CableModem modem);

}
