package org.oursight.study.patterns.visitor.simplevisitor;

import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.AdslModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.CableModem;
import org.oursight.study.patterns.visitor.simplevisitor.modemimpl.LightModem;

public class MacOSModemVisitor implements IModemVisitor {

	@Override
	public String visit(AdslModem modem) {
		System.out.println("MacOSModemVisitor.visit()");
		System.out.println("Config AdslModem in MacOS! ");
		System.out.println("");

		return "Config AdslModem in MacOS! ";

	}

	@Override
	public String visit(CableModem modem) {
		System.out.println("MacOSModemVisitor.visit()");
		System.out.println("Config CableModem in MacOS! ");
		System.out.println("");

		return "Config CableModem in MacOS! ";

	}

	@Override
	public String visit(LightModem modem) {

		if (modem.accept(this)) {
			System.out.println("MacOSModemVisitor.visit()");
			System.out.println("Config CableModem in MacOS! ");
			System.out.println("");
			return "Config CableModem in MacOS! ";
		}
		
		return "Not Config CableModem in MacOS! ";

	}
}
