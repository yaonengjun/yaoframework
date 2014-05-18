package org.oursight.study.patterns.visitor.simplevisitor;

import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.AdslModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.CableModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.LightModem;

public class UnixModemVisitor implements IModemVisitor {

	@Override
	public String visit(AdslModem modem) {
		System.out.println("UnixModemVisitor.visit()");
		System.out.println("Config AdslModem in Unix! ");
		System.out.println("");
		
		
		
		return "Config AdslModem in Unix! ";
		
	}

	@Override
	public String visit(CableModem modem) {
		System.out.println("UnixModemVisitor.visit()");
		System.out.println("Config CableModem in Unix! ");
		System.out.println("");
		
		return "Config CableModem in Unix! ";
		
		
	}

	@Override
	public String visit(LightModem modem) {
		
		if(modem.accept(this)) {
			System.out.println("UnixModemVisitor.visit()");
			System.out.println("Config CableModem in Unix! ");
			System.out.println("");
			
			return "Config CableModem in Unix! ";
		}
		
		return "Not config CableModem in Unix!";
	}

}
