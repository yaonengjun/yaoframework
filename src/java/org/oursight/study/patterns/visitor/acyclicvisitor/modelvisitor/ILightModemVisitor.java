package org.oursight.study.patterns.visitor.acyclicvisitor.modelvisitor;

import org.oursight.study.patterns.visitor.acyclicvisitor.modemimpl.LightModem;

public interface ILightModemVisitor {
	
	public void visit(LightModem modem);

}
