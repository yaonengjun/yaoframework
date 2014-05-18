package org.oursight.study.patterns.visitor.simplevisitor;

import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.AdslModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.CableModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.LightModem;

public interface IModemVisitor {
	
	String visit(AdslModem modem);
	
	String visit(CableModem modem);
	
	String visit(LightModem modem);

}
